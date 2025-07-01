package com.lib.contentservice.service;

import com.lib.common.pojo.Category;

import java.util.List;

// CategoryService.java
public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}

