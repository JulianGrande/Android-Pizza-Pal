package com.beulah.cs213p5;

import java.util.ArrayList;

/**
 * Order class contains list of pizzas and order number
 * @author Julian Grande, Vansh Sharma
 */
public class Order {

    private static int nextOrderNum = 1;
    private int orderNum;
    private ArrayList<Pizza> pizzas;
    private double orderTotal;

    /**
     * No argument constructor
     */
    public Order(){
        this.orderNum = nextOrderNum++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Getter method for the current order number
     * @return order number
     */
    public int getOrderNum(){
        return this.orderNum;
    }

    /**
     * Getter method for the list of pizzas in the order
     * @return list of pizzas
     */
    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    /**
     * Adds a pizza to the list
     * @param pizza pizza to be added
     */
    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }

    /**
     * Setter method for the computed total for the order
     * @param total total amount including tax as a double
     */
    public void setOrderTotal(double total){
        this.orderTotal = total;
    }

    /**
     * Getter method for the computed total for the order
     * @return total amount including tax as a double
     */
    public double getOrderTotal(){
        return this.orderTotal;
    }

}