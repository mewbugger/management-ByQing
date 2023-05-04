package com.managementbyqing.boot.mapper;

import com.managementbyqing.boot.entity.Requirement;
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
@Mapper
@Repository
public interface RequirementMapper {
    public List<Requirement> queryAllRequirement();

    public void addRequirement(Requirement requirement);

    public void deleteRequirement(int requirement_id);

    public void updateRequirement(Requirement requirement);
}
