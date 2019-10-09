package com.wayfather.springbootwebclient.common;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/8 14:27
 **/
public class BusinessException extends RuntimeException {

    private Integer status;

    private String msg;

    public BusinessException(String msg){
        setMsg(msg);
        setStatus(0);
    }
    public BusinessException(Integer status,String msg){
        setMsg(msg);
        setStatus(status);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
