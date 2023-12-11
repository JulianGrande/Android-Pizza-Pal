package com.beulah.cs213p5;
import java.util.*;

/**
 * Abstract class that all pizzas are based on
 * @author Julian Grande, Vansh Sharma
 */
public abstract class Pizza {

    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Constructor which is called by all subclasses
     * @param toppings array list of toppings as enum constants
     * @param size size of the pizza as an enum constant
     * @param sauce sauce of the pizza as an enum constant
     * @param extraSauce boolean which dictates if pizza has extra sauce
     * @param extraCheese boolean which dictates if pizza has extra cheese
     */
    Pizza(ArrayList<Topping> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese){
        this.toppings = toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * Calculate price of the pizza based on size and extras
     * @return price as a double
     */
    public abstract double price();

    /**
     * Setter method that changes size of pizza
     * @param size size of pizza as an enum constant
     */
    public abstract void setSize(Size size);

    /**
     * Getter method that returns size of pizza
     * @return size as an enum constant
     */
    public abstract Size getSize();

    /**
     * Setter method that changes extraSauce boolean
     * @param sauce boolean
     */
    public abstract void setExtraSauce(boolean sauce);

    /**
     * Setter method that changes extraCheese boolean
     * @param cheese boolean
     */
    public abstract void setExtraCheese(boolean cheese);

    public abstract ArrayList<Topping> getToppings();

}