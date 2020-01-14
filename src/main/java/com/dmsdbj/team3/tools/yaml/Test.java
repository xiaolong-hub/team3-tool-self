package com.dmsdbj.team3.tools.yaml;

import java.util.Map;
import java.util.Properties;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2020年01月07日 10:55:00
 */
public class Test {
    public static void main(String[] args) {
        String path = "test.yaml";
        Map<String, Object> stringObjectMap = YamlUtil.yaml2Map(path);
        System.out.println(stringObjectMap);

        Properties properties = YamlUtil.yaml2Properties(path);
        System.out.println(properties);
    }
}
