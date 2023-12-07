package com.beulah.cs213p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Main Menu Activity that handles logic for navigating the app
 * @author Julian Grande, Vansh Sharma
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cashier cashier = Cashier.Cashier();

        ImageView specialty = findViewById(R.id.specialtyButton);
        ImageView byo = findViewById(R.id.byoButton);
        ImageView yourOrder = findViewById(R.id.yourOrderButton);
        ImageView storeOrder = findViewById(R.id.storeOrderButton);

        specialty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSpecialtyPizza();
            }
        });

        byo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBYOPizza();
            }
        });

        yourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleYourOrder();
            }
        });

        storeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStoreOrders();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void handleSpecialtyPizza(){
        Intent intent = new Intent(MainActivity.this, SpecialtyPizzaActivity.class);
        startActivity(intent);
    }

    private void handleBYOPizza(){

    }

    private void handleYourOrder(){

    }

    private void handleStoreOrders(){

    }
}