package com.fdm.filter;

import com.alibaba.fastjson2.JSONObject;
import com.fdm.domain.Result;
import com.fdm.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author April Chou
 * @Classname LoginCheckFilter
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/23 20:59
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 1. 获取URL请求
     * 2. 判断请求url中是否包含login， 如果包含，说明是登录操作，放行
     * 3. 获取请求头中的令牌（token）
     * 4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
     * 5. 解析token，如果解析失败，返回错误结果（未登录）
     * 6. 放行
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        1. 获取URL请求
        String url = request.getRequestURL().toString();
        log.info("request url is : " + url);

//        2. 判断请求url中是否包含login， 如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("This is login request" + url);
            filterChain.doFilter(request, response);
            return;
        }
//         3. 获取请求头中的令牌（token）, 请求头的名字就叫token
        String jwt = request.getHeader("token");

//        4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("Token is empty, return unauthorized information");
//            msg 里面NOT_LOGIN 是传回前端的信息
            Result error = Result.error("NOT_LOGIN");
//            手动将对象转为Json
            String notLogin = JSONObject.toJSONString(error);
//            通过response对象将请求返回给浏览器
            response.getWriter().write(notLogin);
            return;
        }

//        5. 解析token，如果解析失败，返回错误结果（未登录）
//        捕获异常，如果出现异常，说明令牌解析失败，要么未登录，要么被篡改，没有异常则说明解析成功
        try {
            JwtUtil.verifyJwt(jwt);
        } catch (Exception e) {
            log.info("Token is empty, return unauthorized information");
//            msg 里面NOT_LOGIN 是传回前端的信息
            Result error = Result.error("NOT_LOGIN");
//            手动将对象转为Json
            String notLogin = JSONObject.toJSONString(error);
//            通过response对象将请求返回给浏览器
            response.getWriter().write(notLogin);
            return;
        }
//        6. 放行
        log.info("Token is valid");
        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
