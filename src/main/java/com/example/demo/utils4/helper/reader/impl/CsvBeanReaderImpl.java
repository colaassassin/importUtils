package com.example.demo.utils4.helper.reader.impl;


import com.example.demo.utils4.helper.annotation.resolver.InputBeanResolver;
import com.example.demo.utils4.helper.csv.ImproveBeanReader;
import com.example.demo.utils4.helper.exception.ConvertException;
import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;
import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.reader.BeanReader;
import com.example.demo.utils4.helper.util.BeanRefelectUtils;
import com.example.demo.utils4.helper.util.StringUtils;
import com.example.demo.utils4.helper.validation.AbstractPostValidator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.ObjectUtils;
import org.supercsv.prefs.CsvPreference;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CsvBeanReaderImpl implements BeanReader {

    private Reader reader;

    private ImproveBeanReader beanReader;


    private InputBeanResolver resolver;

    private Map<String, Integer> titleCellIndexMap;

    private AbstractPostValidator postValidator;

    static Pattern compile = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2}");
    static Pattern compile2 = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2} [0-5]?[0-9]?:[0-5]?[0-9]?:[0-5]?[0-9]?");
    static Map<Pattern, String> map = new HashMap<Pattern, String>(){{
        put(compile,"yyyy/MM/dd");
        put(compile2, "yyyy/MM/dd HH:mm:ss");
    }};

    public CsvBeanReaderImpl(Reader reader) {
        beanReader = new ImproveBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);
    }

    @Override
    public void setPostValidator(AbstractPostValidator postValidator) {
        this.postValidator = postValidator;
    }

    @Override
    public <T> List<T> read(Class<T> beanType) throws ExcelCsvHelperException, InputValidationException, ConvertException {
        if (resolver == null) {
            resolver = InputBeanResolver.getResolerByBeanType(beanType);
        }
        // 解析标题
        readTitleRow();

        List<T> beans = new ArrayList<>();
        while (true) {
            // 读下一行
            try {
                if (!beanReader.readNextRow()) {
                    break;
                }
            } catch (Exception e) {
                throw new ExcelCsvHelperException("read next row fail", e);
            }

            T bean;
            try {
                bean = beanType.newInstance();
            } catch (Exception e) {
                throw new ExcelCsvHelperException("instantiation bean fail", e);
            }
            for (String title : resolver.getTitleFieldConfigMap().keySet()) {
                Integer cellIndex = titleCellIndexMap.get(title);
                if (cellIndex == null) {
                    throw new ExcelCsvHelperException("nonexist title : " + title);
                }


                Object cellValue = beanReader.get(cellIndex);
                // 设置bean
                try {
                    InputBeanResolver.FieldConfig fieldConfig = resolver.getTitleFieldConfigMap().get(title);
                    if(fieldConfig.getField().getType() == Date.class){
                        boolean Date_flag = false;
                        for (Pattern p : map.keySet()){
                            Matcher m = p.matcher(String.valueOf(cellValue));
                            if (m.matches()){
                                SimpleDateFormat sdf = new SimpleDateFormat(map.get(p));
                                cellValue = sdf.parse(String.valueOf(cellValue));
                                System.out.println("时间转换格式为：" + map.get(p) + "     结果为：" + cellValue);
                                Date_flag = true;
                                break;
                            }
                        }
                        if (!Date_flag)
                            cellValue = null;
                    }
                    BeanRefelectUtils.setFieldValue(bean, fieldConfig.getField(), cellValue, resolver.getTitleDeserializerConfigMap().get(title));
                } catch (Exception e) {
                    throw new ConvertException("convert bean field value fail", e, beanReader.getRowNumber(), title);
                }
                // 单元格校验
                Object fieldValue = BeanRefelectUtils.getBeanFieldValueByGetterMethod(bean, resolver.getTitleFieldConfigMap().get(title).getField(), beanType);
                for (InputBeanResolver.ValidatorConfig validatorConfig : resolver.getTitleValidatorConfigsMap().get(title)) {
                    validatorConfig.getValidator().validate(resolver.getTitleFieldConfigMap().get(title).getField().getType(), fieldValue, validatorConfig.getMessage(), beanReader.getRowNumber(), cellIndex, validatorConfig.getArgs());
                }
            }
            if (postValidator != null) {
                postValidator.postValidate(beanReader.getRowNumber(), bean, beans);
            }
            beans.add(bean);
        }

        return beans;
    }

    @Override
    public void close() throws ExcelCsvHelperException {
        try {
            beanReader.close();
        } catch (Exception e) {
            throw new ExcelCsvHelperException("close fail", e);
        }
    }

    /**
     *  解析标题
     */
    private void readTitleRow() throws ExcelCsvHelperException {
        if (titleCellIndexMap != null) {
            return ;
        }

        try {
            titleCellIndexMap = new HashMap<>();
            String titles[] = beanReader.getHeader(true);
            for (int i = 0; i < titles.length; i++) {
                titleCellIndexMap.put(titles[i], i + 1);
            }
        } catch (Exception e) {
            throw new ExcelCsvHelperException("read title row fail, ", e);
        }
    }
}
