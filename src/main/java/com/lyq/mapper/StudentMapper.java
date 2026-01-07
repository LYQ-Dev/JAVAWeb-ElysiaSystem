package com.lyq.mapper;

import com.lyq.pojo.Student;
import com.lyq.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    /*
    分页查询学生信息
     */
    List<Student> fenyeGet(StudentQueryParam studentQueryParam);

    /*
    批量删除学生信息
     */
    void delete(List<Integer> ids);

    /*
    添加学生信息
     */
    void add(Student student);

    /*
    查询学生信息byID
     */
    @Select("select * from student where id=#{id}")
    Student getInfo(Integer id);

    /*
    更新学生信息
     */
    void update(Student student);

    /*
    管理学生数据
     */
    void updateData(Student student);

    /*
    统计学生学历信息
     */
    @MapKey("degree")//实际这一行是不需要的，这样只是为了消除“假报错”
    List<Map> countStudentDegree();
}
