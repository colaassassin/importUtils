package com.example.demo.utils4.helper;

import com.example.demo.utils4.helper.PoJo.LineLossData;
import com.example.demo.utils4.helper.PoJo.Qt;
import com.example.demo.utils4.helper.reader.impl.CsvBeanReaderImpl;
import com.example.demo.utils4.helper.reader.impl.ExcelBeanReaderImpl;
import com.example.demo.utils4.helper.util.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try {
            TestPostValidator postValidator = new TestPostValidator();
            FileReader fileReader = new FileReader("D:\\Users\\HP\\Desktop\\台区数据\\12-台区线损数据.csv");
            CsvBeanReaderImpl csvBeanReader = new CsvBeanReaderImpl(fileReader);
            csvBeanReader.setPostValidator(postValidator);
            List<LineLossData> list = csvBeanReader.read(LineLossData.class);
            int i = 0;
            for (LineLossData l : list){
                if(i == 3)
                    break;
                System.out.println(l);
                i++;
            }
            DecimalFormat ds = new DecimalFormat("0");
            System.out.println(ds.format(list.get(0).getREC_ID()).trim());
            csvBeanReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acd() {
        try {
            TestPostValidator postValidator = new TestPostValidator();
            File f=new File("D:\\Users\\HP\\Desktop\\台区数据\\1-台区档案数据.xlsx");
            InputStream inputStream= new FileInputStream(f);
            ExcelBeanReaderImpl csvBeanReader = new ExcelBeanReaderImpl(inputStream);
            csvBeanReader.setPostValidator(postValidator);
            List<Qt> list = csvBeanReader.read(Qt.class);
            list.forEach(System.out::println);
            csvBeanReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
