package com.wu.websocket.webphone.entity;

import java.util.HashMap;

public class OutClass {
    private String str;

    private Long id;

    public class InnerClass{
        public InnerClass(){
            str = "wxc";
            id = 1L;
        }
    }

    static class StaticClass{
        public StaticClass(){
            System.out.println("i am statis inner class");
        }
    }

    public InnerClass getInnerClass(){
        return new InnerClass();
    }

    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        OutClass.InnerClass innerClass = outClass.new InnerClass();
        System.out.println(innerClass.toString());
        OutClass.InnerClass innerClass1 = outClass.getInnerClass();
        System.out.println(innerClass1.toString());
        new StaticClass();
        HashMap<Long,String> map = new HashMap<>();
        map.put(1L,"test");
    }
}
