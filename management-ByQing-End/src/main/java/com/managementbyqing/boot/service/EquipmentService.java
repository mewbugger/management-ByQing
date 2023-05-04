package com.managementbyqing.boot.service;

import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Equipment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:50
 * @Description:
 */
public interface EquipmentService {
    List<Equipment> queryAllEquipment();

    PageInfo<Equipment> queryAllEquipmentByPage(int pageNum , int PageSize);

    void addEquipment(Equipment equipment);

    void deleteEquipment(int equipment_id);

    void updateEquipment(Equipment equipment);
}
