package com.beulah.cs213p5;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Veggie extends Pizza{

    public static final double SMALL_PRICE = 14.99; //all prices are based around price of small pizza

    /**
     * Argument Constructor
     * @param size size of the pizza
     * @param extraSauce boolean which dictates if the pizza has extra sauce
     * @param extraCheese boolean which dictates if the pizza has extra cheese
     */
    public Veggie(Size size, boolean extraSauce, boolean extraCheese) {
        super(getVeggieToppings(), size, Sauce.TOMATO, extraSauce, extraCheese);
    }

    /**
     * Populates an array list with the toppings for a deluxe pizza
     * @return array list of toppings
     */
    private static ArrayList<Topping> getVeggieToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.BLACK_OLIVE);
        toppings.add(Topping.SPINACH);
        return toppings;
    }

    /**
     * Setter method for the size of the pizza
     * @param size size of pizza
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Getter method for the size of the pizza
     * @return size of the pizza
     */
    public Size getSize(){return this.size;}

    /**
     * Setter method for the boolean extraSauce
     * @param sauce true if extra sauce is selected, else false
     */
    public void setExtraSauce(boolean sauce){
        this.extraSauce = sauce;
    }

    /**
     * Setter method for the boolean extraCheese
     * @param cheese true if extra cheese is selected, else false
     */
    public void setExtraCheese(boolean cheese){
        this.extraCheese = cheese;
    }

    /**
     * Getter method for the toppings of the pizza
     * @return list of toppings of the pizza
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * Calculates the price of the pizza
     * @return price of pizza as a double
     */
    @Override
    public double price() {
        double basePrice = 0;
        switch (this.size.getName()){
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
        if(extraCheese) { basePrice++; }
        if (extraSauce) { basePrice++; }
        return basePrice;
    }

    /**
     * Determines the string tokens that comprise the pizza as a string representation
     * @return pizza as a String
     */
    @NonNull
    @Override
    public String toString() {
        //format
        //[type] Toppings comma separated, size, sauce, extra sauce if true, extra cheese if true, subtotal rounded to 2 places so use .2f string modifier
        //[Deluxe] Sausage, GreenPepper, Onion, Pepperoni, Mushroom, large, tomato, extra sauce, extra cheese, $20.99
        String fString = "[Veggie] ";
        for (Topping topping : getVeggieToppings()) { // [type] topping1, topping2, etc. //changed this from toppings to getDeluxe toppings
            fString += topping.getName() + ", ";
        }
        fString += size.getName() + ", ";
        fString += sauce.getName() + ", ";
        if(extraCheese){fString += "extra cheese, ";}
        if(extraSauce){fString += "extra sauce, ";}
        fString += "$" + String.format("%.2f", price()); // $price.2f
        return fString;
    }

}
