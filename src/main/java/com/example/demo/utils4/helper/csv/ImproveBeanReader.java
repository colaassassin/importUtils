package com.example.demo.utils4.helper.csv;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ITokenizer;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;


public class ImproveBeanReader extends CsvBeanReader {

    public ImproveBeanReader(Reader reader, CsvPreference preferences) {
        super(reader, preferences);
    }

    public ImproveBeanReader(ITokenizer tokenizer, CsvPreference preferences) {
        super(tokenizer, preferences);
    }

    public boolean readNextRow() throws IOException {
        return this.readRow();
    }
}
