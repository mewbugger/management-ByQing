package com.managementbyqing.boot.mapper;

import com.managementbyqing.boot.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:34
 * @Description:
 */
@Mapper
@Repository
public interface CategoryMapper {
    public List<Category> queryAllCategory();

    public void addCategory(Category category);

    public void deleteCategory(int equipment_category_name);

    public void updateCategory(Category category);
}
