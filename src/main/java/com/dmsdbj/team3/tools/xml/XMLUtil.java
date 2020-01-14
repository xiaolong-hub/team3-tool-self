package com.dmsdbj.team3.tools.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName XmlUtil.java
 * @Description TODO
 * @createTime 2020年01月07日 16:25:00
 */
public class XMLUtil {
    public static Object getBean(String path, String tagName) {
        try {
            // 创建文档解析对象
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // 解析文档，形成文档树，也就是生成Document对象
            Document document = builder.parse(new File(path));
            // 获取XML文档的第一个子节点
            String firstChild = document.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
            Class aClass = Class.forName(firstChild);
            Object o = aClass.newInstance();
            return  o;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
