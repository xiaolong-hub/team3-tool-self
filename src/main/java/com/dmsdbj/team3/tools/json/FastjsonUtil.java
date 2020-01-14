package com.dmsdbj.team3.tools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName FastjsonUtil.java
 * @Description TODO  基于阿里的fastjson封装的JSON转换工具类
 * @createTime 2020年01月08日 09:50:00
 */
public class FastjsonUtil {

    /**
     * 功能描述:把JSON数据转换成指定的Java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的Java对象
     * @param <T>
     * @return 指定的Java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);

    }

    /**
     * 功能描述:把Java对象转换成JSON数据
     *
     * @param object
     * @return
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);

    }


    /**
     * 功能描述:把JSON数据转换成指定的Java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的Java对象
     * @param <T>
     * @return
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }


    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }
}
