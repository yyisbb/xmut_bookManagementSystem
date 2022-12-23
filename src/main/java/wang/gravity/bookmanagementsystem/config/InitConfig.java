package wang.gravity.bookmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import wang.gravity.bookmanagementsystem.service.UserService;


@Component
@Order(value = 1)
public class InitConfig implements ApplicationRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //创建超级管理员账号
        userService.initAdmin();
    }
}
