package com.lib.contentservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lib.common.dao.ContentDto;
import com.lib.common.dao.UserDto;
import com.lib.common.pojo.Content;
import com.lib.common.result.Result;
import com.lib.contentservice.rpc.UserClient;
import com.lib.contentservice.service.ContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private UserClient userClient;

    // 分页查询营销内容
    @GetMapping("/list")
    public Result<Page<ContentDto>> list(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return Result.ok(contentService.pageContentWithCategory(page, size));
    }

    // 创建内容
    @PostMapping("/create")
    public Result<Content> create(@RequestBody Content content) {
        return Result.ok(contentService.createContent(content));
    }

    // 更新内容
    @PutMapping("/update")
    public Result<Content> update(@RequestBody Content content) {
        return Result.ok(contentService.updateContent(content));
    }

    // 删除内容
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        contentService.deleteContent(id);
    }


    @GetMapping("/user/{userId}/{token}")
    public Result<List<ContentDto>> getUserContents(@PathVariable String token,
                                            @PathVariable Long userId) {
        // 查询内容
        List<Content> contentList = contentService.findByUserId(userId);

        token = "Bearer " + token;
        // 查询用户信息
        UserDto user = userClient.getUserInfo(token);

        // 封装内容+用户名
        return Result.ok(contentList.stream().map(content -> {
            ContentDto dto = new ContentDto();
            BeanUtils.copyProperties(content, dto);
            dto.setUsername(user.getUsername());
            return dto;
        }).collect(Collectors.toList()));
    }
}
