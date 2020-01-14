package com.dmsdbj.team3.tools.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName ExcelUtil.java
 * @Description TODO
 * @createTime 2020年01月10日 10:27:00
 */
public class ExcelUtil {
    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);

    }

    /**
     * 读取少于1000行数据
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readLessThan1000Rows(String filePath) {
        return readLessThan1000RowsBySheet(filePath, null);
    }

    /**
     * 读小于1000行数据,带样式
     *
     * @param filePath 文件绝对路径
     * @param sheet
     * @return initSheet:
     * sheetNo:sheet页码,默认为1
     * headLineMun:从第几行开始读数据,默认为0,
     * clazz:返回数据List<Object>中Object的类名</>
     */
    private static List<Object> readLessThan1000RowsBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }
        sheet = sheet != null ? sheet : initSheet;

        InputStream inputFileStream = null;
        try {
            inputFileStream = new FileInputStream(filePath);
            return EasyExcelFactory.read(inputFileStream, sheet);
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件或文件路径错误,文件{}" + filePath);
            e.printStackTrace();
        } finally {
            try {
                if (inputFileStream != null) {
                    inputFileStream.close();
                }
            } catch (IOException e) {
                System.out.println("excel文件读取失败,失败原因:{}" + e);
            }
        }
        return null;
    }


    /**
     * 读大于10000行数据
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readMoreThan1000Row(String filePath) {
        return readMoreThan1000RowBySheet(filePath, null);
    }

    /**
     * 读取大于1000行的sheet,带样式
     *
     * @param filePath 文件绝对路径
     * @param sheet    具体的文件
     * @return 返回读取的最后结果
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream inputFileStream = null;
        try {
            inputFileStream = new FileInputStream(filePath);
            ExeclListenenr execlListenenr = new ExeclListenenr();
            EasyExcelFactory.readBySax(inputFileStream, sheet, execlListenenr);
            return execlListenenr.getDatas();
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件或文件路径错误,文件:{}" + filePath);
            e.printStackTrace();
        } finally {
            try {
                if (inputFileStream != null) {
                    inputFileStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件读取失败,失败原因:{}" + e);
            }
        }
        return null;

    }

    /**
     * 生成Excel
     *
     * @param filthPath 绝对路径:
     * @param data      数据源
     * @param head      表头
     */
    public static void writeBySimple(String filthPath, List<List<Object>> data, List<String> head) throws FileNotFoundException {
        writeSimpleBySheet(filthPath, data, head, null);
    }


    /**
     * 生成Excel
     *
     * @param filePath 绝对路径
     * @param data     数据源
     * @param head     表头
     * @param sheet    Excel页面样式
     */
    public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet) throws FileNotFoundException {
        sheet = (sheet != null) ? sheet : initSheet;
        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data, sheet);

        } catch (FileNotFoundException e) {
            System.out.println("找不到文件或文件路径错误,文件:{}" + filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("excel文件导出失败,失败原因:{}" + e);
            }
        }

    }

    /**
     * 生成excel
     * @param filePath     绝对路径
     * @param data         数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data){
        writeWithTemplateAndSheet(filePath,data,null);
    }

    /**
     * @param filePath  绝对路径
     * @param data      数据源
     * @param sheet     excel页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        sheet=(sheet!=null)?sheet:initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream=null;
        ExcelWriter writer=null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件或文件路径错误,文件:{}" + filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Excel文件导出失败,失败原因:{}"+e);
            }
        }
    }

    public static void writeWithMultipleSheel(String filePath, List<MultipleSheelPropety> multipleSheelPropetys) {
        if (CollectionUtils.isEmpty(multipleSheelPropetys)) {
            return;
        }

        OutputStream outputStream=null;
        ExcelWriter writer=null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
                Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
                if(!CollectionUtils.isEmpty(multipleSheelPropety.getData())){
                    sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
                }
                writer.write(multipleSheelPropety.getData(), sheet);
            }

        } catch (FileNotFoundException e) {
            System.out.println("找不到文件或文件路径错误, 文件：{}"+filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("excel文件导出失败, 失败原因：{}"+e);
            }
        }
    }
    /**************************************匿名内部类开始,可以提取出去**************************************/
    @Data
    public static class MultipleSheelPropety {
        private List<? extends BaseRowModel> data;

        private Sheet sheet;
    }


    /**
     * 解析监听器,
     * 每解析一行会回调invoke()方法
     * 整个Excel解析结束会执行doAfterAllAnalysed()方法
     */
    @Getter
    @Setter
    public static class ExeclListenenr extends AnalysisEventListener {
        private List<Object> datas = new ArrayList<>();

        /**
         * 逐行解析
         * Object:当前行的数据
         *
         * @param object
         * @param context
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            if (object != null) {
                datas.add(object);
            }

        }

        /**
         * 解析完所有的数据后会调用该方法
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
        }
    }


    /**************************************匿名内部类结束,可以提取出去**************************************/

}
