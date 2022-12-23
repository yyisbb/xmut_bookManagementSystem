package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;
import wang.gravity.bookmanagementsystem.mapper.UserLogMapper;
import wang.gravity.bookmanagementsystem.pojo.UserLog;
import wang.gravity.bookmanagementsystem.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;
    @Override
    public List<UserLog> getAllUserLog() {
        return userLogMapper.getAllUserLog();
    }

    @Override
    public void createLog(UserLog userLog) {
        userLogMapper.createLog(userLog);
        if (userLog.getId()==0){
            throw new MyException(ErrorCode.DB_ERROR);
        }
    }


}
