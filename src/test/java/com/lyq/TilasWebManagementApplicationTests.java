package com.lyq;

import com.lyq.mapper.EmpMapper;
import com.lyq.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TilasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
//    private EmpMapper empMapper;
//
//    @Test
//    public void testList(){
//        List<Emp> empList = empMapper.list();
//        // 1. 打印数据量，确认是否查到数据
//        System.out.println("查询到的员工数量：" + empList.size());
//        // 2. 遍历打印每条数据（如果有数据）
//        if (empList.isEmpty()) {
//            System.out.println("⚠️ 没有查询到任何员工数据！");
//        } else {
//            empList.forEach(emp -> {
//                System.out.println("员工信息：" + emp);
//            });
//        }
//    }

}
