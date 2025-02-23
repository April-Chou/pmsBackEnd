package com.fdm.controller;

import com.fdm.domain.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Chou
 * @Classname SessionController
 * @Description TODO 纯演示，不用的
 *
 * @Version 1.0
 * @Date 2025/2/23 17:14
 */
@Slf4j
@RestController
public class SessionController {

//    设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        response.addCookie(new Cookie("login_username", "root"));
        return Result.success();
    }

//    获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {
//        获取所有Cookies
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_username")) {
                System.out.println("login_username: " + cookie.getValue());
            }
        }
        return Result.success();
    }

//    往session中存储值
    @GetMapping("/s1")
    public Result Session1(HttpSession session) {
        log.info("Session 1: {}", session.hashCode());
//        往session中存值
        session.setAttribute("login_username", "tom");
        return Result.success();
    }

//    往session中取值
    @GetMapping("/s2")
    public Result Session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("Session 2: {}", session.hashCode());

//        从session中获取数据
        Object loginUser = session.getAttribute("login_username");
        log.info("Login User: {}", loginUser);
        return Result.success(loginUser);

    }


}
