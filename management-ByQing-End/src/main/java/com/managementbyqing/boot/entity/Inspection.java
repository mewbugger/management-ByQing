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
 * @Date: 2023/02/20/2:18
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 巡检表
 */
public class Inspection {
    private int inspection_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8",locale = "zh")
    private Date inspection_date;
    private String inspection_employee;
    private String inspection_description;
    private int equipment_id;
    private String status;
}
