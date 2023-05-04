package com.managementbyqing.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.managementbyqing.boot.common.Constant;

import com.managementbyqing.boot.controller.domain.LoginDTO1;
import com.managementbyqing.boot.entity.Leader;
import com.managementbyqing.boot.exception.ServiceException;
import com.managementbyqing.boot.mapper.LeaderMapper;
import com.managementbyqing.boot.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 无解亦有解
 * @Date: 2023/02/27/14:30
 * @Description:
 */
@Service
public class LeaderServiceImpl implements LeaderService {
    @Autowired
    LeaderMapper leaderMapper;

    @Override
    public LoginDTO1 login(Leader leader) {
        Leader dbLeader = null;
        try {
            dbLeader = leaderMapper.queryOneLeader();
        }catch (Exception e){
            throw new RuntimeException("数据库异常");
        }
        if (dbLeader == null){
            throw new ServiceException("未找到用户");
        }
        if (!dbLeader.getPassword().equals(leader.getPassword()) || !dbLeader.getUsername().equals(leader.getUsername())){
            throw new ServiceException("用户名或密码错误");
        }
        StpUtil.login(dbLeader.getId());
        StpUtil.getSession().set(Constant.LOGIN_USER_KEY,dbLeader);
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();
        return LoginDTO1.builder().leader(dbLeader).token(tokenValue).build();
    }

    @Override
    public Leader queryOneLeader() {
        return leaderMapper.queryOneLeader();
    }

}
