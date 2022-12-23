package wang.gravity.bookmanagementsystem.mapper;

import org.apache.ibatis.annotations.Mapper;

import wang.gravity.bookmanagementsystem.pojo.Info;



@Mapper
public interface InfoMapper {
    Info getPlatformInfo();
}
