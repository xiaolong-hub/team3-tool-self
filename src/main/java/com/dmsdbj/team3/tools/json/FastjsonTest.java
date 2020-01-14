package com.dmsdbj.team3.tools.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import lombok.Data;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName FastjsonTest.java
 * @Description TODO
 * @createTime 2020年01月08日 09:50:00
 */
public class FastjsonTest {
    /**
     * 将user对象转JSON
     */
    public void objectToJson() {
        User user = new User();
        user.setAge(20);
        user.setId(1402014);
        user.setBirthday(new Date());
        user.setName("YNO");

        /**
         * 这里运用阿里巴巴user对象转JSON的工具类PropertyFilter继承Java的SerializeFilter接口
         */
        SerializeFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                if (s.equals("id")) {
                    return false;
                }
                return true;
            }
        };
        //将user对象转换成JSON
        String json = JSONObject.toJSONString(user, filter);
        System.out.println(json);
    }

    @Test
    public void listToJsonTest() {
        User user1 = new User();
        user1.setAge(25);
        user1.setId(1402013);
        user1.setName("Betty");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setAge(27);
        user2.setId(1402011);
        user2.setName("Cook");
        user2.setBirthday(new Date());

        User user3 = new User();
        user3.setAge(26);
        user3.setId(1402012);
        user3.setName("Sunshine");
        user3.setBirthday(new Date());


        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        String listToJson = JSONArray.toJSONString(userList);
        System.out.println(listToJson);
    }

    @Data
    public class User {
        private int age;
        private String name;
        private int id;

        public void setAge(int age) {
            this.age = age;
        }

        public void setId(int id) {
            this.id = id;
        }

        @JSONField(format = "yyyy-MM-dd")
        private Date birthday;

        public void setBirthday(Date date) {
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
