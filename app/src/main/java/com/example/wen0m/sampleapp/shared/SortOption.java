package com.example.wen0m.sampleapp.shared;


public enum SortOption {

    BY_PRICE(0), BY_APARTS(1), BY_NAME(3);

    private final int option;

    SortOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }
}
