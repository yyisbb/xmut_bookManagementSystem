package wang.gravity.bookmanagementsystem.aop;


import org.aspectj.lang.reflect.MethodSignature;

import lombok.RequiredArgsConstructor;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.pojo.UserLog;
import wang.gravity.bookmanagementsystem.service.UserLogService;
import wang.gravity.bookmanagementsystem.service.UserService;
import wang.gravity.bookmanagementsystem.utils.JwtUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class UserLogAspect {
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private UserService userService;

    @Pointcut("@annotation(wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();

        saveLog(point, new Date());
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, Date date) throws Exception {
        // 通过 point 拿到方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 通过方法签名拿到被调用的方法
        Method method = methodSignature.getMethod();
        UserLog log = new UserLog();
        // 通过方法区获取方法上的 UserLogAn 注解
        UserLogAnnotation logAnnotation = method.getAnnotation(UserLogAnnotation.class);
        if (logAnnotation != null) {
            log.setType(logAnnotation.operation().getMessage());
        }
        //得到request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //得到token
        String token = request.getHeader("X-Token");
        // 获取 token 中的 username
        String username = JwtUtils.getAudience(token);
        //检查用户是否存在
        User user = userService.getUserByUserName(username);
        //判断是否是管理员
        log.setUserId(user.getId());
        log.setCreatedTime(date);
        userLogService.createLog(log);
    }
}