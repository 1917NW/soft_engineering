package com.example.service;

import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.Result;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
public interface IUserService extends IService<User> {

    boolean login(User user, Result<Map<String, String>> result);
}