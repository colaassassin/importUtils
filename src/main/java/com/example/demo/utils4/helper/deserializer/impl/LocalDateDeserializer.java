package com.example.demo.utils4.helper.deserializer.impl;



import com.example.demo.utils4.helper.deserializer.AbstractCellDeserializer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateDeserializer extends AbstractCellDeserializer {

    private static final String DEFAULT_FORMAT = "yyyy/MM/dd";

    @Override
    public Object deserialize(String cellValue, String[] args) {
        String dateFormat;
        if (args.length > 0) {
            dateFormat = args[0];
        } else {
            dateFormat = DEFAULT_FORMAT;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(cellValue, timeFormatter);
    }



}
