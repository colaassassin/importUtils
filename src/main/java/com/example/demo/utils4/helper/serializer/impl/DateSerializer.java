package com.example.demo.utils4.helper.serializer.impl;



import com.example.demo.utils4.helper.serializer.AbstractCellSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateSerializer extends AbstractCellSerializer {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    @Override
    public Object serialize(Object object, String args[]) {
        String dateFormat;
        if (args.length > 0) {
            dateFormat = args[0];
        } else {
            dateFormat = DEFAULT_FORMAT;
        }

        final SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(dateFormat);

        return formatter.format((Date) object);
    }
}
