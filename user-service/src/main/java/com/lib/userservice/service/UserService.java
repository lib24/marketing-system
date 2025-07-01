package com.lib.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lib.common.pojo.User;

public interface UserService extends IService<User> {

    User findByUsername(String username);
}
