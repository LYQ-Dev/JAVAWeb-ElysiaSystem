package com.lyq.filter;

import com.lyq.utils.CurrentHolder;
import com.lyq.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * 令牌校验过滤器
 * 所有的请求都经过这里，若是登录请求就直接放行；若是其他请求，则需要解析令牌，判断令牌是否合法。
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1. 获取请求url。
        String url = request.getRequestURL().toString();

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){ //登录请求
            log.info("登录请求 , 直接放行");
            chain.doFilter(request, response);  //doFilter方法，调用下一个过滤器或者Servlet(放行)
            return;
        }

        //3. 获取请求头中的令牌（token）。前提是前端已经把token存储在请求头中了
        String jwt = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){ //jwt为空
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED); //抛出错误码401
            return;
        }

        //5. 解析token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJWT(jwt); //解析令牌，并拿出其中的数据
            // 1. 获取用户id（直接强转，避免多余的toString，同时加空值判断）
            Integer empId = (Integer) claims.get("id");
            // 2. 获取用户名
            String username = (String) claims.get("username");

            // 打印正确的解析结果
            log.info("解析到用户名：{}", username);
            log.info("解析到用户id：{}", empId);

            // 空值校验：如果解析不到用户ID，直接返回未登录
            if (empId == null) {
                log.error("JWT令牌中未获取到用户ID");
                response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                return;
            }
            //接下来调用线程工具类存入这个获取到的id（因为要实现写入操作日志的时候记录操作人员ID）
            CurrentHolder.setCurrentId(empId);
            log.info("token解析成功, 放行");

        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        //6. 放行。
        log.info("令牌合法, 放行");
        chain.doFilter(request , response);

        //7. 清空当前线程绑定的id
        CurrentHolder.remove();
    }

}