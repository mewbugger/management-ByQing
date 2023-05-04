package com.managementbyqing.boot.service;

import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Requirement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:51
 * @Description:
 */
public interface RequirementService {

    List<Requirement> queryAllRequirement();

    PageInfo<Requirement> queryAllRequirementByPage(int pageNum , int PageSize);

    void addRequirement(Requirement requirement);

    void deleteRequirement(int requirement_id);

    void updateRequirement(Requirement requirement);
}
