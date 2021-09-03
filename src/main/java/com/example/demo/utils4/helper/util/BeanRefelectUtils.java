package com.example.demo.utils4.helper.util;



import com.example.demo.utils3.utils.DateUtils;
import com.example.demo.utils4.helper.annotation.resolver.InputBeanResolver;
import com.example.demo.utils4.helper.deserializer.impl.DefaultDeserializer;
import com.example.demo.utils4.helper.exception.ExcelCsvHelperException;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class BeanRefelectUtils {

    public static String getBeanGetterMethodName(Field field) {
        String filedName = field.getName();
        char firstChar = filedName.charAt(0);
        return "get" + Character.toUpperCase(firstChar) + filedName.substring(1);
    }

    public static String getBeanSetterMethodName(Field field) {
        String filedName = field.getName();
        char firstChar = filedName.charAt(0);
        return "set" + Character.toUpperCase(firstChar) + filedName.substring(1);
    }

    public static Object getBeanFieldValueByGetterMethod(Object object, Field field, Class<?> type) throws ExcelCsvHelperException {
        String getterMethodName = getBeanGetterMethodName(field);
        try {
            Method method = type.getMethod(getterMethodName);
            return method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcelCsvHelperException("getBeanFieldValueByGetterMethod fail");
        }

    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = type;
        while (clazz != Object.class) {
            fields.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    public static Object setBeanFieldValueBySetterMethod(Object object, Field field, Class<?> type, Object value) throws ExcelCsvHelperException {
        String getterMethodName = getBeanSetterMethodName(field);
        try {
            Method method = type.getMethod(getterMethodName, field.getType());
            return method.invoke(object, value);
        } catch (Exception e) {
            throw new ExcelCsvHelperException("setBeanFieldValueBySetterMethod fail", e);
        }

    }

    public static void setFieldValue(Object obj, Field field , Object s, InputBeanResolver.DeserializerConfig deserializerConfig) throws ExcelCsvHelperException {
        // 依次判断是否基本类型
        Object value = null;
        Class<?> fieldType = field.getType();
        if (deserializerConfig.getDeserializer().getClass() != DefaultDeserializer.class) {
            value = deserializerConfig.getDeserializer().deserialize(String.valueOf(s), deserializerConfig.getArgs());
        } else {
            try {
                if (fieldType == Date.class) {
                    value = ObjectUtils.isEmpty(s) ? null : s;
                }else {
                    String fieldValue = s == null ? null : String.valueOf(s);
                    if (fieldType == Byte.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Byte(fieldValue);
                    } else if (fieldType == Byte.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? (byte) 0 : new Byte(fieldValue);
                    } else if (fieldType == Short.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Short(fieldValue);
                    } else if (fieldType == Short.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? (short) 0 : new Short(fieldValue);
                    } else if (fieldType == Integer.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : Integer.parseInt(fieldValue);
                    } else if (fieldType == Integer.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? 0 : Integer.parseInt(fieldValue);
                    } else if (fieldType == Long.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Long(fieldValue);
                    } else if (fieldType == Long.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? 0L : new Long(fieldValue);
                    } else if (fieldType == Float.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Float(fieldValue);
                    } else if (fieldType == Float.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? 0.0 : new Float(fieldValue);
                    } else if (fieldType == Double.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Double(fieldValue);
                    } else if (fieldType == Double.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? 0.0D : new Double(fieldValue);
                    } else if (fieldType == BigDecimal.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new BigDecimal(fieldValue);
                    } else if (fieldType == Boolean.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : Boolean.valueOf(fieldValue);
                    } else if (fieldType == Boolean.TYPE) {
                        value = !StringUtils.isEmpty(fieldValue) && Boolean.parseBoolean(fieldValue);
                    } else if (fieldType == String.class) {
                        value = fieldValue;
                    } else if (fieldType == Character.class) {
                        value = StringUtils.isEmpty(fieldValue) ? null : new Character(fieldValue.charAt(0));
                    } else if (fieldType == Character.TYPE) {
                        value = StringUtils.isEmpty(fieldValue) ? '\0' : new Character(fieldValue.charAt(0));
                    }
                }
            } catch (Exception e) {
                throw new ExcelCsvHelperException("convert base type field obj fail", e);
            }
        }

        setBeanFieldValueBySetterMethod(obj, field, obj.getClass(), value);
    }
}
