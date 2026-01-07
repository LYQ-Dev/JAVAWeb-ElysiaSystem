package com.lyq.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/*
1). 需要创建一个实现类，且实体类中的属性名和配置文件当中key的名字必须要一致
     比如：配置文件当中叫endpoint，实体类当中的属性也得叫endpoint，另外实体类当中的属性还需要提供 getter / setter方法
2). 需要将实体类交给Spring的IOC容器管理，成为IOC容器当中的bean对象
3). 在实体类上添加@ConfigurationProperties注解，并通过perfect属性来指定配置参数项的前缀
 */

//定义实体类AliyunOSSProperties ，并交给IOC容器管理
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
}