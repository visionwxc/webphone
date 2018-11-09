package com.wu.websocket.webphone.entity;

public class Test {

    public static void changeStr(String str){
        str = "welcome";
    }
    public static void main(String[] args) {
        String str = "123";
        changeStr(str);
        System.out.println(str);
    }
}
