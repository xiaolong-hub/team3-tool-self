package com.dmsdbj.team3.tools.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2020年01月10日 10:27:00
 */
public class Test {
    /**
     * 读取少于1000行的Excel
     */
    @org.junit.Test
    public void readLessThan1000Row() {
        String filePath = "/Users/Downloads/team3toolself/excelToolTest.xlsx";
        List<Object> objects = ExcelUtil.readLessThan1000Rows(filePath);
        objects.forEach(System.out::println);
    }

    /**
     * 读取少于1000行的excel,可以指定sheet和从第几行读取
     */
    @org.junit.Test
    public void readLessThan1000RowBySheet() {
        String filePath = "/Users/lanan/Downloads/team3toolself/excelToolTest.xlsx";
        Sheet sheet = new Sheet(1, 1);
        List<Object> objects = ExcelUtil.readMoreThan1000RowBySheet(filePath, sheet);
        objects.forEach(System.out::println);
    }

    /**
     * 读取大于1000行的Excel
     * 带sheet参数的方法可以参照readLessThan1000RowsBySheet()
     */
    @org.junit.Test
    public void readMoreThan1000Row() {
        String filePath = "/Users/lanan/Downloads/team3toolself/excelToolTest1.xlsx";
        List<Object> objects = ExcelUtil.readMoreThan1000Row(filePath);
        objects.forEach(System.out::println);

    }


    @org.junit.Test
    public void writeBySimple() throws FileNotFoundException {
        String filePath = "/Users/lanan/Downloads/team3toolself/excelToolTest2.xlsx";
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111", "222", "333"));
        data.add(Arrays.asList("111", "222", "333"));
        data.add(Arrays.asList("111","222","333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath, data, head);

    }


    /**
     * 生成excle, 带用模型
     * 带sheet参数的方法可参照测试方法readLessThan1000RowBySheet()
     */
    public void writeWithTemplate() {
        String filePath="/Users/lanan/Downloads/team3toolself/excelToolTest3.xlsx";
        ArrayList<TableHeaderExcelProperty> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
            tableHeaderExcelProperty.setName("Yno" + i);
            tableHeaderExcelProperty.setAge(25 + i);
            tableHeaderExcelProperty.setSchool("廊坊师范学院"+i);
            data.add(tableHeaderExcelProperty);
        }
        ExcelUtil.writeWithTemplate(filePath,data);
    }

    /**
     * 生成excel ,带用模型,带多个sheet
     */
    @org.junit.Test
    public void writeWithMultipleSheel(){
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableHeaderExcelProperty> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
                tableHeaderExcelProperty.setName("cmj" + i);
                tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);

        }

        ExcelUtil.writeWithMultipleSheel("/Users/lanan/Downloads/team3toolself/excelToolTest4.xlsx",list1);

    }

    /*******************匿名内部类，实际开发中该对象要提取出去**********************/

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TableHeaderExcelProperty extends BaseRowModel {

        /**
         * value:表头名
         * Index:列的号,0表示第一列
         */
        @ExcelProperty(value = "姓名", index = 0)
        private String name;

        @ExcelProperty(value = "年龄", index = 1)
        private int age;

        @ExcelProperty(value = "学校",index = 2)
        private String school;
    }

}

