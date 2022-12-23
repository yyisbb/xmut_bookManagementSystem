package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.pojo.UserLog;
import wang.gravity.bookmanagementsystem.service.UserLogService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;

@RestController
public class UserLogController {
    @Autowired
    private UserLogService userLogService;

    @AuthCheck
    @GetMapping("/getUserLogList")
    public ResultUtil<List<UserLog>> getUserLogList() {
        return ResultUtil.success(userLogService.getAllUserLog());
    }
}
