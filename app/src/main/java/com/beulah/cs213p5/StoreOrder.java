package com.beulah.cs213p5;

import java.util.ArrayList;

/**
 * Store Order class contains list of orders, and Store order number
 */
public class StoreOrder {

    private static int nextOrderNum = 1;
    private ArrayList<Order> orders;

    /**
     * Constructor instantiates new list of orders
     */
    public StoreOrder(){
        this.orders = new ArrayList<>();
    }

    /**
     * Getter method for list of orders
     * @return list of order objects
     */
    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    /**
     * Adds an order to the list
     * @param order order object that needs to be added
     */
    public void addOrder(Order order){
        int currentOrderNum = order.getOrderNum();
        nextOrderNum = currentOrderNum++;
        orders.add(order);
    }

    /**
     * Getter method for the order number
     * @return number of the current order
     */
    public int getNextOrderNum(){
        return nextOrderNum;
    }

//    public void export(){
//        //export all orders into a txt file here
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save Orders");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
//
//        File file = fileChooser.showSaveDialog(new Stage());
//
//        if(file != null){
//
//            try{
//                PrintWriter pw = new PrintWriter(file);
//                for(int i = 0; i < orders.size(); i++){
//                    pw.print("Order Number: ");
//                    pw.println(orders.get(i).getOrderNum());
//                    pw.print("Total: $");
//                    pw.println(String.format("%.2f", orders.get(i).getOrderTotal()));
//                    ArrayList<Pizza> pizzas = orders.get(i).getPizzas();
//                    for(int j = 0; j < pizzas.size(); j++){
//                        pw.println(pizzas.get(j).toString());
//                    }
//                    pw.println();
//                }
//                pw.close();
//            }
//            catch (Exception e){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Handling File");
//                alert.setHeaderText("Could Not Save File");
//                alert.setContentText("There Was a Problem Saving Your File, Please Try Again.");
//                alert.showAndWait();
//            }
//
//        }
//    }

}