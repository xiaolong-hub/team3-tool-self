package com.dmsdbj.team3.tools.word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;
/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName WordUtil.java
 * @Description TODO
 * @createTime 2020年01月09日 15:48:00
 */
public class WordUtil {
    /*
    Freemarker java导出word文档步骤
    * 1.生成word模版
      2.修改ftl模版
      3.填充数值，导出word模板
      参考链接:https://www.jianshu.com/p/89a80afb5f4f
    */

    private static WordUtil wordUtil=null;
    private static Object WordUtil;

    private WordUtil() {

    }

    /*利用反射获取WordUtil类 单例模式兼锁
    * 实例化一个工具类供多个线程调用,节约内存资源*/
    public static WordUtil getInstance() {
        if (wordUtil == null) {
            synchronized (WordUtil.class) {
                if (wordUtil == null) {
                    wordUtil = new WordUtil();
                }
            }
        }
        return wordUtil;
    }

    /**
     *
     * @param tmeplateFilePath     导入Word模板的位置
     * @param dataMap              Word文档内容是key-value的形式
     * @param exportFilePath       将Word导出的位置
     * @param loadType             设置路径加载方式.1-绝对路径 ,2-项目相对路径
     * @return
     */
    public File createDocFile(String tmeplateFilePath, Map<String, Object> dataMap, String exportFilePath, int loadType) {
        Template t = null;

        /*configuration用于读取ftl文件*/
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        /*以UTF-8的编码读取ftl文件*/
        configuration.setDefaultEncoding("UTF-8");
        try {
            //调用pathReplace方法替换导入文档的路径
            tmeplateFilePath = pathReplace(tmeplateFilePath);
            String ftlPath = tmeplateFilePath.substring(0, tmeplateFilePath.lastIndexOf("/"));
            if (loadType == 1) {
                //FTL文件所在的位置
                configuration.setDirectoryForTemplateLoading(new File(ftlPath));
            } else {
                //类加载的方式查找模板文件路径
                configuration.setClassForTemplateLoading(this.getClass(), ftlPath);
            }

            String ftlFile = tmeplateFilePath.substring(tmeplateFilePath.lastIndexOf("/") + 1);
            //模板文件名
            t = configuration.getTemplate(ftlFile);

            /*输出文档路径及名称*/
            File outFile = new File(exportFilePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            t.process(dataMap,out);
        } catch (IOException | TemplateException e) {
            System.out.println("导出Word文档出错");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把路径的\替换成/
     * @param tmeplateFilePath
     * @return
     */
    private String pathReplace(String tmeplateFilePath) {
        while (tmeplateFilePath != null && tmeplateFilePath.contains("\\")) {
            tmeplateFilePath = tmeplateFilePath.replace("\\", "/");
        }
        return tmeplateFilePath;
    }

}
