package com.example.demo.utils4.helper.validation.impl;


import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.util.StringUtils;
import com.example.demo.utils4.helper.validation.Validator;


public class NotEmptyValidator implements Validator {

    @Override
    public void validate(Class type, Object value, String message, int row, int col, Object... objs) throws InputValidationException {
        if (type == String.class) {
            if (StringUtils.isEmpty((String)value)) {
                throw new InputValidationException(formatMessage(message, row, col));
            }
        }
    }
}
