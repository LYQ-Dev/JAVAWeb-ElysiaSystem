package com.lyq.service;

import com.lyq.pojo.PageResult;
import com.lyq.pojo.Student;
import com.lyq.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult page(StudentQueryParam studentQueryParam);

    void delete(List<Integer> ids);

    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    //管理学生成绩
    void updateData(Student student);
}
