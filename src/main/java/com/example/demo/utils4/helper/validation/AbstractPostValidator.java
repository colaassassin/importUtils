package com.example.demo.utils4.helper.validation;

import com.example.demo.utils4.helper.exception.InputValidationException;


import java.util.List;


public abstract class AbstractPostValidator {

   public abstract void postValidate(int row, Object currentBean, List currentBeans) throws InputValidationException;

}
