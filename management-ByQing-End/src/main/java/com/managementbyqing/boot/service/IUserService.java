package com.managementbyqing.boot.service;

import com.managementbyqing.boot.controller.domain.LoginDTO;
import com.managementbyqing.boot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王乐岩
 * @since 2023-01-16
 */
public interface IUserService extends IService<User> {

    LoginDTO login(User user);

    void register(User user);

    void logout(String id);

    void saveUser(User user);
}
