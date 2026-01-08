package com.lyq;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 修复：类名遵循大驼峰命名法
public class TestGenJwt {

    // 修复：@Test注解标注在方法上，所有业务逻辑放在方法内
    @Test
    public void generateJwtToken() {
        // 1. 定义JWT载荷Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "itheima");

        try {
            // 2. 生成JWT令牌，本质上就是一个字符串
            String jwt = Jwts.builder()
                    // 设置签名算法和密钥（注意：生产环境密钥不能硬编码）
                    .signWith(SignatureAlgorithm.HS256, "aXRjYXN0")
                    // 添加自定义载荷
                    .addClaims(claims)
                    // 设置过期时间（12小时）
                    .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                    // 构建并压缩JWT
                    .compact();

            // 3. 打印生成的JWT
            System.out.println("生成的JWT令牌：");
            System.out.println(jwt);

            // 4. 解析JWT令牌，验证是否正确
            Claims claims2 = Jwts.parser().setSigningKey("aXRjYXN0")    //密钥必须和生成的时候一致
                    .parseClaimsJws(jwt)
                    .getBody();
            System.out.println(claims2);
        } catch (Exception e) {
            // 异常处理：打印错误信息
            System.err.println("生成JWT失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}