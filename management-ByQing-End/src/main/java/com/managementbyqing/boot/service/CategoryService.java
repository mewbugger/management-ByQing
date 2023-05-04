package com.managementbyqing.boot.service;

import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Category;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:49
 * @Description:
 */
public interface CategoryService {
     List<Category> queryAllCategory();

     PageInfo<Category> queryAllCategoryByPage(int pageNum , int PageSize);

     void addCategory(Category category);

     void deleteCategory(int equipment_category_name);

     void updateCategory(Category category);
}
