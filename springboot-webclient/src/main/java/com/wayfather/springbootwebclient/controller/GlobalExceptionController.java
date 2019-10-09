package com.wayfather.springbootwebclient.controller;

import com.wayfather.springbootwebclient.common.BusinessException;
import com.wayfather.springbootwebclient.common.HttpUtil;
import com.wayfather.springbootwebclient.common.ResultVo;
import com.wayfather.springbootwebclient.enums.ErrorStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.text.ParseException;

/**
 * @ClassName GlobalExceptionController
 * @Description TODO
 * @Author IBM
 * @Date 2019/10/8 11:16
 **/
@Slf4j

@RestControllerAdvice
public class GlobalExceptionController {
    /**
     * 处理 NoHandlerFoundException （请求资源不存在）异常. <br/>
     * 需配置 [spring.mvc.throw-exception-if-no-handler-found=true]
     * 需配置 [spring.resources.add-mappings=false]
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVo handleNotFoundException(NoHandlerFoundException e) {
        ResultVo result = new ResultVo(ErrorStatusEnum.NOT_FOUND.getStatus(),ErrorStatusEnum.NOT_FOUND.getMsg());
        log.info(e.getMessage());
        return result;
    }
    /**
     * 业务异常处理
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ExceptionHandler(BusinessException.class)
    public ResultVo handleBusinessException(BusinessException e) {
        ResultVo result = new ResultVo();
        if (e.getStatus() == null) {
            result.setCode(ErrorStatusEnum.ERROR.getStatus());
        } else {
            result.setCode(e.getStatus());
        }
        result.setMessage(e.getMsg());
        HttpUtil.sendServerChanInfo(e.getMsg());
        log.debug("BusinessException[status: {}, msg: {}]", e);
        return result;
    }


    /**
     * 参数异常处理
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ExceptionHandler(ParseException.class)
    public ResultVo handleParseException(ParseException e) {
        ResultVo result = new ResultVo(ErrorStatusEnum.PARAMS_ERROR.getStatus(),ErrorStatusEnum.PARAMS_ERROR.getMsg());
        log.debug(e.getMessage(), e);
        return result;
    }


    /**
     * 系统异常处理
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        ResultVo result = new ResultVo(ErrorStatusEnum.SYS_ERROR.getStatus(),ErrorStatusEnum.SYS_ERROR.getMsg());
        log.error(e.getMessage(), e);
        return result;
    }


    /**
     * 405异常，不支持当前请求方法
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ResultVo result = new ResultVo(ErrorStatusEnum.NOT_METHOD.getStatus(),ErrorStatusEnum.NOT_METHOD.getMsg());
        log.debug(result.getMessage(), e);
        return result;
    }


    /**
     * 400异常，参数解析失败
     *
     * @return com.work.shop.cloud.api.bean.ResultVo
     * @params [e]
     * @Author IBM
     * @Date 2019/10/8 11:16
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResultVo result = new ResultVo(ErrorStatusEnum.PARAMS_ERROR.getStatus(),ErrorStatusEnum.PARAMS_ERROR.getMsg());
        log.debug(result.getMessage(), e);
        return result;
    }
}
