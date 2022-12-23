package wang.gravity.bookmanagementsystem.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.Notice;
import wang.gravity.bookmanagementsystem.pojo.User;
import wang.gravity.bookmanagementsystem.pojo.UserNotice;
import wang.gravity.bookmanagementsystem.service.NoticeService;
import wang.gravity.bookmanagementsystem.service.UserService;

@Configuration
@EnableScheduling
public class StaticScheduleTask {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        //查询未拉取的消息
        List<Notice> noticeList = noticeService.getUnPullNoticeList();
        List<UserNotice> notices = null;
        //遍历所有消息
        for (Notice notice : noticeList) {
            switch (notice.getType()) {
                case "all":
                    //发送全体
                    List<User> userList = userService.getAllUser();
                    notices = new LinkedList<>();
                    for (User user : userList) {
                        notices.add(new UserNotice(0, 0, notice.getId(), user.getId(), new Date()));
                    }
                    noticeService.userPullNoticeList(notices);
                    break;
                case "single":
                    //单用户发送
                    noticeService.userPullNotice(new UserNotice(0, 0, notice.getId(), Integer.parseInt(notice.getRecipientId()), new Date()));
                    break;
                case "multi":
                    //多用户发送
                    String[] recipientId = notice.getRecipientId().split(",");
                    notices = new LinkedList<>();
                    for (String id : recipientId) {
                        notices.add(new UserNotice(0, 0, notice.getId(), Integer.parseInt(id), new Date()));
                    }
                    noticeService.userPullNoticeList(notices);
                    break;
            }
            //最后将发送好的通知设置为已拉取状态
            noticeService.completeNotice(notice.getId());
        }
    }
}
