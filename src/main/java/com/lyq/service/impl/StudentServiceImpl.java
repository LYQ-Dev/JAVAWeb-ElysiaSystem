package com.lyq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyq.mapper.StudentMapper;
import com.lyq.pojo.PageResult;
import com.lyq.pojo.Student;
import com.lyq.pojo.StudentQueryParam;
import com.lyq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        //2. 执行查询，返回的是当前查询页面的数据
        List<Student> studentList = studentMapper.fenyeGet(studentQueryParam);
        //3. 封装分页结果，Page是一个提供的底层类
        Page<Student> p = (Page<Student>) studentList;  //强转还原Page对象，获取完整分页数据
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void updateData(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateData(student);
    }

}

