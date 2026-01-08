package com.lyq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;


@MapperScan("com.lyq.mapper")   // 核心注解：指定Mapper接口所在的包路径
@SpringBootApplication
@ServletComponentScan //开启对Servlet组件的支持，不开启，过滤器无法生效
public class TilasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebManagementApplication.class, args);
    }

}
