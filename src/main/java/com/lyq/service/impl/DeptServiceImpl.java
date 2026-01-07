package com.lyq.service.impl;

import com.lyq.mapper.DeptMapper;
import com.lyq.pojo.Dept;
import com.lyq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;  //因为要用Mapper中的findAll函数，所以得注入生成一个对象

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    public void deleteById(Integer id){

        deptMapper.deleteById(id);
    }

    public void save(Dept dept){
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.addByApartment(dept);
    }

    public Dept getById(Integer id){
        return deptMapper.getById(id);
    }

    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //保存部门
        deptMapper.update(dept);
    }

}
