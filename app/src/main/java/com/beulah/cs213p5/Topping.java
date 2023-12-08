package com.beulah.cs213p5;

/**
 * Enumeration class with possible topping values
 * @author Julian Grande, Vansh Sharma
 */
public enum Topping {

    SAUSAGE("sausage"),
    PEPPERONI("pepperoni"),
    GREEN_PEPPER("green pepper"),
    ONION("onion"),
    MUSHROOM("mushroom"),
    HAM("ham"),
    BLACK_OLIVE("black olive"),
    BEEF("beef"),
    SHRIMP("shrimp"),
    SQUID("squid"),
    CRAB_MEATS("crab meats"),
    BACON("bacon"),
    SPINACH("spinach"),
    BUFFALO_CHICKEN("buffalo chicken"),
    BASIL("basil"),
    PARMESAN_CHEESE("parmesan cheese"),
    PINEAPPLE("pineapple");


    private final String topping;

    /**
     * Constructor that compiler uses
     * @param topping property of enum constants as a String
     */
    Topping(String topping) {
        this.topping = topping;
    }

    /**
     * Getter method for the String representation of an enum constant
     * @return constant as a String
     */
    public String getName() {
        return topping;
    }

    /**
     * Takes string input for a topping and returns it's corresponding enum
     * @param topping input topping in String format.
     * @return the corresponding Topping
     */
    public static Topping fromString(String topping) {
        for (Topping t : Topping.values()) {
            if (t.topping.equalsIgnoreCase(topping)) {
                return t;
            }
        }
        // Handle the case where the input topping doesn't match any enum value
        throw new IllegalArgumentException("No enum constant for topping: " + topping);
    }

}