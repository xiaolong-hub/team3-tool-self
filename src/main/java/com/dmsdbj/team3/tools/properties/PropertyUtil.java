package com.dmsdbj.team3.tools.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName PropertyUtil.java
 * @Description TODO 自定义用于读取properties中的数据,
 * 此种将在类的static静态代码块中读取properties文件内容保存在static属性中的方式可以一次加载多次使用,高效方便
 * @createTime 2020年01月07日 15:15:00
 */
public class PropertyUtil {
    //在类的static静态代码块中读取properties文件内容保存在static属性中以供别的程序使用
    private  static Properties properties;

    //加载指定路径下的properties文件
    synchronized static private void loadProperties(String path) {
        System.out.println("开始加载properties文件内容......");
        properties = new Properties();
        InputStream inputStream=null;
        try {
            //获取Property中的元素
            //加载方式1，通过类加载器进行获取properties文件流
            //inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("test.properties");
            //加载方式2,通过类进行获取properties文件流
            inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(path);

            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("properties文件未找到");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {
                    System.out.println("jdbc.properties文件流关闭出现异常");
                    e.printStackTrace();
                }
            }

        }
        System.out.println("加载properties文件内容完成");
        System.out.println("properties文件内容:" + properties);

    }

    public static String getProperty(String path, String key) {
        if (properties == null) {
            loadProperties(path);
        }
        return properties.getProperty(key);
    }

    public static String getProperty(String path, String key, String defaultValue) {
        if (properties == null) {
            loadProperties(path);
        }
        return properties.getProperty(key, defaultValue);
    }
}
