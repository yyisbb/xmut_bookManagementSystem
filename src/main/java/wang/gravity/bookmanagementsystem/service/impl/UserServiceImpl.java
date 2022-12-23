package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import wang.gravity.bookmanagementsystem.constant.Check;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.dto.PasswordForm;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.UserMapper;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.UserService;
import wang.gravity.bookmanagementsystem.utils.AesUtil;
import wang.gravity.bookmanagementsystem.utils.JwtUtils;
import wang.gravity.bookmanagementsystem.utils.PassWordUtil;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public void initAdmin() {
        List<User> admin = userMapper.findAdmin();
        if (admin == null || admin.size() == 0) {
            userMapper.register(new User(0, "admin", "7251B052D39F11B21EA8C5CD30643FD6", "admin", "", 1, "https://zwjimg.oss-cn-beijing.aliyuncs.com/6BF46770-4CA4-4A8D-B85C-B3A7C27F75C0_1_105_c.jpeg", 1, new Date(), 0));
        }
    }

    @Override
    public String login(User user) throws IllegalArgumentException {
        //参数为空抛异常
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //对密码进行加密
        String password = AesUtil.encrypt(user.getPassword());
        //将加密后的子串作为密码
        user.setPassword(password);
        User loginUser = userMapper.login(user);
        if (loginUser == null) {
            //账号密码错误
            throw new MyException(ErrorCode.USERNAME_PASSWORD_ERROR);
        }
        //账号密码存在但状态为禁用
        if (loginUser.getStatus() != 1) {
            //用户状态异常
            throw new MyException(ErrorCode.USER_STATUS_ERROR);
        }
        //生成token
        return JwtUtils.createToken(loginUser);
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public String register(User user) {
        //参数为空抛异常
        if (user.getUsername() == null
                || user.getPassword() == null
                || user.getStudentId() == null
                || user.getNickname() == null
        ) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        //校验密码合法性
        boolean pwdMatches = user.getPassword().matches(Check.PASSWORD_REGEX);
        boolean stuIdMatches = user.getStudentId().matches(Check.STUDENT_ID_REGEX);
        boolean usernameMatches = user.getUsername().matches(Check.USERNAME_REGEX);
        boolean nicknameMatches = user.getNickname().matches(Check.NICKNAME_REGEX);
        if (!pwdMatches) {
            throw new MyException(ErrorCode.PASSWORD_FORMAT_ERROR);
        }
        if (!stuIdMatches) {
            throw new MyException(ErrorCode.STUDENT_ID_FORMAT_ERROR);
        }
        if (!usernameMatches) {
            throw new MyException(ErrorCode.USERNAME_FORMAT_ERROR);
        }
        if (!nicknameMatches) {
            throw new MyException(ErrorCode.NICKNAME_FORMAT_ERROR);
        }

        //对密码进行加密
        String password = AesUtil.encrypt(user.getPassword());
        //将加密后的子串作为密码
        user.setPassword(password);
        user.setCreatedTime(new Date());
        List<User> dbUser = userMapper.getUserByUserNameOrStudentId(user.getUsername(), user.getStudentId());
        if (dbUser != null && dbUser.size() > 0) {
            throw new MyException(ErrorCode.USER_EXIST_ERROR);
        }

        userMapper.register(user);
        if (user.getId() == 0) {
            throw new MyException(ErrorCode.REGISTER_ERROR);
        }

        return password;
    }

    @Override
    public void deleteUsers(int[] ids) {
        if (ids.length != 0) {
            userMapper.deleteUsers(ids);
        }
    }

    @Override
    public String addStudent(User user) {
        //参数为空抛异常
        if (user.getUsername() == null
                || user.getStudentId() == null
                || user.getNickname() == null
        ) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        //校验密码合法性
        boolean stuIdMatches = user.getStudentId().matches(Check.STUDENT_ID_REGEX);
        boolean usernameMatches = user.getUsername().matches(Check.USERNAME_REGEX);
        boolean nicknameMatches = user.getNickname().matches(Check.NICKNAME_REGEX);
        if (!stuIdMatches) {
            throw new MyException(ErrorCode.STUDENT_ID_FORMAT_ERROR);
        }
        if (!usernameMatches) {
            throw new MyException(ErrorCode.USERNAME_FORMAT_ERROR);
        }
        if (!nicknameMatches) {
            throw new MyException(ErrorCode.NICKNAME_FORMAT_ERROR);
        }

        //生成密码
        String randomPassword = PassWordUtil.randomPassword(8);
        //将加密后的子串作为密码
        user.setPassword(AesUtil.encrypt(randomPassword));
        user.setCreatedTime(new Date());
        List<User> dbUser = userMapper.getUserByUserNameOrStudentId(user.getUsername(), user.getStudentId());
        if (dbUser != null && dbUser.size() > 0) {
            throw new MyException(ErrorCode.USER_EXIST_ERROR);
        }

        userMapper.register(user);
        if (user.getId() == 0) {
            throw new MyException(ErrorCode.REGISTER_ERROR);
        }

        return randomPassword;
    }

    @Override
    public String resetPassword(PasswordForm passwordForm) {
        if (passwordForm.getId() == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        String newPassword = PassWordUtil.randomPassword(8);
        userMapper.resetPassword(passwordForm.getId(), AesUtil.encrypt(newPassword));
        return newPassword;
    }

    @Override
    public String updatePassword(PasswordForm passwordForm) {
        if (passwordForm.getNewPassword() == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //校验密码合法性
        boolean pwdMatches = passwordForm.getNewPassword().matches(Check.PASSWORD_REGEX);
        if (!pwdMatches) {
            throw new MyException(ErrorCode.PASSWORD_FORMAT_ERROR);
        }
        User user = UserUtil.getLoginUser();
        if (user.getId() == 0) {
            throw new MyException(ErrorCode.USER_NOT_FOUND_ERROR);
        }
        userMapper.resetPassword(String.valueOf(user.getId()), AesUtil.encrypt(passwordForm.getNewPassword()));
        return passwordForm.getNewPassword();
    }

    @Override
    public void disableUser(PasswordForm passwordForm) {
        if (passwordForm.getId() == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }
        //查询当前用户
        User user = userMapper.findUserById(passwordForm.getId());
        //用户不存在
        if (user.getId() == 0) {
            throw new MyException(ErrorCode.USER_NOT_FOUND_ERROR);
        }
        //存在就修改状态取反
        int status = user.getStatus();
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        userMapper.disableUser(user.getId(), status);
    }
}
