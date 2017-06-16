package com.wordty.third.wechat;

import java.net.URL;

/**
 * Created by jerry on 6/16/17.
 */
public class Test {


    public static void main(String[] args) {

        URL resource = Test.class.getClassLoader().getResource("cfg-third-wechat.properties");

        System.out.println(resource);


    }

}
