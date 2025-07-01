package com.lib.common.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private Long id;
    private String title;
    private String type;
    private String content;
    private String categoryName;  // 关联的分类名称
    private LocalDateTime createTime;
    private String username;
}

