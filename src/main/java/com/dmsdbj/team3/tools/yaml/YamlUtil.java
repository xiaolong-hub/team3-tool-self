package com.dmsdbj.team3.tools.yaml;

import java.util.Map;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;


import java.util.Properties;


/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName YamlUtil.java
 * @Description TODO 加载yaml配置文件的两种方法:YamlMapFactoryBean与YamlPropertiesFactoryBean
 * @createTime 2020年01月07日 10:57:00
 */
public class YamlUtil {
    public static Map<String,Object>yaml2Map(String yamlSource) {
        YamlMapFactoryBean bean = new YamlMapFactoryBean();
        bean.setResources(new ClassPathResource(yamlSource));
        return bean.getObject();
    }

    public static Properties yaml2Properties(String yamlSource) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(yamlSource));
        return yaml.getObject();

    }

}
