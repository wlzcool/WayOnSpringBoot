package com.wayfather.springbootmybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.wayfather.springbootmybatis.enums.PublishStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName BookDO
 * @Description TODO
 * @Author IBM
 * @Date 2019/9/30 10:14
 **/
@Data
public class BookDO {
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime publishtime;
    private PublishStatusEnum publishstatus;
    private String bookCover;
    private Boolean deleted;
}
