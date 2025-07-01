package com.lib.contentservice.rpc;

import com.lib.common.dao.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/info")
    UserDto getUserInfo(@RequestHeader("Authorization") String token);
}
