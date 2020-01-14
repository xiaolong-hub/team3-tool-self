package com.dmsdbj.team3.tools.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.List;
import java.util.Map;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName JsonUtil.java
 * @Description TODO 处理json数据格式的工具类
 * @createTime 2020年01月08日 09:51:00
 */
public class JsonUtil {

    /**
     * 将数组转换成String类型的JSON数据格式
     *
     * @param objects 要转换为String类型的JSON数据的数组
     * @return 返回转好的JSON
     */
    public static String arraryToStringJSON(Object[] objects) {
        JSONArray jsonArray = JSONArray.fromObject(objects);
        return jsonArray.toString();
    }

    /**
     * 将list集合转换成String类型的JSON数据格式
     *
     * @param list 需要转JSON的list
     * @return 转好的JSON
     * * jsonArray，使用中括号[ ],只不过数组里面的项也是json键值对格式的
     */
    public static String listToStringJson(List list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * 将xml数据格式转换成String类型的JSON数据格式
     *
     * @param xml
     * @return
     */
    public static String xmlToStringJson(String xml) {
        JSONArray jsonArray = JSONArray.fromObject(xml);
        return jsonArray.toString();
    }

    /**
     * jsonObject，就是一个键对应一个值，使用的是大括号{ }，如：{key:value}
     * JsonObject中是添加的键值对,JSONArray中添加的是Json对象
     * 将map集合转换成String类型的JSON数据格式
     *
     * @param map
     * @return 由map转好的Json
     */
    public static String mapToStringJson(Map map) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }

    /**
     * 将Object 对象转换成String类型的JSON数据格式
     *
     * @param object 需转对象
     * @return 转好内容
     */
    public static String objectToStringJson(Object object) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }


    /**
     * 除去不想生成的字段(特别适合去掉级联的对象)
     *
     * @param excludes
     * @return
     */
    public static JsonConfig configJson(String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(true);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return jsonConfig;
    }

}
