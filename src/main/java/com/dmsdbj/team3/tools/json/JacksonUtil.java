package com.dmsdbj.team3.tools.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Data;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName JacksonUtil.java
 * @Description TODO
 * @createTime 2020年01月08日 09:51:00
 */
public class JacksonUtil {
    @Data
    public class Product {
        private int weight;
        private String name;
        private int price;
        private Date releaseDate;
    }


    //将Product转换成JSON
    @Test
    public void objToJSON() {
        Product product = new Product();
        product.setName("娃哈哈");
        product.setPrice(10);
        product.setReleaseDate(new Date());
        product.setWeight(100);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String json = null;
        try {
            json = mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);

    }

    //将List<Product>转换成JSON
    // @Test注解用法:将方法标记为测试方法。
    @Test
    public void listToJson() {
        Product hairTV = new Product();
        hairTV.setWeight(1000);
        hairTV.setPrice(3999);
        hairTV.setName("hair智能电视");
        hairTV.setReleaseDate(new Date());


        Product TCLTV = new Product();
        TCLTV.setReleaseDate(new Date());
        TCLTV.setName("TCL智能电视");
        TCLTV.setPrice(3999);
        TCLTV.setWeight(1100);

        List<Product> productList = new ArrayList<>();
        productList.add(hairTV);
        productList.add(TCLTV);

        ObjectMapper mapper = new ObjectMapper();

        //处理过滤属性
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("productFilter", SimpleBeanPropertyFilter.serializeAllExcept("id", "name"));

        String listToJson = null;
        try {
            listToJson = mapper.writeValueAsString(productList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(listToJson);
    }


}
