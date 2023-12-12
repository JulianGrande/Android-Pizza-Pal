package com.beulah.cs213p5;

import java.util.ArrayList;

/**
 * Factory method which creates a pizza
 * @author Julian Grande, Vansh Sharma
 */
public class PizzaMaker {

    /**
     * Creates a pizza given a type with default values
     * @param pizzaType type of pizza as a String
     * @return pizza object
     */
    public static Pizza createPizza(String pizzaType){
        ArrayList<Topping> list = new ArrayList<>();
        Pizza pizza = null;
        switch (pizzaType){
            case ("deluxe"): pizza = new Deluxe(Size.SMALL, false, false); break;
            case ("supreme"): pizza = new Supreme(Size.SMALL, false, false); break;
            case ("seafood"): pizza = new Seafood(Size.SMALL, false, false); break;
            case ("meatzza"): pizza = new Meatzza(Size.SMALL, false, false); break;
            case ("pepperoni"): pizza = new Pepperoni(Size.SMALL, false, false); break;
            case ("byo"): pizza = new BuildYourOwn(list, Size.SMALL, Sauce.TOMATO, false, false ); break;
            case ("buffalo chicken"): pizza = new BuffaloChicken(Size.SMALL, false, false); break;
            case ("margharita"): pizza = new Margharita(Size.SMALL, false, false); break;
            case ("veggie"): pizza = new Veggie(Size.SMALL, false, false); break;
            case ("hawaiian"): pizza = new Hawaiian(Size.SMALL, false, false); break;
            case ("rutgers special"): pizza = new RutgersSpecial(Size.SMALL, false, false); break;
        }
        return pizza;
    }
}