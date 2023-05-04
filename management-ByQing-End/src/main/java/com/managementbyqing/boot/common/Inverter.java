package com.managementbyqing.boot.common;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/16/11:37
 * @Description:
 */
@Data
public class Inverter implements Serializable {

    @ExcelProperty(index = 0 , value = "时间")
    private String inspectionTime;

    @ExcelProperty(index = 1 , value = "逆变器编号")
    private int id;

    @ExcelProperty(index = 5 , value = "有功")
    private float power;

}
