package com.fdm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdm.domain.User;
import com.fdm.mapper.UserMapper;
import com.fdm.service.PositionCaculationService;
import com.fdm.service.UserService;
import com.fdm.util.DatabaseTest;

import com.fdm.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    @Autowired
    private JwtUtil jwtUtil;

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
     * 查询ysername 带e的
     *
     */
    @Test
    void testQueryWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","username","password").like("username","e");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * QueryWrapper 测试更新
     */
    @Test
    void testUpdateByQueryWrapper() {
        User user = new User();
        user.setId(17);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "Yennefer");
        userMapper.update(user, wrapper);
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

    @Test
    void testUserService() {
        userService.saveOrUpdate(user);
    }

    @Test
    void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", 3);
        claims.put("username","Ciri");
        String jwt = JWT.create().withPayload(claims).withExpiresAt(new Date(System.currentTimeMillis() + 12*3600*1000)).sign(Algorithm.HMAC256("witcher"));
        System.out.println(jwt);
    }

//    @Test
//    void testDecodeJWT() {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywidXNlcm5hbWUiOiJDaXJpIiwiZXhwIjoxNzQwMzEyOTYzfQ.5_hNyD8R4-MFfgHbgJyQDconi_rTY_WYwIWh0RG6rWk";
//        System.out.println(jwtVerifierUtil.verifyJwt(token));
//        System.out.println(jwtVerifierUtil.verifyJwt(token).getPayload());
//        System.out.println(jwtVerifierUtil.verifyJwt(token).getExpiresAt());
//        System.out.println(jwtVerifierUtil.verifyJwt(token).getIssuedAt());
//        System.out.println(jwtVerifierUtil.verifyJwt(token).getSubject());
//        System.out.println(jwtVerifierUtil.verifyJwt(token).getAudience());
////        JWTParser jwtParser = new JWTParser();
////        JWTParser
////        JWTVerifier jwtVerifier = new com.auth0.jwt.interfaces.JWTVerifier()
//    }

    @Test
    void testJwtUtil(){
//        JwtUtil.generateJWT(Map.of("username","Bill Russell"));
//        JwtUtil.generateJWT(Map.of("id","4"));
//        JwtUtil.verifyJWT("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkNpcmkiLCJpZCI6MywiZXhwIjoxNzQwMzU1NTU1fQ.oN4xI_aVEw5onPyvtS-azFsXy1QGYN4d_zN8Jgx3axo");
        JwtUtil.verifyJwt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjQ1NiIsInVzZXJuYW1lIjoiQ2lyaSIsImV4cCI6MTc0MDM2Mzk2Nn0.OWPXll_MGUCs7fp4zClvTjCv3ubsJGC6wgoOACv8A1E");

    }



//    @Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//        Assert.isTrue(5 == userList.size(), "");
//        userList.forEach(System.out::println);
//    }



}
