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
            case ("deluxe"): pizza = new Deluxe(Size.SMALL, false, false);
            case ("supreme"): pizza = new Supreme(Size.SMALL, false, false);
            case ("seafood"): pizza = new Seafood(Size.SMALL, false, false);
            case ("meatzza"): pizza = new Meatzza(Size.SMALL, false, false);
            case ("pepperoni"): pizza = new Pepperoni(Size.SMALL, false, false);
            case ("byo"): pizza = new BuildYourOwn(list, Size.SMALL, Sauce.TOMATO, false, false );
            case ("buffalo chicken"): pizza = new BuffaloChicken(Size.SMALL, false, false);
            case ("margharita"): pizza = new Margharita(Size.SMALL, false, false);
            case ("veggie"): pizza = new Veggie(Size.SMALL, false, false);
            case ("hawaiian"): pizza = new Hawaiian(Size.SMALL, false, false);
            case ("rutgers special"): pizza = new RutgersSpecial(Size.SMALL, false, false);
        }
        return pizza;
    }
}