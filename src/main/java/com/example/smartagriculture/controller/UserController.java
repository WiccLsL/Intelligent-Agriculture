package com.example.smartagriculture.controller;

import com.example.smartagriculture.entity.User;
import com.example.smartagriculture.mapper.UserMapper;
import com.example.smartagriculture.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    // 注册
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        log.info("register user: " + user.toString());
        // 检查用户名是否已存在
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名已存在");
        }

        // 新增用户
        userService.save(user);

        // 返回注册成功的响应
        return ResponseEntity.ok().body("{\"success\": true, \"message\": \"注册成功\"}");
    }


    //登录
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 在 UserService 中实现登录逻辑
        User user = userService.login(username, password);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    //新增和修改
    @PostMapping//插入请求
    public Integer save(@RequestBody User user){
        //新增或更新
        return userService.save(user);
    }

    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        List<User> all=userMapper.findAll();//查询接口
        return all;
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

    //分页查询接口
//@RequestParam接收 ?pageNum=1&pageSize=10
    @GetMapping("/page")//接口路径
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum=(pageNum-1)*pageSize;
        List<User> data = userMapper.selectPage(pageNum, pageSize);
        Integer total = userMapper.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


}
