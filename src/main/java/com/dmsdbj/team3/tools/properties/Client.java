package com.dmsdbj.team3.tools.properties;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2020年01月07日 15:15:00
 */
public class Client {
    public static void main(String[] args) {
        String path = "test.properties";
        String key = "username";
        String defaultValue = "password";
        String result = PropertyUtil.getProperty(path, key);
        String result1 = PropertyUtil.getProperty(path, key, defaultValue);
        System.out.println(result);
        System.out.println(result1);
    }

}
