package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import wang.gravity.bookmanagementsystem.pojo.UserLog;

@Mapper
public interface UserLogMapper {
    // 查询所有日志
    List<UserLog> getAllUserLog();

    //新增日志
    void createLog(UserLog userLog);

}
