package com.wu.websocket.webphone.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dice {

    static Long[] init = {1L,2L,3L,4L,5L,6L};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        str.equals("");
        Map<Long,String> map = new HashMap<>();
        map.put(1L,str);
        System.out.println(getKey(map));
    }

    public static String getKey(Map<Long,String> map){
        synchronized (map){
            if(map.get(1L) == null){
                return null;
            }else {
                return map.get(1L);
            }
        }
    }
}
