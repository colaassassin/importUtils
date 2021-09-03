package com.example.demo.utils4.helper;

import com.example.demo.utils4.helper.exception.InputValidationException;
import com.example.demo.utils4.helper.validation.AbstractPostValidator;

import java.util.List;

public class TestPostValidator extends AbstractPostValidator {
    @Override
    public void postValidate(int row, Object currentBean, List currentBeans) {
        System.out.println("当前处理第" + row + "行");
        System.out.println("当前处理的Bean：" + currentBean);
    }
}
