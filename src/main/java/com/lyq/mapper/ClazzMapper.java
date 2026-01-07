package com.lyq.mapper;

import com.lyq.pojo.Clazz;
import com.lyq.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClazzMapper {
    /*
    查询所有班级信息
     */
    @Select("SELECT * from clazz")
    List<Clazz> getAllClazzs();

    /*
    分页查询班级信息，同理在文件中配置SQL语句，在ClazzMapper.xml文件中配置
     */
    List<Clazz> fenyeGet(ClazzQueryParam clazzQueryParam);

    /*
    批量删除班级信息
     */
    void deleteByIds(Integer ids);

    /*
    添加班级
     */
    void addClazz(Clazz clazz);

    /*
    根据id查询班级
     */
    @Select("SELECT * FROM clazz where id=#{id}")
    Clazz getClazzById(Integer id);

    /*
    更新班级信息
     */
    void update(Clazz clazz);
}
