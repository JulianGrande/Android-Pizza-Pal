package com.beulah.cs213p5;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * BuildYourOwn Pizza class with support to customize a pizza however the user wants
 * @author Julian Grande, Vansh Sharma
 */
public class BuildYourOwn extends Pizza {

    private static final double SMALL_PRICE = 8.99;

    BuildYourOwn(ArrayList<Topping> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        super(toppings, size, sauce, extraSauce, extraCheese);
    }

    /**
     * Calculates the price of the pizza after all costs factored in.
     * @return the final price of the pizza
     */
    @Override
    public double price() {//each topping after 3 toppings costs an extra $1.49, up to seven toppings
                           //add a listener to gui that requires at least 3 toppings to be selected before allowing you to order a BYO pizza
        double basePrice = 0;
        switch(this.size.getName()){
            case "small":{
                basePrice = SMALL_PRICE;
            }
            case "medium":{
                basePrice = SMALL_PRICE + 2;
            }
            case "large":{
                basePrice = SMALL_PRICE + 4;
            }
        }
//        switch(this.size.getName()){
//            case("small") -> basePrice = SMALL_PRICE;
//            case("medium") -> basePrice = SMALL_PRICE + 2;
//            case("large") -> basePrice = SMALL_PRICE + 4;
//        }
        if(toppings.size() > 3){
            basePrice += (toppings.size() - 3) * 1.49;
        }
        if(extraCheese) { basePrice++; }
        if (extraSauce) { basePrice++; }
        return basePrice;
    }

    /**
     * Convert the pizza into string format
     * @return a string that holds all info about pizza
     */
    @NonNull
    @Override
    public String toString() {
        String fString = "[Build Your Own] ";
        for (Topping topping : toppings) { // [byo] topping1, topping2, etc.
            fString += topping + ", ";
        }
        fString += "$" + String.format("%.2f", price()); // $price.2f
        return fString;
    }

    /**
     * getter for pizza toppings
     * @return all pizza toppings
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * setter for pizza toppings
     * @param toppings to be placed on the pizza
     */
    public void setToppings(ArrayList<Topping> toppings){
        this.toppings = toppings;
    }

    /**
     * Setter for pizza sauce.
     * @param sauce selection for the pizza
     */
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }

    /**
     * setter for pizza size
     * @param size size of pizza as an enum constant
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * getter for pizza size
     * @return pizza size as an enum constant
     */
    @Override
    public Size getSize() {
        return this.size;
    }

    /**
     * setter for extra sauce
     * @param sauce boolean
     */
    public void setExtraSauce(boolean sauce){
        this.extraSauce = sauce;
    }

    /**
     * setter for extra cheese
     * @param cheese boolean
     */
    public void setExtraCheese(boolean cheese){
        this.extraCheese = cheese;
    }

}