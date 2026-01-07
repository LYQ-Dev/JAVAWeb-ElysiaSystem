package com.lyq.controller;

import com.lyq.mapper.EmpMapper;
import com.lyq.pojo.*;
import com.lyq.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    查询所有员工
     */
    @GetMapping("/allEmps")
    public Result getAllEmp(){
        List<Emp> allEmps=empService.getAllEmp();
        return Result.success(allEmps);
    }

    /*
    分页查询员工相关
     */
    @GetMapping //这里就可以用empQueryParam封装好的结构来代替一个个的传入参数
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    //@RequestParam是设置默认参数，前端传入的两个信息：当前页面page和每页展示的记录数量pageSize

    /*
    添加员工信息操作
     */
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){            //@RequestBody能让传入的JSON参数自动封装到Emp对象
        empService.addemp(emp);
        return Result.success();
    }

    /*
    删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        empService.deleteByIds(ids);
        return Result.success();
    }


    /*
    在进行修改员工信息的时候，我们首先先要根据员工的ID查询员工的详细信息用于页面回显展示，
    然后用户修改员工数据之后，点击保存按钮，就可以将修改的数据提交到服务端，保存到数据库。 具体操作为：
        1. 根据ID查询员工信息
        2. 保存修改的员工信息
     */
    /*
    根据ID查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }
    /*
    修改员工数据，传入的数据格式和根据ID查询回显的相同
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){ //这个注解的作用就是将HTTP中发送过来的json数据封装到实体类对象中去。接口文档描述前端发来的数据和Emp对象同格式
        empService.update(emp);
        return Result.success();
    }

    /*
    根据部门id查询员工数量
     */
    @GetMapping("/countEmpbyDept")
    public Result countEmpbyDept(@RequestParam Integer id){
        Integer number=empService.countEmpbyDept(id);
        return Result.success(number);
    }
}
