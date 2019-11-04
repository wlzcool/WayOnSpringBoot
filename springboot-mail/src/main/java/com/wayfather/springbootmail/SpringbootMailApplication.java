package com.wayfather.springbootmail;

import cn.hutool.Hutool;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.wayfather.springbootmail.common.SecureUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMailApplication {

    public static void main(String[] args) {
        System.out.println(SecureUtil.sha256("test"));
        System.out.println(SecureUtils.String2SHA256StrJava("test"));
        System.out.println(IdUtil.createSnowflake(12,23).nextId());
        SpringApplication.run(SpringbootMailApplication.class, args);
    }

}
