package com.managementbyqing.boot.service;


import com.managementbyqing.boot.controller.domain.LoginDTO1;
import com.managementbyqing.boot.entity.Leader;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/27/14:30
 * @Description:
 */
public interface LeaderService {

    LoginDTO1 login(Leader leader);

    Leader queryOneLeader();

}
