package wang.gravity.bookmanagementsystem.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.annotation.PassToken;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.UserService;
import wang.gravity.bookmanagementsystem.utils.JwtUtils;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("X-Token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        } else if (method.isAnnotationPresent(AuthCheck.class)) {
            // 执行认证
            if (token == null) {
                //TOKEN为空
                //响应输出对象
                throw new MyException(ErrorCode.TOKEN_NULL_ERROR);
            }
            // 获取 token 中的 username
            String username = JwtUtils.getAudience(token);

            //检查用户是否存在
            User user = userService.getUserByUserName(username);
            if (user == null || user.getId() == 0) {
                //用户不存在
                throw new MyException(ErrorCode.TOKEN_USER_NOT_FOUND_ERROR);
            }
            user.setPassword(null);

            // 验证 token
            JwtUtils.verifyToken(token, username);
            //若token验证成功，把用户信息存储在ThreadLocal
           if (user.getIsAdmin() != 1){
               throw new MyException(ErrorCode.PERMISSION_ERROR);
           }

            UserUtil.setLoginUser(user);
        }

        //默认全部检查
        else {
            // 执行认证
            if (token == null) {
                //TOKEN为空
                //响应输出对象
                throw new MyException(ErrorCode.TOKEN_NULL_ERROR);
            }

            // 获取 token 中的 username
            String username = JwtUtils.getAudience(token);

            //检查用户是否存在
            User user = userService.getUserByUserName(username);
            if (user.getId() == 0) {
                //用户不存在
                throw new MyException(ErrorCode.TOKEN_USER_NOT_FOUND_ERROR);
            }
            user.setPassword(null);

            // 验证 token
            JwtUtils.verifyToken(token, username);
            //若token验证成功，把用户信息存储在ThreadLocal
            UserUtil.setLoginUser(user);
            return true;

        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //清除线程变量
        UserUtil.removeUser();
    }
}
