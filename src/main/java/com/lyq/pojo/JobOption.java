package com.lyq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {    //按照前端的接口要求定义的两个属性，该对象用于统计和返回所以员工工作经历的统计信息
    private List jobList;   //职位列表
    private List dataList;  //职位数量
}