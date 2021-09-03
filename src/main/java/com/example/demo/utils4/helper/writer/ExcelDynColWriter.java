package com.example.demo.utils4.helper.writer;


import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;


public interface ExcelDynColWriter extends DynamicColumnWriter {

    /**
     *  刷新excel缓存至输出流, 只能调用一次
     */
    void flush() throws ExcelCsvHelperException;
}
