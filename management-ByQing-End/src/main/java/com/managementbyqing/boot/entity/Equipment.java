package com.managementbyqing.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/20/2:12
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 设备表
 */
public class Equipment {
    private int equipment_id;
    private String equipment_name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8",locale = "zh")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date installment_date;
    private String equipment_description;
    private String equipment_address;
    private int equipment_category_id;
}
