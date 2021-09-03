package com.example.demo.utils4.helper.reader.impl;


import com.example.demo.utils4.helper.annotation.resolver.InputBeanResolver;
import com.example.demo.utils4.helper.deserializer.impl.LocalDateTimeDeserializer;
import com.example.demo.utils4.helper.exception.ConvertException;
import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;
import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.reader.BeanReader;
import com.example.demo.utils4.helper.util.BeanRefelectUtils;
import com.example.demo.utils4.helper.util.StringUtils;
import com.example.demo.utils4.helper.validation.AbstractPostValidator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExcelBeanReaderImpl implements BeanReader {

    private InputStream inputStream;

    private Workbook workbook;

    private Sheet sheet;

    private int currentRow;

    private Map<String, Integer> titleCellIndexMap;

    private InputBeanResolver resolver;

    private AbstractPostValidator postValidator;

    static Pattern compile = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2}");
    static Pattern compile2 = Pattern.compile("[1-9]?\\d{3}/\\d{1,2}/\\d{1,2} [0-5]?[0-9]?:[0-5]?[0-9]?:[0-5]?[0-9]?");
    static Map<Pattern, String> map = new HashMap<Pattern, String>(){{
        put(compile,"yyyy/MM/dd");
        put(compile2, "yyyy/MM/dd HH:mm:ss");
    }};

    public ExcelBeanReaderImpl(InputStream inputStream) throws ExcelCsvHelperException {
        try {
            this.inputStream = inputStream;
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException | EncryptedDocumentException e) {
            throw new ExcelCsvHelperException("create workbook fail", e);
        }

        sheet = workbook.getSheetAt(0);
        // 数据行从第二行开始
        currentRow = 1;
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
        for (int i = currentRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
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
                Cell cell = row.getCell(cellIndex);
                // 设置bean
                try {
                    Object cellValue = "";
                    InputBeanResolver.FieldConfig fieldConfig = resolver.getTitleFieldConfigMap().get(title);
                    if(fieldConfig.getField().getType() == Date.class){
                        if(cell.getCellType().equals(CellType.STRING))
                        {
                            if(getCellStringValue(cell).equals(""))
                                cellValue = getCellStringValue(cell);
                            else{
//                                LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer();
//                                cellValue = localDateTimeDeserializer.deserialize(String.valueOf(getCellStringValue(cell)), arg);
                                for (Pattern p : map.keySet()){
                                    Matcher m = p.matcher(getCellStringValue(cell));
                                    if (m.matches()){
                                        SimpleDateFormat sdf = new SimpleDateFormat(map.get(p));
                                        cellValue = sdf.parse(getCellStringValue(cell));
                                        System.out.println("时间转换格式为：" + map.get(p) + "     结果为：" + cellValue);
                                        break;
                                    }
                                }
                            }
                        }
                        else
                            cellValue = cell.getDateCellValue();
                    }else{
                        cellValue = getCellStringValue(cell);
                    }
//                  String  cellValue = getCellStringValue(cell);
//                  InputBeanResolver.FieldConfig fieldConfig = resolver.getTitleFieldConfigMap().get(title);
                    BeanRefelectUtils.setFieldValue(bean, fieldConfig.getField(), cellValue, resolver.getTitleDeserializerConfigMap().get(title));
                } catch (Exception e) {
                    throw new ConvertException("convert bean field value fail" + title, e, i + 1, title);
                }
                // 单元格校验
                Object fieldValue = BeanRefelectUtils.getBeanFieldValueByGetterMethod(bean, resolver.getTitleFieldConfigMap().get(title).getField(), beanType);
                for (InputBeanResolver.ValidatorConfig validatorConfig : resolver.getTitleValidatorConfigsMap().get(title)) {
                    validatorConfig.getValidator().validate(resolver.getTitleFieldConfigMap().get(title).getField().getType(), fieldValue, validatorConfig.getMessage(), i + 1, cellIndex + 1, validatorConfig.getArgs());
                }
            }
            if (postValidator != null) {
                postValidator.postValidate(i + 1, bean, beans);
            }
            beans.add(bean);
        }

        currentRow = sheet.getLastRowNum();
        return beans;
    }

    @Override
    public void close() throws ExcelCsvHelperException {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
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

        Row row = sheet.getRow(0);
        titleCellIndexMap = new HashMap<>();
        for (short i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            String title  = getCellStringValue(cell);
            titleCellIndexMap.put(title, Integer.valueOf(i));
        }
    }

    /**
     *  获取单元格原始内容，已字符串形式返回
     * @return
     */
    private String getCellStringValue(Cell cell) throws ExcelCsvHelperException {
        // 目前只支持NUMERIC、STRING、BOOLEAN类型的单元格
        switch (cell.getCellType()) {
            case BLANK :
                return "";
            case NUMERIC :
                return StringUtils.removeDecimalPartInvalidZero("" + cell.getNumericCellValue());
            case STRING :
                return cell.getStringCellValue();
            case BOOLEAN:
                return "" + cell.getBooleanCellValue();
            default:
                throw new ExcelCsvHelperException("unsupport cell type :" + cell.getCellType().name());
        }
    }

    /**
     *  获取单元格原始内容，已字符串形式返回
     * @return
     */
    private Object getCellValue(Cell cell) throws ExcelCsvHelperException {
        // 目前只支持NUMERIC、STRING、BOOLEAN类型的单元格
        switch (cell.getCellType()) {
            case NUMERIC :
                return cell.getNumericCellValue();
            case STRING :
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                throw new ExcelCsvHelperException("unsupport cell type :" + cell.getCellType().name());
        }
    }

}
