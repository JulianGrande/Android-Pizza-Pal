package com.beulah.cs213p5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Specialty Pizza selection is done via a Recycler View which is initialized here
 * @author Julian Grande, Vansh Sharma
 */
public class SpecialtyPizzaActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<Items> pizzas = new ArrayList<>();
    private int [] images = {R.drawable.deluxe, R.drawable.meatzza, R.drawable.pepperoni,
                                R.drawable.seafood, R.drawable.supreme, R.drawable.buffalochicken,
                                R.drawable.margharita, R.drawable.veggie, R.drawable.hawaiian,
                                R.drawable.rutgers_special};

    /**
     * get references of all instances of Views defined in the layout file, populates list of pizzas
     * to be displayed in the Recycler View
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        Intent intent = getIntent();
        Cashier cashier = Cashier.Cashier();
        recycler = findViewById(R.id.recycler);
        setUpMenuItems();
        ItemsAdapter adapter = new ItemsAdapter(this, pizzas);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method used to populate array list of pizza info
     */
    private void setUpMenuItems(){
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza pizza;

        String [] pizzaNames = getResources().getStringArray(R.array.specialty_pizzas);
        //pizza maker make pizza, pizza.price, add all the other pizzas as classes and to pizza maker
        for(int i = 0; i < pizzaNames.length; i++){
            pizza = pizzaMaker.createPizza(pizzaNames[i]);
            pizzas.add(new Items(pizzaNames[i], images[i], pizza.price()));
        }

    }
}
