package com.wu.websocket.webphone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;

@Service
public class WebsocketHandle extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpSession = (HttpSession)session;
        String user = (String) httpSession.getAttribute("user");
        System.out.println(user);
    }
}
