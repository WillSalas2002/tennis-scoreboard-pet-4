package com.will.service;

public enum Point {
    LOVE("00"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");
    private String value;

    Point(String value) {
        this.value = value;
    }

    public Point next() {
        if (this.equals(ADVANTAGE)) {
            throw new IllegalStateException("The current score is AD");
        }
        return Point.values()[this.ordinal() + 1];
    }
}
