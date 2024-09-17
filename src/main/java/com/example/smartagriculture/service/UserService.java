package com.example.smartagriculture.service;

import com.example.smartagriculture.entity.User;

public interface UserService {

    Integer save(User user);

    User login(String username, String password);

    User findByUsername(String username);
}
