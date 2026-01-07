package com.lyq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//这个类是用来封装分页查询的结果
public class PageResult <T>{    //用泛型占位参数写，可以满足emp\dept等多种对象的封装返回
    private Long total; //总记录数
    private List<T> rows; //当前页数据列表
}
