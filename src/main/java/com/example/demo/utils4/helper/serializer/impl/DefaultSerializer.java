package com.example.demo.utils4.helper.serializer.impl;


import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;


public class DefaultSerializer extends AbstractCellSerializer {

    @Override
    public Object serialize(Object object, String args[]) {
        return object;
    }
}
