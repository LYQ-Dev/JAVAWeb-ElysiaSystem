package com.lyq.service;

import com.lyq.pojo.Emp;
import com.lyq.pojo.EmpQueryParam;
import com.lyq.pojo.LoginInfo;
import com.lyq.pojo.PageResult;

import java.util.List;

public interface EmpService {
//    PageResult page(Integer page, Integer pageSize);

    PageResult page(EmpQueryParam empQueryParam);

    void addemp(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> getAllEmp();

    Integer countEmpbyDept(Integer id);

    LoginInfo login(Emp emp);
}
