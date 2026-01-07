package com.lyq.controller;


import com.lyq.pojo.PageResult;
import com.lyq.pojo.Result;
import com.lyq.pojo.Student;
import com.lyq.pojo.StudentQueryParam;
import com.lyq.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController

//注意学生表和班级表是有多对多的关系的，相关操作参考员工相关
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
    学员列表查询
     */
    @GetMapping()
    public Result page(StudentQueryParam studentQueryParam){
        PageResult allStudent =studentService.page(studentQueryParam);
        return Result.success(allStudent);
    }

    /**
     * 批量删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List< Integer> ids){
        log.info("批量删除员工，ids：{}", ids);
        //批量删除员工
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 新增学员
     */
    @PostMapping()
    public Result add(@RequestBody Student student){
        log.info("新增员工，员工信息：{}", student);
        //新增员工
        studentService.add(student);
        return Result.success();
    }

    /*
    根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Student student=studentService.getInfo(id);
        student.setClazzName(null); // 手动置空，序列化时不会显示该字段,符合前端接口需求
        return Result.success(student);
    }

    /*
    修改学员基本信息
     */
    @PutMapping()
    public Result update(@RequestBody Student student){
        log.info("修改学生，学生信息：{}", student);
        //修改学生
        studentService.update(student);
        return Result.success();
    }


    /**
     * 管理学生分数
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Short score){
        log.info("管理学生分数，id：{}，score：{}", id, score);
        //管理学生分数

//        1.先获取当前学生分数，再修改分数
        Student crustudent = studentService.getInfo(id);
        if(crustudent==null){
            return Result.error("无此学生");
        }
        Short cruScore = crustudent.getViolationScore();
        if(cruScore<score){
            return Result.error("扣分不能大于当前分数");
        }
        cruScore = (short) (cruScore - score);
        crustudent.setViolationScore(cruScore);
        studentService.updateData(crustudent);
        return Result.success();
    }

}
