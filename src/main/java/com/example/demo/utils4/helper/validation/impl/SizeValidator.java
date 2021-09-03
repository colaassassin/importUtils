package com.example.demo.utils4.helper.validation.impl;


import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.validation.Validator;


public class SizeValidator implements Validator {

    @Override
    public void validate(Class type, Object value, String message, int row, int col, Object... objs) throws InputValidationException {
        if (type == String.class && value != null) {
            int len = ((String) value).length();
            int min = (Integer) objs[0];
            int max = (Integer) objs[1];
            if (len < min || len > max) {
                throw new InputValidationException(formatMessage(message, row, col));
            }
        }
    }
}
