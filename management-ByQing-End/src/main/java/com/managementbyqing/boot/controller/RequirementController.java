package com.managementbyqing.boot.controller;

import com.managementbyqing.boot.common.Result;
import com.managementbyqing.boot.entity.Requirement;
import com.managementbyqing.boot.service.RequirementService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:56
 * @Description:
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/requirement")
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @GetMapping("/page")
    public Result queryAllRequirement(@RequestParam Integer pageNum , @RequestParam Integer pageSize){
        return Result.success(requirementService.queryAllRequirementByPage(pageNum,pageSize));
    }



    @PostMapping
    public Result addRequirement(@RequestBody Requirement requirement){
        requirementService.addRequirement(requirement);
        return Result.success();
    }

    @DeleteMapping("/{requirement_id}")
    public Result deleteRequirement(@PathVariable Integer requirement_id){
        requirementService.deleteRequirement(requirement_id);
        return Result.success();
    }

    @PutMapping
    public Result updateRequirement(@RequestBody Requirement requirement){
        requirementService.updateRequirement(requirement);
        return Result.success();
    }

}
