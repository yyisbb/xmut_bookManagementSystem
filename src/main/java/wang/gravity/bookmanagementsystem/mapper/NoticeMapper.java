package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Notice;
import wang.gravity.bookmanagementsystem.pojo.UserNotice;

@Mapper
public interface NoticeMapper {
    void sendNotice(Notice notice);

    List<Notice> getUnPullNoticeList();

    List<Notice> getAllPullNoticeList();


    void userPullNoticeList(@Param("userNoticeList") List<UserNotice> userNoticeList);

    void userPullNotice(UserNotice userNotice);

    void completeNotice(int noticeId);

    List<Notice> getNoticeByUserId(int userid);

    void readNotice(int noticeId);
    void readAllNotice(int recipientId);
}
