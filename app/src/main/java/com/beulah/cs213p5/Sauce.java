package com.beulah.cs213p5;

/**
 * Enumeration class with possible sauce values
 * @author Julian Grande, Vansh Sharma
 */
public enum Sauce {

    TOMATO("tomato"),
    ALFREDO("alfredo");
    private final String sauce;

    /**
     * Constructor that compiler uses
     * @param sauce property of enum constants as a String
     */
    Sauce(String sauce) {
        this.sauce = sauce;
    }

    /**
     * Getter method for the String representation of an enum constant
     * @return constant as a String
     */
    public String getName() {
        return sauce;
    }


}