package com.lyq.service;

import com.lyq.pojo.Clazz;
import com.lyq.pojo.ClazzQueryParam;
import com.lyq.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClazzService {



    List<Clazz> getAllClazzs();

    PageResult page(ClazzQueryParam clazzQueryParam);

    void deleteByIds(Integer ids);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void update(Clazz clazz);
}
