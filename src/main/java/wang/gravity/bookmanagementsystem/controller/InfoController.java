package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.pojo.Info;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.InfoService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    @AuthCheck
    @GetMapping("/getPlatformInfo")
    public ResultUtil<Info> getPlatformInfo() {
        return ResultUtil.success(infoService.getPlatformInfo());
    }
}
