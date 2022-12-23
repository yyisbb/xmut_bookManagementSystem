package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.UserLog;


@Repository
public interface UserLogService {
    List<UserLog> getAllUserLog();

    //创建日志
    void createLog(UserLog userLog);
}
