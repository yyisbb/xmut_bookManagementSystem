package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Notice;
import wang.gravity.bookmanagementsystem.pojo.UserNotice;

@Repository
public interface NoticeService {
    void sendNotice(Notice notice);

    List<Notice> getUnPullNoticeList();

    List<Notice> getAllPullNoticeList();

    void userPullNoticeList(List<UserNotice> userNotice);

    void userPullNotice(UserNotice userNotice);

    void completeNotice(int noticeId);

    List<Notice> getNoticeByUserId(int userid);

    void readNotice(int noticeId);
    void readAllNotice(int recipientId);
}
