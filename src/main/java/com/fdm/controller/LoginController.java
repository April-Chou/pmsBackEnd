package com.fdm.controller;

import com.fdm.domain.Result;
import com.fdm.domain.User;
import com.fdm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Chou
 * @Classname LoginController
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/19 21:42
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("Login User: {}", user);
        User loginUser = userService.login(user);
        return loginUser != null?Result.success() : Result.error("login failed, username or password wrong. please try again");

    }
}
