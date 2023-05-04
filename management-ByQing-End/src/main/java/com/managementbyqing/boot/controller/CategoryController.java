package com.managementbyqing.boot.controller;

import com.managementbyqing.boot.common.Result;
import com.managementbyqing.boot.entity.Category;
import com.managementbyqing.boot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:55
 * @Description:
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result queryCategory(){
        List<Category> list = categoryService.queryAllCategory();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result queryAllCategory(@RequestParam Integer pageNum , @RequestParam Integer pageSize){
        return Result.success(categoryService.queryAllCategoryByPage(pageNum , pageSize));
    }

    @PostMapping
    public Result addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return Result.success();
    }

    @DeleteMapping("/{equipment_category_id}")
    public Result deleteCategory(@PathVariable Integer equipment_category_id){
        categoryService.deleteCategory(equipment_category_id);
        return Result.success();
    }

    @PutMapping
    public Result updateCategory(@RequestBody  Category category){


        categoryService.updateCategory(category);
        return Result.success();
    }
}
