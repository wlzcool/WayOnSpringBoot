package com.wayfather.springbootwebclient.enums;

public enum ErrorStatusEnum {
    SUCCESS(1, "操作成功")
    ,ERROR(0, "操作失败")
    ,SYS_ERROR(0, "系统异常，请联系管理员")
    ,LOGIN_OUT(9, "未登录或登录超时，请重新登录")
    ,NOT_STORE(8, "暂无店铺，不能进行此操作")
    ,NOT_FOUND(404,"请求的资源不存在")
    ,PARAMS_ERROR(500,"请求参数异常")
    ,NOT_METHOD(405,"不支持该请求方法")
    ,NO_PERMISSIONS(1007, "对不起，您无  {module} {type} 权限，不能进行此操作！")
    ;
    Integer status;
    String msg;

    ErrorStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

