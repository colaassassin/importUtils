package com.example.demo.utils4.helper.exception;


public class ConvertException extends Exception {

    private int row;
    private String title;

    public ConvertException(String message, Throwable cause, int row, String title) {
        super(message, cause);
        this.row = row;
        this.title = title;
    }

    public int getRow() {
        return row;
    }

    public String getTitle() {
        return title;
    }
}
