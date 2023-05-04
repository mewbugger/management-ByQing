package com.managementbyqing.boot.controller;

import com.managementbyqing.boot.common.Result;
import com.managementbyqing.boot.entity.Equipment;
import com.managementbyqing.boot.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:55
 * @Description:
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService   equipmentService;


    @GetMapping
    public Result queryAll(){
        return Result.success(equipmentService.queryAllEquipment());
    }

    @GetMapping("/page")
    public Result queryAllEquipment(@RequestParam Integer pageNum , @RequestParam Integer pageSize){
        return Result.success(equipmentService.queryAllEquipmentByPage(pageNum , pageSize));
    }

    @PostMapping
    public Result addEquipment(@RequestBody Equipment equipment){
        equipmentService.addEquipment(equipment);
        return Result.success();
    }

    @DeleteMapping("/{equipment_id}")
    public Result deleteEquipment(@PathVariable Integer equipment_id){
        equipmentService.deleteEquipment(equipment_id);
        return Result.success();
    }

    @PutMapping
    public Result updateEquipment(@RequestBody Equipment equipment){
        log.info("设备{}",equipment);
        equipmentService.updateEquipment(equipment);
        return Result.success();
    }
}
