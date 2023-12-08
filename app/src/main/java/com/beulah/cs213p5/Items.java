package com.beulah.cs213p5;

/**
 *Item class that defines the data structure of an item to be displayed in the SpecialtyPizzaActivity class Recycler View
 * @author Julian Grande, Vansh Sharma
 */
public class Items {

    private String name;
    private int image;
    private double price;

    /**
     *Parameterized Constructor
     * @param name name of pizza
     * @param image picture of pizza
     * @param price cost of a pizza pre-tax
     */
    public Items(String name, int image, double price){
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getPizzaName(){
        return name;
    }

    public int getPizzaImage(){
        return image;
    }

    public double getPizzaPrice(){
        return price;
    }



}
