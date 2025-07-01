package com.lib.common.pojo;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user") // 指定数据库表名
public class User {

    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
