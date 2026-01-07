package com.lyq.pojo;

import lombok.Data;

//同理这个类用来封装分页查询参数
@Data
public class StudentQueryParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page;
    private Integer pageSize;
}
