package com.managementbyqing.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:17
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 设备类别表
 */
public class Category {
    private int equipment_category_id;
    private String equipment_category_name;
}
