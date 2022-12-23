package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.NoticeMapper;
import wang.gravity.bookmanagementsystem.pojo.Notice;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.pojo.UserNotice;
import wang.gravity.bookmanagementsystem.service.NoticeService;
import wang.gravity.bookmanagementsystem.utils.UserUtil;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void sendNotice(Notice notice) {
        if (notice == null) {
            throw new MyException(ErrorCode.PARAM_NULL_ERROR);
        }

        User user = UserUtil.getLoginUser();

        notice.setCreatedTime(new Date());
        notice.setManagerId(user.getId());
        noticeMapper.sendNotice(notice);
    }

    @Override
    public List<Notice> getUnPullNoticeList() {
        return noticeMapper.getUnPullNoticeList();
    }

    @Override
    public List<Notice> getAllPullNoticeList() {
        return noticeMapper.getAllPullNoticeList();
    }

    @Override
    public void userPullNoticeList(List<UserNotice> userNotice) {
        noticeMapper.userPullNoticeList(userNotice);
    }

    @Override
    public void userPullNotice(UserNotice userNotice) {
        noticeMapper.userPullNotice(userNotice);
    }

    @Override
    public void completeNotice(int noticeId) {
        noticeMapper.completeNotice(noticeId);
    }

    @Override
    public List<Notice> getNoticeByUserId(int userid) {
        return noticeMapper.getNoticeByUserId(userid);
    }

    @Override
    public void readNotice(int noticeId) {
        noticeMapper.readNotice(noticeId);
    }

    @Override
    public void readAllNotice(int recipientId) {
        noticeMapper.readAllNotice(recipientId);
    }
}
