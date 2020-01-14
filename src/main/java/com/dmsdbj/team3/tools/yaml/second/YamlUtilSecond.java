package com.dmsdbj.team3.tools.yaml.second;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;
import java.util.Properties;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName YamlUtilSecond.java
 * @Description TODO 加载yaml配置文件的两种方法:YamlMapFactoryBean与YamlPropertiesFactoryBean
 * @createTime 2020年01月07日 14:52:00
 */
public class YamlUtilSecond {

    public static Map<String, Object> yamlSecondMap(String yamlSource) {
        YamlMapFactoryBean bean=new YamlMapFactoryBean();
        bean.setResources(new ClassPathResource(yamlSource));
        return bean.getObject();
    }

    public static Properties yamlSecondProperties(String yamlSource) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(yamlSource));
        return yaml.getObject();
    }
}
