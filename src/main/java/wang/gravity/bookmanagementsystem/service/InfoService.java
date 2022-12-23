package wang.gravity.bookmanagementsystem.service;

import org.springframework.stereotype.Repository;

import wang.gravity.bookmanagementsystem.pojo.Info;

@Repository
public interface InfoService {
    Info getPlatformInfo();
}
