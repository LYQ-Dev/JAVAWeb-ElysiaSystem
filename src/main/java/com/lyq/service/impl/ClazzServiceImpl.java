package com.lyq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyq.mapper.ClazzMapper;
import com.lyq.mapper.EmpExprMapper;
import com.lyq.mapper.EmpMapper;
import com.lyq.pojo.Clazz;
import com.lyq.pojo.ClazzQueryParam;
import com.lyq.pojo.Emp;
import com.lyq.pojo.PageResult;
import com.lyq.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Clazz> getAllClazzs() {
        List<Clazz> allClazzs=clazzMapper.getAllClazzs();
        return allClazzs;
    }

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2. 执行查询，返回的是当前查询页面的数据
        List<Clazz> clazzList = clazzMapper.fenyeGet(clazzQueryParam);
        //3. 封装分页结果，Page是一个提供的底层类
        Page<Clazz> p = (Page<Clazz>) clazzList;  //强转还原Page对象，获取完整分页数据
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteByIds(Integer ids) {
        clazzMapper.deleteByIds(ids);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        Clazz clazz=clazzMapper.getClazzById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
}
