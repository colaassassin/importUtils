package com.example.demo.utils3.utsv;

import com.github.crab2died.converter.ReadConvertible;
import com.github.crab2died.converter.WriteConvertible;

public class DefaultConvertible implements WriteConvertible, ReadConvertible {
    public DefaultConvertible() {
    }

    public Object execRead(String object) {
        throw new UnsupportedOperationException();
    }

    public Object execWrite(Object object) {
        throw new UnsupportedOperationException();
    }
}