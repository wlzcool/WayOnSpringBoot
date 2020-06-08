package com.wayfather.springbootfastjson.Controller;

import com.wayfather.springbootfastjson.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IBM
 * @className TestController
 * @description TODO
 * @date 2020/6/8 14:35
 **/
@RestController()
@RequestMapping("test")
public class TestController {
    @RequestMapping("/getAllBrand")
    public UserBean getAllBrand(String token) {

        UserBean userBean =new UserBean();
        userBean.setUserName("wanglingzhi");
        return userBean;
    }
}
