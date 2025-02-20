package com.fdm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author April Chou
 * @Classname SpringWebSocketConfig
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/16 20:25
 */

@Configuration
@EnableWebSocketMessageBroker
public class SpringWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 配置消息代理
        config.enableSimpleBroker("/topic"); // 启用简单内存消息代理，处理以 /topic 开头的目的地
        config.setApplicationDestinationPrefixes("/app"); // 设置应用前缀，客户端发送消息时需要以 /app 开头
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 STOMP 端点，客户端通过这个端点连接到 WebSocket
        registry.addEndpoint("/ws").withSockJS(); // 支持 SockJS 回退选项
    }
}