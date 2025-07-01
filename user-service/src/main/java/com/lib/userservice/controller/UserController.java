package com.lib.userservice.controller;

import com.lib.common.pojo.User;
import com.lib.common.result.Result;
import com.lib.common.util.JwtUtil;
import com.lib.userservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 注册
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        boolean saved = userService.save(user);
        return Result.ok(saved ? "注册成功" : "注册失败");
    }

    // 登录
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        User dbUser = userService.findByUsername(user.getUsername());
        Map<String, Object> result = new HashMap<>();
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            String token = com.lib.common.util.JwtUtil.generateToken(dbUser.getId(), dbUser.getUsername());
            result.put("token", token);
            result.put("msg", "登录成功");
        } else {
            result.put("msg", "用户名或密码错误");
        }
        return Result.ok(result);
    }

    // Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJseWwiLCJ1c2VySWQiOjEsImlhdCI6MTc1MTM2MDM2OCwiZXhwIjoxNzUxMzgxOTY4fQ.uRxAl3eZuqdAEchrsswNdHiBMbCrTGgsStqqYylqwQI
    // 查询当前登录用户信息
    @GetMapping("/info")
    @SecurityRequirement(name = "bearerAuth")  // 只有这个接口需要token
    public Result<User> info(@RequestHeader("Authorization") String token) {
        String username = JwtUtil.parseUsername(token);
        System.out.println("Authorization header: " + token);
        return Result.ok(userService.findByUsername(username));
    }
}
