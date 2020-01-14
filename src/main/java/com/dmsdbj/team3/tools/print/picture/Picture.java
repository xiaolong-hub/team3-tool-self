package com.dmsdbj.team3.tools.print.picture;

import com.dmsdbj.team3.tools.json.GsonUtil;
import org.w3c.dom.ls.LSOutput;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Picture.java
 * @Description TODO
 * @createTime 2020年01月08日 14:52:00
 */
public class Picture {
    public static void main(String[] args) {
        //外循环控制要打印的行数
        for (int i = 1; i <6 ; i++) {
            //打印每行左侧的空格数
            for (int j = 0; j <=4-i; j++) {
                System.out.print(" ");
            }
            //内循环控制每行打印的个数
            for (int j = 1; j <= 2*i-1; j++) {
                System.out.print("*");
            }
            System.out.println("\t");
        }
    }

}
