package com.beulah.cs213p5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpecialtyPizzaActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ArrayList<Items> pizzas = new ArrayList<>();
    private int [] images = {R.drawable.deluxe, R.drawable.meatzza, R.drawable.pepperoni,
                                R.drawable.seafood, R.drawable.supreme, R.drawable.buffalochicken,
                                R.drawable.margharita, R.drawable.veggie, R.drawable.hawaiian,
                                R.drawable.rutgers_special};

    //Speciality pizza stuff here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        Intent intent = getIntent();
        Cashier cashier = Cashier.Cashier();
        recycler = findViewById(R.id.recycler);
        //setUpMenuNames
        //adapter
        //linear layout
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpMenuItems(){

        String [] pizzaNames = getResources().getStringArray(R.array.specialty_pizzas);
        //pizza maker make pizza, pizza.price, add all the other pizzas as classes and to pizza maker
        for(int i = 0; i < pizzaNames.length; i++){

        }

    }
}
