package com.lyq.service;

import com.lyq.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();
}
