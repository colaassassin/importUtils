package com.example.demo.utils4.helper.deserializer.impl;


import com.example.demo.utils4.helper.deserializer.AbstractCellDeserializer;


public class BooleanDeserializer extends AbstractCellDeserializer {

    private static final int ARGS_LEN = 2;

    @Override
    public Boolean deserialize(String cellValue, String[] args) {
        if (args.length == ARGS_LEN) {
            return cellValue.equals(args[0]) ? Boolean.TRUE : Boolean.FALSE;
        } else {
            return Boolean.FALSE;
        }
    }
}
