package com.example.smartagriculture.mapper;
import com.example.smartagriculture.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    Integer save(User user);
    Integer update(User user);
    Integer deleteById(Integer id);
    List<User> selectPage(Integer pageNum, Integer pageSize);
    Integer selectTotal();
    User findByUsername(String username);
}