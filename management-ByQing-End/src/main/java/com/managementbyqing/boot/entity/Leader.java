package com.managementbyqing.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/27/14:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 管理员表
 */
public class Leader {

    private int id;

    private String username;

    private String password;

    private String user;
}
