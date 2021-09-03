package com.example.demo.utils3;

import com.example.demo.utils3.PoJo.LineLossData;

import java.util.List;


public class test {
    public static void main(String[] args) {
        List<LineLossData> list = ExcelUtils.getInstance().readCSV2Objects("D:\\Users\\HP\\Desktop\\台区数据\\12-台区线损数据.csv", LineLossData.class);
        System.out.println(list.size());
    }
}
