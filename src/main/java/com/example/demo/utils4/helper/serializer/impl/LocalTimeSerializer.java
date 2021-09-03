package com.example.demo.utils4.helper.serializer.impl;

import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class LocalTimeSerializer extends AbstractCellSerializer {

    private static final String DEFAULT_FORMAT = "HH:mm:ss";

    @Override
    public Object serialize(Object object, String args[]) {
        String dateFormat;
        if (args.length > 0) {
            dateFormat = args[0];
        } else {
            dateFormat = DEFAULT_FORMAT;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormat);
        return ((LocalTime)object).format(fmt);
    }
}
