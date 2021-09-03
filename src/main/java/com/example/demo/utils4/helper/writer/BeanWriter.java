package com.example.demo.utils4.helper.writer;



import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;

import java.util.List;


interface BeanWriter extends AutoCloseable {

    /**
     * 将bean列表写入流中
     *
     * @param list 待写入bean列表
     * @param beanType， 待写入的bean类型
     */
    <E> void write(List<E> list, Class<E> beanType) throws ExcelCsvHelperException;

    /**
     *  关闭writer对应的流, 不需要再执行写入操作时，需要执行close操作
     */
    void close() throws ExcelCsvHelperException;

}
