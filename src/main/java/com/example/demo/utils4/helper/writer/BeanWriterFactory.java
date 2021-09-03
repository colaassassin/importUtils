package com.example.demo.utils4.helper.writer;


import com.example.demo.utils4.helper.type.ExcelType;
import com.example.demo.utils4.helper.writer.impl.CsvBeanWriterImpl;
import com.example.demo.utils4.helper.writer.impl.XlsxExcelBeanWriterImpl;
import org.supercsv.prefs.CsvPreference;

import java.io.OutputStream;


public class BeanWriterFactory {

    /**
     * 根据指定的excel type创建excel writer
     *
     * @param excelType, excel类型, 目前仅支持xlsx格式的writer
     * @param outputStream, excel内容输出流
     */
    public static ExcelBeanWriter createExcelWriter(ExcelType excelType, OutputStream outputStream) {
        switch (excelType) {
            case XLSX_SUFFIX :
                return new XlsxExcelBeanWriterImpl(outputStream);
            default :
                return new XlsxExcelBeanWriterImpl(outputStream);

        }
    }

    /**
     * 创建csv writer
     *
     * @param writer, csv内容输出流
     */
    public static CsvFormatBeanWriter createCsvWriter(CsvPreference csvPreference, java.io.Writer writer) {
        return new CsvBeanWriterImpl(csvPreference, writer);
    }

}
