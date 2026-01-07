package com.lyq.service;

import com.lyq.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface DeptService {
    //查询全部部门
    List<Dept> findAll();

    void deleteById(Integer id);

    void save(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);


}
