package com.managementbyqing.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Equipment;
import com.managementbyqing.boot.mapper.EquipmentMapper;
import com.managementbyqing.boot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:51
 * @Description:
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> queryAllEquipment() {
        return equipmentMapper.queryAllEquipment();
    }

    @Override
    public PageInfo<Equipment> queryAllEquipmentByPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum , PageSize);
        List<Equipment> list = equipmentMapper.queryAllEquipment();
        PageInfo<Equipment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipmentMapper.addEquipment(equipment);
    }

    @Override
    public void deleteEquipment(int equipment_id) {
        equipmentMapper.deleteEquipment(equipment_id);
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        equipmentMapper.updateEquipment(equipment);
    }
}
