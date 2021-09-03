package com.example.demo.utils4.helper.writer;



import com.example.demo.utils4.helper.dynamiccolumn.ColumnConfig;
import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;

import java.util.List;
import java.util.Map;


interface DynamicColumnWriter extends AutoCloseable {

    /**
     *  写列标题
     * @param columnConfigs, 列配置
     */
    void writeTitle(List<ColumnConfig> columnConfigs) throws ExcelCsvHelperException;

    /**
     *  写列标题
     * @param datas , 数据行，每行为一个Map对象，用标题做key取值
     */
    void write(List<Map<String, Object>> datas) throws ExcelCsvHelperException ;

    /**
     *  关闭writer对应的流, 不需要再执行写入操作时，需要执行close操作
     */
    void close() throws ExcelCsvHelperException;
}
