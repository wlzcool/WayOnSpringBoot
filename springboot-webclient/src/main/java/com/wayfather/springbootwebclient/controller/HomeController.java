package com.wayfather.springbootwebclient.controller;

import com.wayfather.springbootwebclient.common.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HomeController
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/8 11:11
 **/
@RequestMapping("/home")
@RestController
public class HomeController {
    @RequestMapping("index")
    public Boolean index (){
        return true;
    }
    @RequestMapping("error")
    public Boolean error(){
        throw new BusinessException(1,"测试server酱");
    }
}
