package com.dmsdbj.team3.tools.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName PdfUtil.java
 * @Description TODO
 * @createTime 2020年01月14日 08:53:00
 */
public class PdfUtil {
    //建立一个Document对象
    Document document = new Document();
    //设置字体大小
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;

    //设置PDF内容的字体类型和大小
    static {
        BaseFont bfChinese;
        try {
            //设置字体类型
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            //设置字体大小
            headfont = new Font(bfChinese, 10, Font.BOLD);
            keyfont = new Font(bfChinese, 8, Font.BOLD);
            textfont = new Font(bfChinese, 8, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //生成PDF的构造方法
    public PdfUtil(File file) throws FileNotFoundException, DocumentException {
        //设置页面大小
        document.setPageSize(PageSize.A4);
        try {
            //利用反射获取PdfWriter的具体类
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    int maxWidth=520;

    //生成表格内容
    public PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    //生成表头
    public PdfPCell createCell(String value, Font font, int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    //生成表格名称
    public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setPadding(3.0f);
        if(!boderFlag){
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    //创建表格,colNumber为生成的表格列数
    public PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        table.setTotalWidth(maxWidth);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        //表格线的粗细宽度
        table.getDefaultCell().setBorder(1);
        return table;
    }


    //生成具体的PDF
    public void generatePDF() {
        //生成表格具体列数
        PdfPTable table = createTable(4);
        //表格名字
        table.addCell(createCell("学生信息列表：", keyfont,Element.ALIGN_LEFT,4,false));
        //各列头内容
        table.addCell(createCell("姓名", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("年龄", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("性别", keyfont, Element.ALIGN_CENTER));
        table.addCell(createCell("住址", keyfont, Element.ALIGN_CENTER));

        //表格行数
        for (int i = 0; i < 5; i++) {
            table.addCell(createCell("姓名" + i, textfont));
            table.addCell(createCell(i + 15 + "", textfont));
            table.addCell(createCell((1 % 2 == 0) ? "男" : "女", textfont));
            table.addCell(createCell("地址" + i, textfont));
        }
        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

   

}
