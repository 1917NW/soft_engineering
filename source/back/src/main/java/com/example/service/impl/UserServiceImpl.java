package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.JwtUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean login(User user, Result<Map<String, String>> result) {
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();

        q.eq(User::getUserIdNumber, user.getUserIdNumber());
        q.eq(User::getUserPassword, user.getUserPassword());
        User loginUser = baseMapper.selectOne(q);

        if(loginUser != null) {
            result.getData().put("role", loginUser.getUserRole().toString());
            result.getData().put("token",jwtUtil.createToken(loginUser));
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean register(User user, Result<Map<String, String>> result) {
        String s = jwtUtil.createToken(user);
        System.out.println(s);
        result.getData().put("token",s);
        return true;
    }
}
