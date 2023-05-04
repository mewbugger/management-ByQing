package com.managementbyqing.boot.controller;
import com.managementbyqing.boot.common.Result;
import com.managementbyqing.boot.entity.Inspection;
import com.managementbyqing.boot.service.InspectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:55
 * @Description:
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/inspection")
public class InspectionController {

    @Autowired
    InspectionService inspectionService;


    @GetMapping
    public Result queryAll(){
        return Result.success(inspectionService.queryAllInspection());
    }

    @GetMapping("/page")
    public Result queryAllInspection(@RequestParam Integer pageNum , @RequestParam Integer pageSize){
        return Result.success(inspectionService.queryAllEquipmentByPage(pageNum,pageSize));
    }

    @PostMapping
    public Result addInspection(@RequestBody  Inspection inspection){
        inspectionService.addInspection(inspection);
        return Result.success();
    }

    @DeleteMapping("/{inspection_id}")
    public Result deleteInspection(@PathVariable Integer inspection_id){
        inspectionService.deleteInspection(inspection_id);
        return Result.success();
    }

    @PutMapping
    public Result updateInspection(@RequestBody Inspection inspection){
        inspectionService.updateInspection(inspection);
        return Result.success();
    }
}
