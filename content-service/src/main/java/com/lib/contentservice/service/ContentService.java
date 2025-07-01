package com.lib.contentservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lib.common.dao.ContentDto;
import com.lib.common.pojo.Content;

import java.util.List;

public interface ContentService {
    Page<ContentDto> pageContentWithCategory(int page, int size);
    Content createContent(Content content);
    Content updateContent(Content content);
    void deleteContent(Long id);

    List<Content> findByUserId(Long userId);
}

