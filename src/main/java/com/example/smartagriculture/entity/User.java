package com.example.smartagriculture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 代替 Getter 和 Setter
@NoArgsConstructor // 创建无参构造
@AllArgsConstructor // 创建有参构造
public class User {

    private Integer id;  // 对应数据库中的 id 列

    private String username;  // 对应数据库中的 username 列

    private String password;  // 对应数据库中的 password 列

    private String nickname;  // 对应数据库中的 nickname 列

    private String email;  // 对应数据库中的 email 列

    private String phone;  // 对应数据库中的 phone 列

    private String address;  // 对应数据库中的 address 列
}
