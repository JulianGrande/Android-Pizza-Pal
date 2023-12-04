package com.beulah.cs213p5;

/**
 * Singleton class that allows controller classes to access the same order and store order
 * @author Julian Grande, Vansh Sharma
 */
public class Cashier {

    private static Cashier cashier = null;
    private static Order order = null;
    private static StoreOrder storeOrder = null;

    /**
     * Constructor Class defines new order and store order
     */
    private Cashier(){
        order = new Order();
        storeOrder = new StoreOrder();
    }

    /**
     * public class allows for controller classes to access same static instance of the class
     * @return
     */
    public static Cashier Cashier(){

        if(cashier == null){
            cashier = new Cashier();
        }

        return cashier;
    }

    /**
     * Adds a pizza to the current order
     * @param pizza pizza to be added to the order
     */
    public void addToOrder(Pizza pizza){
        order.addPizza(pizza);
    }

    /**
     * Adds an order to the store order and instantiates a new order
     */
    public void addToStore(){
        storeOrder.addOrder(order);
        order = new Order();
    }

    /**
     * Getter method for order object
     * @return Order obeject
     */
    public Order getOrder(){
        return this.order;
    }

    /**
     * Getter method for store order object
     * @return StoreOrder object
     */
    public StoreOrder getStoreOrder(){
        return this.storeOrder;
    }

}