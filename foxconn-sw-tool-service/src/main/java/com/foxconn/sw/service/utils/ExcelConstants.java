package com.foxconn.sw.service.utils;

import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;

public interface ExcelConstants {

    interface Font {

        String Arial = "Arial";
        String 標楷體 = "標楷體";
        String ArialUnicodeMS = "Arial Unicode MS";
    }

    interface FontSize {
        Integer Size_6 = 6;
        Integer Size_8 = 8;
        Integer Size_9 = 9;
        Integer Size_10 = 10;
        Integer Size_12 = 12;
        Integer Size_14 = 14;
        Integer Size_16 = 16;
    }

    interface Color {
        // RGB值 -标题颜色
        XSSFColor Title_Color = new XSSFColor(new java.awt.Color(153, 204, 255), new DefaultIndexedColorMap());
        // RGB值 -计划颜色
        XSSFColor Plan_Color = new XSSFColor(new java.awt.Color(255, 204, 153), new DefaultIndexedColorMap());
        // RGB值 -当前周颜色
        XSSFColor Current_Color = new XSSFColor(new java.awt.Color(204, 255, 204), new DefaultIndexedColorMap());
        // RGB值 -历史颜色
        XSSFColor History_Color = new XSSFColor(new java.awt.Color(192, 192, 192), new DefaultIndexedColorMap());

        // RGB值 -计划颜色
        XSSFColor S_Plan_Color = new XSSFColor(new java.awt.Color(255, 204, 153), new DefaultIndexedColorMap());
        // RGB值 -历史颜色
        XSSFColor S_History_Color = new XSSFColor(new java.awt.Color(192, 192, 192), new DefaultIndexedColorMap());
        // RGB值 -计划颜色
        XSSFColor Items_Color = new XSSFColor(new java.awt.Color(255, 153, 204), new DefaultIndexedColorMap());

        // RGB值 -计划颜色
        XSSFColor Red_Color = new XSSFColor(new java.awt.Color(255, 0, 0), new DefaultIndexedColorMap());

        // RGB值 -计划颜色
        XSSFColor Reach_Color = new XSSFColor(new java.awt.Color(51, 153, 102), new DefaultIndexedColorMap());

        // RGB值 -计划颜色
        XSSFColor Gray_Color = new XSSFColor(new java.awt.Color(192, 192, 192), new DefaultIndexedColorMap());
    }

}
