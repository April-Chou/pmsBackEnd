package com.fdm;

import com.fdm.domain.User;
import com.fdm.mapper.UserMapper;
import com.fdm.service.PositionCaculationService;
import com.fdm.service.UserService;
import com.fdm.util.DatabaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PmsBackEndApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DatabaseTest databaseTest;

    @Autowired
    private PositionCaculationService positionCaculationService;

    @Autowired
    private User user;

    @Test
    void contextLoads() throws Exception {
        databaseTest.run();
        System.out.println(userMapper.selectList(null));
//        System.out.println(userService.login());
//        userMapper.insertOrUpdate();
//        userService.login();

    }

    @Test
    void testFindByUsername() {

        System.out.println(userMapper.selectById("1"));
    }

    @Test
    void callPositionCalculator() {
        positionCaculationService.callPositionCalculator();
    }

    /**
     * 提前注入测试对象
     * @throws Exception
     */

//    @BeforeEach
//    @Test
//    void setUp() throws Exception {
//        user.setId(11);
//        user.setUsername("Bill Russell");
//        user.setPassword("00");
//        System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword());
//
//    }

    /**
     * 新增用户方法
     */
    @Test
    void testInsertUser() {
        User user = new User();
        user.setId(11);
        user.setUsername("Bill Russell");
        user.setPassword("00");
        userService.save(user);
    }

    /**
     * 查询方法
     */
    @Test
    void testSelectByUsername() {
        System.out.println(userMapper.selectById(1));
    }

    /**
     * 批量查询
     */
    @Test
    void testQueryByUsernames() {
        List<User> users = userMapper.selectByIds(List.of("Geralt", "Yennefer","Triss"));
        users.forEach(System.out::println);
    }

    /**
     * 测试更新用户
     */
    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUsername("Bill Russell");
        user.setPassword("92");
        userMapper.updateById(user);
    }

    /**
     * 测试删除
     */
    @Test
    void testDeleteUser() {
        userMapper.deleteById("Bill Russell");
    }



//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
//        userList.forEach(System.out::println);
//    }



}
