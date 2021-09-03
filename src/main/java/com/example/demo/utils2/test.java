package com.example.demo.utils2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class test {
    public static void main(String[] args)  throws FileNotFoundException {
        File f=new File("D:\\Users\\HP\\Desktop\\台区数据\\2-计量点档案信息.xls");
        InputStream inputStream= new FileInputStream(f);
        ExcelLogs logs =new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
        for(Map m : importExcel){
            System.out.println(m);
        }

    }
}
