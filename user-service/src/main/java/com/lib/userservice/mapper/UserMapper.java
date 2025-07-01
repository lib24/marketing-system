package com.lib.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lib.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
