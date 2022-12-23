package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gravity.bookmanagementsystem.mapper.InfoMapper;
import wang.gravity.bookmanagementsystem.pojo.Info;
import wang.gravity.bookmanagementsystem.service.InfoService;


@Service
public class InfoServiceImpl  implements InfoService {
    @Autowired
    private InfoMapper infoMapper;
    @Override
    public Info getPlatformInfo() {
        return infoMapper.getPlatformInfo();
    }
}
