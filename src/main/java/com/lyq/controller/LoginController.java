package com.lyq.controller;

import com.lyq.pojo.Emp;
import com.lyq.pojo.LoginInfo;
import com.lyq.pojo.Result;
import com.lyq.service.EmpService;
import com.lyq.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工来登录啦 , {}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null){
            //在这里加上生成令牌并返回给前端的逻辑
            //1.先构建一个jwt标准形式的键值对map，然后把查询到的值对应赋值给map
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", loginInfo.getId());
            dataMap.put("username", loginInfo.getUsername());

            //把构建的map传入jwt工具类生成jwt令牌，jwt本质上就是一个字符串
            String jwt= JwtUtils.generateJwt(dataMap);
            //最后把生成的token加在返回的数据上一起返回给前端
            LoginInfo loginInfoWithToken = new LoginInfo(loginInfo.getId(), loginInfo.getUsername(), loginInfo.getName(), jwt);

            return Result.success(loginInfoWithToken);
        }
        return Result.error("用户名或密码错误~");
    }

}