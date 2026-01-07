package com.lyq.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyq.mapper.EmpMapper;
import com.lyq.pojo.*;
import com.lyq.mapper.EmpExprMapper;
import com.lyq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

//    原始分页查询的代码
//    @Override
//    public PageResult page(Integer page, Integer pageSize) {
//        Long count = empMapper.count(); //获取页面总数
//        Integer nowpage=(page-1)*pageSize;  //获取当前页面的起始序号
//        List<Emp> emps = empMapper.allEmpAndDept(nowpage,pageSize); //获取当前页面的数据
//
//        //返回的形式是封装在PageResult的，可以同时返回总记录数和当前页面数据列表
//        return new PageResult<Emp>(count,emps); //用Emp是为了传入这个泛型给其中定义的List类型的rows，作为Emp类的数据列表返回
//
//    }

    /*@Override
    public PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(page, pageSize);
        //2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        //3. 封装分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(), p.getResult());
    }*/

    public PageResult page(EmpQueryParam empQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2. 执行查询，返回的是当前查询页面的数据
        List<Emp> empList = empMapper.fenyeGet(empQueryParam);
        //3. 封装分页结果，Page是一个提供的底层类
        Page<Emp> p = (Page<Emp>) empList;  //强转还原Page对象，获取完整分页数据
        return new PageResult(p.getTotal(), p.getResult());
    }


    @Transactional
    /*
    //我们一般会在业务层当中来控制事务，因为在业务层当中，一个业务功能可能会包含多个数据访问的操作。
    在业务层来控制事务，我们就可以将多个数据访问操作控制在一个事务范围内。@Transactional保证事务
     */
    @Override
    public void addemp(Emp emp) {
        //1.保存员工的基本信息，初始化一下操作时间相关（前端不会传入）
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        // 1. 执行新增，MyBatis通过ID回显把数据库自增的ID赋值给emp.getId()
        empMapper.addemp(emp);

        //2.保存员工的工作经历
        // 2. 这里的emp.getId()就是回显得到的！如果没有回显，这里ID是null，工作经历会关联失败
        List<EmpExpr> empExprs = emp.getEmpExprs();
        if(!CollectionUtils.isEmpty(empExprs)){
            //遍历集合，用emp中拿到的员工id为empId赋值
            empExprs.forEach(empExpr->{
                empExpr.setEmpId(emp.getId());  //设置empID为员工ID
            });
            empExprMapper.pilianginsert(empExprs);  //在mapper层添加员工经历实现
        }

    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        //删除ID对应员工
        empMapper.deleteByIds(ids);
        //连带删除对应员工工作经历
        empMapper.deleteExprByIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        //根据ID查询回显
        Emp emp=empMapper.getInfo(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
        //2. 根据员工ID删除员工的工作经历信息 【删除老的】。由于一对多的特殊性，如果用update更新经历表会引起很多业务问题，所以直接采用删旧添新
        empMapper.deleteExprByIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】,直接复制前面的添加工作经历代码
        List<EmpExpr> empExprs = emp.getEmpExprs();
        if(!CollectionUtils.isEmpty(empExprs)){
            empExprs.forEach(empExpr->{
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.pilianginsert(empExprs);
        }
    }

    @Override
    public List<Emp> getAllEmp() {
        List<Emp> allEmps=empMapper.getAllEmps();
        return allEmps;
    }

    @Override
    public Integer countEmpbyDept(Integer deptid) {
        return empMapper.getNumByDeptId(deptid);

    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), null);
            return loginInfo;
        }
        return null;
    }


}
