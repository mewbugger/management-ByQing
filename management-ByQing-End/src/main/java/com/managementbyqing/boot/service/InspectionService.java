package com.managementbyqing.boot.service;

import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Inspection;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:51
 * @Description:
 */
public interface InspectionService {

    List<Inspection> queryAllInspection();

    PageInfo<Inspection> queryAllEquipmentByPage(int pageNum , int PageSize);

    void addInspection(Inspection inspection);

    void deleteInspection(int inspection_id);

    void updateInspection(Inspection inspection);
}
