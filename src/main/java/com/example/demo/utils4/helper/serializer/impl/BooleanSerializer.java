package com.example.demo.utils4.helper.serializer.impl;


import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;


public class BooleanSerializer extends AbstractCellSerializer {

    private static final int ARGS_LEN = 2;

    @Override
    public Object serialize(Object object, String[] args) {
        Boolean srcObj = (Boolean)object;
        if (args.length == ARGS_LEN) {
            return srcObj.equals(Boolean.TRUE) ? args[0] : args[1];
        } else {
            return srcObj.toString();
        }
    }

}
