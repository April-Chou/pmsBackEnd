package com.fdm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fdm.domain.User;
import com.fdm.service.UserService;
import com.fdm.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author April Chou
* @description 针对表【pms_users】的数据库操作Service实现
* @createDate 2025-02-20 11:44:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

}




