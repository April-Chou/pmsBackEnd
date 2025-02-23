package com.fdm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author April Chou
 * @Classname SpringWebSocketConfig
 * @Description http协议里浏览器向服务区发送的请求方向是固定的，但服务器之间可以互相发送请求，不固定
 * @Version 1.0
 * @Date 2025/2/16 20:25
 */


@Configuration
@EnableWebSocketMessageBroker
public class SpringWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 配置消息代理
        // 使用内置的消息代理进行订阅和广播，并且 将目标标头以 /topic 或 /queue 开头的消息路由到代理。
        config.enableSimpleBroker("/topic"); // 启用简单内存消息代理，处理以 /topic 开头的目的地
        // 目标标头以 /app 开头的 STOMP 消息会被路由到 @Controller 类中的 @MessageMapping 方法。
        config.setApplicationDestinationPrefixes("/app"); // 设置应用前缀，客户端发送消息时需要以 /app 开头
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 STOMP 端点，客户端通过这个端点连接到 WebSocket
        // ws是 WebSocket（或 SockJS）的端点的 HTTP URL。客户端需要连接以进行 WebSocket 握手。
        registry.addEndpoint("/ws").withSockJS(); // 支持 SockJS 回退选项
    }


    /**
     * spring 为了支持每种容器自己的 websocket 升级策略，抽象了 RequestUpgradeStrategy，
     * <p>对 tomcat 提供了 TomcatRequestUpgradeStrategy 策略</p>
     * 如果不申明这个，就会在启动的时候抛出异常：No suitable default RequestUpgradeStrategy found
     */
//    @Bean
//    public TomcatRequestUpgradeStrategy tomcatRequestUpgradeStrategy() {
//        return new TomcatRequestUpgradeStrategy();
//    }
}