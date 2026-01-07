package com.lyq.controller;

import com.lyq.pojo.Dept;
import com.lyq.pojo.Result;
import com.lyq.service.DeptService;
import com.lyq.service.EmpService;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  //引入日志
@RequestMapping("/depts")    //总体抽取出来后面就不用每个都重复写了
@RestController //内部封装了一个注解会将响应回来的数据结构封装成json返回给前端
public class DeptController {

    @Autowired
    private DeptService deptService;    //因为下面要用DeptService接口对应的实现类中的对象的方法，所以这里通过注入（就不用手动自己new）来生成一个对象
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(){
//        System.out.printf("查询全部部门数据");
        //替换为日志写法
        log.info("查询部门列表");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping  //查询参数写法，拼接在路径末尾的 ?id=1
    public Result delete(@RequestParam("id") Integer id){
        log.info("根据id删除部门, id: {}" , id);
        /*
        这里完善个功能：该部门有员工时不允许删除
         */
        if(empService.countEmpbyDept(id)>0){
            return Result.error("该部门有员工，不允许删除");
        }
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(String name){
        Dept dept=new Dept();
        dept.setName(name);
        log.info("新增部门, dept: {}" , dept);
        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")  //路径参数写法，直接写在路径中
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询, id: {}" , id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门, dept: {}" , dept);
        deptService.update(dept);
        return Result.success();
    }

}
