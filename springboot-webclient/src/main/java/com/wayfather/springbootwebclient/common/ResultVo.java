package com.wayfather.springbootwebclient.common;

import lombok.Data;

/**
 * @ClassName ResultVo
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/8 11:17
 **/
@Data
public class ResultVo {
    private Integer code;
    private String message;

    public ResultVo() {
    }

    public ResultVo(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }
}
