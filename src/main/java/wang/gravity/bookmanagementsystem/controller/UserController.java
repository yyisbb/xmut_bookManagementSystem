package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.annotation.PassToken;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.dto.DeleteForm;
import wang.gravity.bookmanagementsystem.dto.PasswordForm;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.pojo.UserLog;
import wang.gravity.bookmanagementsystem.service.UserLogService;
import wang.gravity.bookmanagementsystem.service.UserService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private UserLogService userLogService;

    @GetMapping("/getAllUser")
    public ResultUtil<List<User>> getAllUser() {
        return ResultUtil.success(userService.getAllUser());
    }

    @GetMapping("/getUserInfo")
    public ResultUtil<User> getUserInfo() {
        User user = UserUtil.getLoginUser();
        return ResultUtil.success(user);
    }
    @AuthCheck
    @PostMapping("/deleteUsers")
    @UserLogAnnotation(operation = UserLogConst.DELETE_USER)
    public ResultUtil<String> deleteUsers(@RequestBody DeleteForm deleteForm) {
        userService.deleteUsers(deleteForm.getIds());
        return ResultUtil.success();
    }
    @AuthCheck
    @PostMapping("/addUser")
    @UserLogAnnotation(operation = UserLogConst.ADD_USER)
    public ResultUtil<String> addUser(@RequestBody User user) {
        //添加学生
        return ResultUtil.success(userService.addStudent(user));
    }


    @PassToken
    @PostMapping("/login")
    public ResultUtil<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return ResultUtil.success(token);
    }

    @PassToken
    @PostMapping("/register")
    public ResultUtil<String> register(@RequestBody User user) {
        //用户注册
        userService.register(user);
        return ResultUtil.success(ErrorCode.SUCCESS.getMessage());
    }

    @AuthCheck
    @PostMapping("/resetPassword")
    @UserLogAnnotation(operation = UserLogConst.RESET_PASSWORD)
    public ResultUtil<String> resetPassword(@RequestBody PasswordForm passwordForm) {
        //重置密码
        String newPassword = userService.resetPassword(passwordForm);
        return ResultUtil.success(newPassword);
    }
    @AuthCheck
    @PostMapping("/updatePassword")
    @UserLogAnnotation(operation = UserLogConst.UPDATE_PASSWORD)
    public ResultUtil<String> updatePassword(@RequestBody PasswordForm passwordForm) {
        //修改密码
        String newPassword = userService.updatePassword(passwordForm);
        return ResultUtil.success(newPassword);
    }
    @AuthCheck
    @PostMapping("/disableUser")
    @UserLogAnnotation(operation = UserLogConst.DISABLE_USER)
    public ResultUtil<String> disableUser(@RequestBody PasswordForm passwordForm) {
        //禁用/开启状态
        userService.disableUser(passwordForm);
        return ResultUtil.success();
    }
}
