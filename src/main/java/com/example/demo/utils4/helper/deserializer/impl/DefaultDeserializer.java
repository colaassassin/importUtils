package com.example.demo.utils4.helper.deserializer.impl;

import com.example.demo.utils4.helper.deserializer.AbstractCellDeserializer;


public class DefaultDeserializer extends AbstractCellDeserializer {

    @Override
    public Object deserialize(String cellValue, String[] args) {
        return cellValue;
    }
}
