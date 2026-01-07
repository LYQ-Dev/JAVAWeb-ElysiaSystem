package com.lyq.service.impl;

import com.lyq.mapper.EmpMapper;
import com.lyq.mapper.StudentMapper;
import com.lyq.pojo.JobOption;
import com.lyq.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
//        返回示例：
//        // 最终返回的是 List<Map<String, Object>>
//    [
//        {
//            "pos": "学工主管",  // 对应SQL中的 pos 列
//                "total": 1         // 对应SQL中的 total 列
//        },
//        {
//            "pos": "其他",
//                "total": 1
//        }
//    ]
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();   //把返回中的信息中的pos抽出来，装入list作为职位种类
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();    //同理抽出各个职位的数量装入list
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegree();
    }
}