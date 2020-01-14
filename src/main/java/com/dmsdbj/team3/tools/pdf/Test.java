package com.dmsdbj.team3.tools.pdf;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2020年01月14日 08:52:00
 */
public class Test {
    public static void main(String[] args) throws IOException, DocumentException {
        String path = "/Users/lanan/Downloads/team3toolself/excelToolTest.xlsx";
        File file = new File(path);
        file.createNewFile();
        new PdfUtil(file).generatePDF();
    }
}
