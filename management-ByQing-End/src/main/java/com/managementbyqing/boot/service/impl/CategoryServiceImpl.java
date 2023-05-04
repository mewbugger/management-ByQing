package com.managementbyqing.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Category;
import com.managementbyqing.boot.mapper.CategoryMapper;
import com.managementbyqing.boot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:49
 * @Description:
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<Category> queryAllCategory() {
        return categoryMapper.queryAllCategory();
    }

    @Override
    public PageInfo<Category> queryAllCategoryByPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum , PageSize);
        List<Category> list = categoryMapper.queryAllCategory();
        log.info("所查询的列表{}" , list);
        PageInfo<Category> pageInfo = new PageInfo<Category>(list);
        return pageInfo;
    }

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    @Override
    public void deleteCategory(int equipment_category_name) {
        categoryMapper.deleteCategory(equipment_category_name);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }
}
