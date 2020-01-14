package com.dmsdbj.team3.tools.word;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2020年01月09日 15:48:00
 * 参考链接:https://blog.csdn.net/m0_37327416/article/details/72650673
 */
public class Test {
    public static void main(String[] args) {
        /*存放填充数据*/
        Map<String, Object> dataMap = new HashMap<>();
        /*要填充的数据*/
        dataMap.put("username", "sunshine");
        dataMap.put("sex", "男");
        String templateFile = "/Users/lanan/Downloads/team3toolself/template.ftl";
        String exportFile = "/Users/lanan/Downloads/team3toolself/src/test/test.word";
        /*运用反射调用WordUtil类的实例方法*/
        WordUtil.getInstance().createDocFile(templateFile, dataMap, exportFile, 1);
    }

}
