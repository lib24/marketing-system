package com.lib.contentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lib.common.dao.ContentDto;
import com.lib.common.pojo.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// ContentMapper.java
@Mapper
public interface ContentMapper extends BaseMapper<Content> {

    // 自定义SQL实现内容与分类的关联分页查询
    @Select("SELECT c.id, c.title, c.type, c.content, cat.name AS category_name, c.create_time " +
            "FROM content c LEFT JOIN category cat ON c.category_id = cat.id " +
            "ORDER BY c.create_time DESC " +
            "LIMIT #{offset}, #{size}")
    List<ContentDto> selectContentWithCategory(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM content")
    int countContent();

    List<Content> selectByUserId(@Param("userId") Long userId);
}

