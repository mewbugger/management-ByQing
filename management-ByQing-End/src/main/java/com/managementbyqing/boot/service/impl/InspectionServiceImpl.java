package com.managementbyqing.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Inspection;
import com.managementbyqing.boot.mapper.InspectionMapper;
import com.managementbyqing.boot.service.InspectionService;
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
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    InspectionMapper inspectionMapper;

    @Override
    public List<Inspection> queryAllInspection() {
        return inspectionMapper.queryAllInspection();
    }

    @Override
    public PageInfo<Inspection> queryAllEquipmentByPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum,PageSize);
        List<Inspection> list = inspectionMapper.queryAllInspection();
        PageInfo<Inspection> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addInspection(Inspection inspection) {
        inspectionMapper.addInspection(inspection);
    }

    @Override
    public void deleteInspection(int inspection_id) {
        inspectionMapper.deleteInspection(inspection_id);
    }

    @Override
    public void updateInspection(Inspection inspection) {
        inspectionMapper.updateInspection(inspection);
    }
}
