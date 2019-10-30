package com.wayfather.springbootmybatisplus.entity;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/18 16:29
 **/
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
