package com.beulah.cs213p5;

/**
 * Enumeration class with possible size values
 */
public enum Size {

    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String size;

    /**
     * Constructor that compiler uses
     * @param size property of enum constants as a String
     */
    Size(String size) {
        this.size = size;
    }

    /**
     * Getter method for the String representation of an enum constant
     * @return constant as a String
     */
    public String getName() {
        return size;
    }

    /**
     * Converts string input for pizza size to it's corresponding Size enum
     * @param size in String format
     * @return corresponding Size enum
     */
    public static Size fromString(String size) {
        for (Size t : Size.values()) {
            if (t.size.equalsIgnoreCase(size)) {
                return t;
            }
        }
        // Handle the case where the input topping doesn't match any enum value
        throw new IllegalArgumentException("No enum constant for size: " + size);
    }
}