package com.wayfather.springbootfirststep.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/hello")
@RestController
public class HelloController {
    @RequestMapping("/index")
    public String index(){
        return "Hello";
    }
}
