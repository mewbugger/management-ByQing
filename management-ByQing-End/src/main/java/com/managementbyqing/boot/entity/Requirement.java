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
 * @Date: 2023/02/20/2:25
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 维修表
 */
public class Requirement {
    private int requirement_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8",locale = "zh")
    private Date requirement_date;
    private String requirement_description;
    private int inspection_id;
    private String requirement_employee;
    private String requirement_result;
}
