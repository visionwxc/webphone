package com.wu.websocket.webphone.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    //静态变量，当前在线人数，应该把它设计成线程安全的
    private static int onlineCount = 0;
    //concurrent包下的线程安全set，用来存放每个客户端的对应的mywebsocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，用它给客户端发送数据
    private Session session;

    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        this.session = session;
        webSocketServers.add(this);
        addOnlineCount();
        logger.info("有新的连接加入，当前人数为 :" + getOnlineCount());
        try{
            sendMessage("连接成功！");
        }catch (Exception e){
            logger.error("websocket IO 异常" + e.getMessage());
        }
    }

    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        subOnlineCount();
        logger.info("有连接退出，当前人数为 :" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("来自客户端的消息 : "+ message);
        //群发消息
        for (WebSocketServer webSocketServer : webSocketServers) {
            try {
                webSocketServer.sendMessage(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println(session.getId());
        logger.error("发生错误！");
        error.printStackTrace();
    }

    private static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    private static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }
    private static synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

    public static void main(String[] args) {
        //测试插入排序
//        Long[] demos = {1L,5L,2L,3L,8L,4L};
//        System.out.println(Arrays.toString(demos));
//
//        int in,out;
//        for (out = 1; out < demos.length; out++) {
//            long temp = demos[out];
//            in = out;
//            while (in > 0 && demos[in - 1] >= temp){
//                demos[in] = demos[in -1];
//                in-- ;
//            }
//            demos[in] = temp;
//        }
//        System.out.println(Arrays.toString(demos));
        //栈的实例1，将字符串置反
//        Stack<String> strs = new Stack<>();
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.next();
//        String[] split = input.split("");
//        for (String s : split) {
//            System.out.print(s);
//            strs.push(s);
//        }
//        System.out.println();
//        while (!strs.isEmpty()){
//           String s = strs.pop();
//           System.out.print(s);
//        }
        //栈的实例2，匹配括号
//        Stack<String> brackets = new Stack<>();
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.next();
//        String[] spilt = input.split("");
//        boolean flag = false;
//        for (String s : spilt) {
//            if(s.equals("{") || s.equals("[") || s.equals("<")){
//                brackets.push(s);
//            }
//            if(s.equals("}") || s.equals("]") || s.equals(">")){
//               if(!brackets.isEmpty()){
//                switch (s){
//                    case "}" : flag = brackets.pop().equals("{"); break;
//                    case "]" : flag = brackets.pop().equals("]"); break;
//                    case ">" : flag = brackets.pop().equals("<"); break;
//                }
//               }
//            }
//        }
//        if(flag){
//            System.out.println("正确");
//        }else {
//            System.out.println("错误");
//        }
        //队列
//        Queue<Long> queue = new PriorityQueue<>();
//        queue.add(1L);
//        queue.add(2L);
//        queue.add(3L);
//        queue.add(4L);
//        queue.add(5L);
//        System.out.println(queue.poll());
        //链表
//        LinkedList<Long> linkedList = new LinkedList<>();
        //希尔排序

    }
}
