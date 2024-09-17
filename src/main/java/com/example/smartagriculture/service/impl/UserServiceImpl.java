package com.example.smartagriculture.service.impl;

import com.example.smartagriculture.mapper.UserMapper;
import com.example.smartagriculture.entity.User;
import com.example.smartagriculture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer save(User user) {
        if (user.getId() == null) {
            return userMapper.save(user);
        } else {
            return userMapper.update(user);
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
