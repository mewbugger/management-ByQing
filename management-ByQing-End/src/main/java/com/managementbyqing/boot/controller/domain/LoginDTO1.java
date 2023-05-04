package com.managementbyqing.boot.controller.domain;

import com.managementbyqing.boot.entity.Leader;
import com.managementbyqing.boot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/01/26/20:32
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO1 implements Serializable {
    private static final long serialVersionUID=1L;

    private Leader leader;


    private String token;
}
