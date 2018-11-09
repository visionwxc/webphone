package com.wu.websocket.webphone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *@description spring 注入 websocket bean
 *@date 2018/10/25
 *@Author: xwu
*/
@Configuration
public class WebSocketConfig {

    //服务器站点发现
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
