package com.dmsdbj.team3.tools.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName GsonUtil.java
 * @Description TODO 基于谷歌(com.google.code.gson)封装的JSON转换工具类
 * @createTime 2020年01月08日 09:50:00
 */
public class GsonUtil {
    /**
     * 1.static方法是类中的一个成员方法,属于整个类,即使不用创建任何对象也可以直接调用!
     */
    private static Gson gson = null;

    static {
        if (gson == null) {
            //实例化一个Gson工具类
            gson = new Gson();

        }
    }

    private GsonUtil() {
    }

    //对象转JSON

    /**
     * 对象转JSON
     *
     * @param object 需要转成JSON的对象
     * @return 转化好的内容
     */
    public static String gsonObjectToJson(Object object) {
        String gsonObjectToJson = null;
        if (gson != null) {
            //调用gson工具类的toJson方法将对象转换成JSON
            gsonObjectToJson = gson.toJson(object);
        }
        return gsonObjectToJson;
    }

    //JSON转对象

    /**
     * @param gsonJson 需要转换成对象的JSON
     * @param clazz    最终由JSON转换成的对象
     * @param <T>      泛型
     * @return
     */
    public static <T> T gsonJsonToObject(String gsonJson, Class<T> clazz) {
        //声明一个对象类型的变量jsonToObjectResult 用于保存JSON转对象后的结果
        T jsonToObjectResult = null;
        if (gson != null) {
            //json转对象使用的是fromJson方法
            jsonToObjectResult = gson.fromJson(gsonJson, clazz);
        }
        return jsonToObjectResult;
    }

    //JSON转list<T>

    /**
     * @param gsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToList(String gsonString, Class clazz) {
        List<T> gsonJsonToListResult = null;
        if (gson != null) {
            gsonJsonToListResult = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return gsonJsonToListResult;
    }


    //JSON转成list中有map的
    public static <T> List<Map<String, T>> gsonJsonToListMaps(String gsonString) {
        List<Map<String, T>> gsonJsonToListMapsResult = null;
        if (gson != null) {
            gsonJsonToListMapsResult = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return gsonJsonToListMapsResult;
    }

    //JSON转成map的
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> gsonJsonToMapResult = null;
        if (gson != null) {
            gsonJsonToMapResult = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return gsonJsonToMapResult;
    }


}
