package com.example.smartagriculture.service;


import com.example.smartagriculture.mapper.UserMapper;
import com.example.smartagriculture.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer save(User user) {
        if (user.getId() == null) {
            return userMapper.save(user);
        } else {
            return userMapper.update(user);
        }
    }

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
