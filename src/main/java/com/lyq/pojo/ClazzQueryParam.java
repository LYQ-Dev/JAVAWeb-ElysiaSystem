package com.lyq.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
//同理为了对接前端班级分页查询接口请求参数，定义一个实体类来接收数据
public class ClazzQueryParam {
    private String name;
    private LocalDate begin;
    private LocalDate end;
    private Integer page ;
    private Integer pageSize ;
}
