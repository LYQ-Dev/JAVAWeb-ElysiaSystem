package com.lyq.controller;

import com.lyq.pojo.JobOption;
import com.lyq.pojo.Result;
import com.lyq.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();    //依据前端接口文档需求规范的返回值新建个对象Object
        return Result.success(jobOption);
    }
    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();    //依据前端接口文档需求规范,设计的数据结构来接收返回值
        return Result.success(genderList);
    }
    /**
     * 统计学生学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历信息");
        List<Map> degreeList = reportService.getStudentDegreeData();    //依据前端接口文档需求规范,设计的数据结构来接收返回值
        return Result.success(degreeList);
    }

}