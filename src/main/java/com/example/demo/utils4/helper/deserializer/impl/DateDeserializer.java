package com.example.demo.utils4.helper.deserializer.impl;


import com.example.demo.utils4.helper.deserializer.AbstractCellDeserializer;

import java.text.SimpleDateFormat;


public class DateDeserializer extends AbstractCellDeserializer {

    private static final String DEFAULT_FORMAT = "yyyy/MM/dd";

    @Override
    public Object deserialize(String cellValue, String[] args) {
        String dateFormat;
        if (args.length > 0) {
            dateFormat = args[0];
        } else {
            dateFormat = DEFAULT_FORMAT;
        }

        final SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(dateFormat);

        try {
            return formatter.parse(cellValue);
        } catch (Exception e) {
            return null;
        }


    }
}
