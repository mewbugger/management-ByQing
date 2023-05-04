package com.managementbyqing.boot.mapper;

import com.managementbyqing.boot.entity.Inspection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:36
 * @Description:
 */
@Repository
@Mapper
public interface InspectionMapper {
    public List<Inspection> queryAllInspection();

    public void addInspection(Inspection inspection);

    public void deleteInspection(int inspection_id);

    public void updateInspection(Inspection inspection);
}
