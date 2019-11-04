package com.wayfather.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wayfather.springbootmybatisplus.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/18 16:30
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

}