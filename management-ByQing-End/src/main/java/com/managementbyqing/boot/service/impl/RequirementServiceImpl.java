package com.managementbyqing.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.managementbyqing.boot.entity.Requirement;
import com.managementbyqing.boot.mapper.RequirementMapper;
import com.managementbyqing.boot.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:52
 * @Description:
 */
@Service
public class RequirementServiceImpl implements RequirementService {
    @Autowired
    RequirementMapper requirementMapper;

    @Override
    public List<Requirement> queryAllRequirement() {
        return requirementMapper.queryAllRequirement();
    }

    @Override
    public PageInfo<Requirement> queryAllRequirementByPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum,PageSize);
        List<Requirement> list = requirementMapper.queryAllRequirement();
        PageInfo<Requirement> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addRequirement(Requirement requirement) {
        requirementMapper.addRequirement(requirement);
    }

    @Override
    public void deleteRequirement(int requirement_id) {
        requirementMapper.deleteRequirement(requirement_id);
    }

    @Override
    public void updateRequirement(Requirement requirement) {
        requirementMapper.updateRequirement(requirement);
    }
}
