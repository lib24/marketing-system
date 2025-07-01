package com.lib.contentservice.controller;

import com.lib.common.pojo.Category;
import com.lib.common.result.Result;
import com.lib.contentservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 查询所有分类，支持排序
    @GetMapping("/list")
    public Result<List<Category>> list() {
        return Result.ok(categoryService.listCategories());
    }

    // 添加分类
    @PostMapping("/create")
    public Result<Category> create(@RequestBody Category category) {
        return Result.ok(categoryService.createCategory(category));
    }

    // 修改分类
    @PutMapping("/update")
    public Result<Category> update(@RequestBody Category category) {
        return Result.ok(categoryService.updateCategory(category));
    }

    // 删除分类
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}

