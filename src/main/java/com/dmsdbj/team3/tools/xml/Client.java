package com.dmsdbj.team3.tools.xml;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2020年01月07日 16:25:00
 */
public class Client {
    public static void main(String[] args) {
        //指定XML文档路径
        String path = "/Users/lanan/Downloads/team3toolself/src/main/resources/test.xml";
        String className = "className";
        Object bean = XMLUtil.getBean(path, className);
        System.out.println(bean.getClass().getName());
    }
}
