package com.lyq.mapper;

import com.lyq.pojo.EmpExpr;

import java.util.List;

public interface EmpExprMapper {

    //因为这里要批量插入员工的工作经历，一个员工可能有很多的工作经历封装在list里，所以用动态SQL，在EmpExprMapper.xml里配置
    void pilianginsert(List<EmpExpr> empExprs);
}
