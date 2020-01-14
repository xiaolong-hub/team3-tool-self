package com.dmsdbj.team3.tools.print;

/**
 * @author 李小龙
 * @version 1.0.0
 * @ClassName Client.java
 * @Description TODO
 * @createTime 2020年01月08日 14:40:00
 */
public class Client {
    public static void main(String[] args) {
        //外循环控制打印的行数
        for (int i = 1; i < 10; i++) {
            //内循环控制打印的列数,当j<=i不成立时跳出内循环开始新一轮的外循环.
            for (int j = 1; j <=i; j++) {
                //如果是每行的最后一个计算式子则不打印逗号
                if (j == i) {
                    System.out.print(i + "×" + j + "=" + i * j + "\t");
                } else {
                    System.out.print(i + "×" + j + "=" + i * j +"," + "\t");
                }
            }
            System.out.println();
        }
    }

}
