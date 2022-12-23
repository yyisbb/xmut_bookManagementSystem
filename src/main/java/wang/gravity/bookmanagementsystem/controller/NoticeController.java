package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import wang.gravity.bookmanagementsystem.annotation.AuthCheck;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.dto.PasswordForm;
import wang.gravity.bookmanagementsystem.pojo.Notice;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.service.NoticeService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;


    @AuthCheck
    @PostMapping("/sendNotice")
    @UserLogAnnotation(operation = UserLogConst.SEND_NOTICE)
    public ResultUtil<String> sendNotice(@RequestBody Notice notice) {
        noticeService.sendNotice(notice);
        return ResultUtil.success();
    }

    @AuthCheck
    @GetMapping("/getAllPullNoticeList")
    public ResultUtil<List<Notice>> getAllPullNoticeList() {
        return ResultUtil.success(noticeService.getAllPullNoticeList());
    }

    @GetMapping("/getNotice")
    public ResultUtil<List<Notice>> getNotice() {
        User user = UserUtil.getLoginUser();
        return ResultUtil.success(noticeService.getNoticeByUserId(user.getId()));
    }


    @GetMapping("/readNotice")
    public ResultUtil<List<Notice>> readNotice(int id) {
        noticeService.readNotice(id);
        return ResultUtil.success();
    }

    @GetMapping("/readAllNotice")
    public ResultUtil<List<Notice>> readAllNotice() {
        User user = UserUtil.getLoginUser();
        noticeService.readAllNotice(user.getId());
        return ResultUtil.success();
    }




}
