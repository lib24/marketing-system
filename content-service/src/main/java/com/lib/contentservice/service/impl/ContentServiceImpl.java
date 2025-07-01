package com.lib.contentservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lib.common.dao.ContentDto;
import com.lib.common.pojo.Content;
import com.lib.contentservice.mapper.ContentMapper;
import com.lib.contentservice.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Page<ContentDto> pageContentWithCategory(int page, int size) {
        int total = contentMapper.countContent();
        int offset = (page - 1) * size;
        List<ContentDto> list = contentMapper.selectContentWithCategory(offset, size);

        Page<ContentDto> result = new Page<>();
        result.setCurrent(page);
        result.setSize(size);
        result.setTotal(total);
        result.setRecords(list);

        return result;
    }

    @Override
    public Content createContent(Content content) {
        contentMapper.insert(content);
        return content;
    }

    @Override
    public Content updateContent(Content content) {
        contentMapper.updateById(content);
        return content;
    }

    @Override
    public void deleteContent(Long id) {
        contentMapper.deleteById(id);
    }

    @Override
    public List<Content> findByUserId(Long userId) {
        return contentMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Content>()
                        .eq(Content::getUserId, userId)
        );
    }
}

