package com.fdm.service;

import com.fdm.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author April Chou
* @description 针对表【pms_users】的数据库操作Service
* @createDate 2025-02-20 11:44:23
*/
@Service
public interface UserService extends IService<User> {


    User login(User user);
}
