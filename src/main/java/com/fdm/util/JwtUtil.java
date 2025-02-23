package com.fdm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author April Chou
 * @Classname JwtUtil
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/23 19:42
 */
@Component
public class JwtUtil {
    private static String secretCode = "witcher";
    private static Long expireTime = 12*3600*1000L;

    /**
     * 生成JWT令牌
     * @param claims
     * @return
     */
    public static String generateJWT(Map<String, Object> claims) {
        return JWT.create().withPayload(claims).withExpiresAt(new Date(System.currentTimeMillis() + expireTime)).sign(Algorithm.HMAC256(secretCode));
    }

    /**
     * 解析令牌
     * @param token
     */
    public static void getJWTInfo(String token) {
        try {
            // 解析 JWT
            DecodedJWT jwt = JWT.decode(token);

            // 获取头部信息
            System.out.println("算法: " + jwt.getAlgorithm());
            System.out.println("类型: " + jwt.getType());

            // 获取载荷信息
            System.out.println("ID: " + jwt.getClaim("id").asInt());
            System.out.println("用户名: " + jwt.getClaim("username").asString());
            System.out.println("过期时间: " + jwt.getExpiresAt());
            System.out.println("Password: " + jwt.getClaim("password").asString());

            // 获取签名
            System.out.println("签名: " + jwt.getSignature());
        } catch (JWTDecodeException e) {
            // 处理 JWT 解析失败的情况
            System.err.println("JWT 解析失败: " + e.getMessage());
        }
    }

    /**
     * 解析 JWT（不验证签名）
     *
     * @param token JWT 字符串
     * @return DecodedJWT 对象
     * @throws JWTDecodeException 如果 JWT 格式无效
     */
    public static DecodedJWT decodeJwt(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    /**
     * 验证并解析 JWT
     *
     * @param token  JWT 字符串
     * （用于验证签名）
     * @return DecodedJWT 对象
     * @throws JWTVerificationException 如果 JWT 验证失败（签名无效或已过期）
     */
    public static DecodedJWT verifyJwt(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretCode); // 使用 HMAC256 算法
        JWTVerifier verifier = JWT.require(algorithm).build(); // 创建验证器
        return verifier.verify(token); // 验证并解析 JWT
    }

    /**
     * 从 JWT 中获取指定声明的值
     *
     * @param token JWT 字符串
     * @param claim 声明名称
     * @return 声明的值（String 类型）
     */
    public static String getClaimAsString(String token, String claim) {
        try {
            DecodedJWT jwt = decodeJwt(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("JWT 解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从 JWT 中获取指定声明的值
     *
     * @param token JWT 字符串
     * @param claim 声明名称
     * @return 声明的值（Integer 类型）
     */
    public static Integer getClaimAsInt(String token, String claim) {
        try {
            DecodedJWT jwt = decodeJwt(token);
            return jwt.getClaim(claim).asInt();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("JWT 解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 检查 JWT 是否已过期
     *
     * @param token JWT 字符串
     * @return 如果 JWT 已过期返回 true，否则返回 false
     */
    public static boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = decodeJwt(token);
            Date expiresAt = jwt.getExpiresAt();
            return expiresAt != null && expiresAt.before(new Date());
        } catch (JWTDecodeException e) {
            throw new RuntimeException("JWT 解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 获取 JWT 的过期时间
     *
     * @param token JWT 字符串
     * @return 过期时间（Date 对象），如果未设置过期时间则返回 null
     */
    public static Date getExpirationDate(String token) {
        try {
            DecodedJWT jwt = decodeJwt(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("JWT 解析失败: " + e.getMessage(), e);
        }
    }





}
