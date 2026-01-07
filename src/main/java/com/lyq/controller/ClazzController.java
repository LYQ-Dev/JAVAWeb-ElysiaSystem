package com.lyq.controller;

import com.lyq.pojo.*;
import com.lyq.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController

public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /*
    查询所有班级信息
     */
    @GetMapping("/list")
    public Result getAllEmp(){
        List<Clazz> allClazzs=clazzService.getAllClazzs();
        return Result.success(allClazzs);
    }

    /*
    分页查询班级
     */
    @GetMapping //这里就可以用ClazzQueryParam封装好的结构来代替一个个的传入参数
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询请求参数： {}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /*
    删除班级
     */
    @DeleteMapping("/{id}")  //查询参数写法，拼接在路径末尾的 ?id=1，删除单个班级
    public Result delete(@PathVariable Integer id){
        clazzService.deleteByIds(id);
        return Result.success();
    }

    /*
    添加班级
     */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /*
    根据id查询班级
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Clazz clazz=clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    /*
    修改班级
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }
}
