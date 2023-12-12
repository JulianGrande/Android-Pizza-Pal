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

    /**
     * convert sauce string to sauce enum
     * @param sauce string
     * @return sauce enum
     */
    public static Sauce fromString(String sauce) {
        for (Sauce t : Sauce.values()) {
            if (t.sauce.equalsIgnoreCase(sauce)) {
                return t;
            }
        }
        // Handle the case where the input topping doesn't match any enum value
        throw new IllegalArgumentException("No enum constant for: " + sauce);
    }
}