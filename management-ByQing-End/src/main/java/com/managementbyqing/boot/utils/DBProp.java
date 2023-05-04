package com.managementbyqing.boot.utils;

import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/01/16/21:15
 * @Description:
 */

@Data
@Builder
public class DBProp {
    private String url;
    private String username;
    private String password;
}