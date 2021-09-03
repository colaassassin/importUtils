package com.example.demo.utils4.helper.reader;



import com.example.demo.utils4.helper.exception.ConvertException;
import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;
import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.validation.AbstractPostValidator;

import java.util.List;


public interface BeanReader extends AutoCloseable {

    /**
     *
     * @param beanType, 待读入的bean类型
     * @return bean列表
     */
    <T> List<T> read(Class<T> beanType) throws ExcelCsvHelperException, InputValidationException, ConvertException;

    /**
     *  不需要再执行读入操作时，需要执行close操作关闭流资源
     */
    void close() throws ExcelCsvHelperException;

    default void setPostValidator(AbstractPostValidator postValidator) {}

}
