package com.managementbyqing.boot.service.impl;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.managementbyqing.boot.common.Constant;
import com.managementbyqing.boot.controller.domain.LoginDTO;
import com.managementbyqing.boot.entity.User;
import com.managementbyqing.boot.exception.ServiceException;
import com.managementbyqing.boot.mapper.UserMapper;
import com.managementbyqing.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王乐岩
 * @since 2023-01-16
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public LoginDTO login(User user) {
        User dbUser=null;
        try {
            dbUser=getOne(new UpdateWrapper<User>().eq("username",user.getUsername()));

        }catch (Exception e){
            throw new RuntimeException("数据库异常");
        }
        if (dbUser ==  null){
            throw new ServiceException("未找到用户");
        }
        String securePass = SaSecureUtil.aesEncrypt(Constant.PASSWORD_KEY, user.getPassword());
        if (!securePass.equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        //登录
        StpUtil.login(dbUser.getId());
        StpUtil.getSession().set(Constant.LOGIN_USER_KEY,dbUser);
        String  tokenValue=StpUtil.getTokenInfo().getTokenValue();
        return LoginDTO.builder().user(dbUser).token(tokenValue).build();
    }

    @Override
    public void register(User user) {
        try {
            User dbUser=getOne(new UpdateWrapper<User>().eq("username",user.getUsername()));
            if (dbUser!=null){
                throw new ServiceException("用户已注册");
            }
            //设置昵称
            if (StrUtil.isBlank((user.getUser()))){
                String name=Constant.USER_NAME_PREFIX+ DateUtil.format(new Date(),Constant.DATE_RULE_YYYYMMDD)+ RandomUtil.randomString(4);
                user.setUser(name);
            }
            //如果用户没有设置密码，则为其设置默认密码
            if (StrUtil.isBlank(user.getPassword())){
                user.setPassword("123");
            }
            //加密
            user.setPassword(SaSecureUtil.aesEncrypt(Constant.PASSWORD_KEY,user.getPassword()));
            boolean saveSuccess= save(user);
            if (!saveSuccess){
                throw new RuntimeException("注册失败");
            }

        }catch (Exception e){
            throw new RuntimeException("数据库异常");
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            User dbUser=getOne(new UpdateWrapper<User>().eq("username",user.getUsername()));
            if (dbUser!=null){
                throw new ServiceException("用户已存在");
            }
            //设置昵称
            if (StrUtil.isBlank((user.getUser()))){
                String name=Constant.USER_NAME_PREFIX+ DateUtil.format(new Date(),Constant.DATE_RULE_YYYYMMDD)+ RandomUtil.randomString(4);
                user.setUser(name);
            }
            //如果用户没有设置密码，则为其设置默认密码
            if (StrUtil.isBlank(user.getPassword())){
                user.setPassword("123");
            }
            //加密
            user.setPassword(SaSecureUtil.aesEncrypt(Constant.PASSWORD_KEY,user.getPassword()));
            boolean saveSuccess= save(user);
            if (!saveSuccess){
                throw new RuntimeException("注册失败");
            }

        }catch (Exception e){
            throw new RuntimeException("数据库异常");
        }
    }

    @Override
    public void logout(String id) {
        //退出登录
        StpUtil.logout(id);
        log.info("用户{}退出成功",id);
    }
}
