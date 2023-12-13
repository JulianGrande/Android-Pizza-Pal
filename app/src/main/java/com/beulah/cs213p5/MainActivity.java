package com.beulah.cs213p5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView mainSP = findViewById(R.id.mainSpecialtyText); mainSP.setKeyListener(null);
        TextView mainBYO = findViewById(R.id.mainBYOText); mainBYO.setKeyListener(null);
        TextView mainYO = findViewById(R.id.mainYourOrderText); mainYO.setKeyListener(null);
        TextView mainSO = findViewById(R.id.mainStoreOrderText); mainSO.setKeyListener(null);
    }

    /**
     * Default onStart method
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Default onResume method
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Helper method for the specialty pizza image listener that redirects to the specialty recycler
     */
    private void handleSpecialtyPizza(){
        Intent intent = new Intent(MainActivity.this, SpecialtyPizzaActivity.class);
        startActivity(intent);
    }

    /**
     * Helper method for the build your own pizza image listener that redirects to the byo activity
     */
    private void handleBYOPizza(){
        Intent intent = new Intent(MainActivity.this, BYOPizzaActivity.class);
        startActivity(intent);

    }

    /**
     * Helper method for the your order pizza image listener that redirects to the your order activity
     */
    private void handleYourOrder(){
        Intent intent = new Intent(MainActivity.this, YourOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Helper method for the store order pizza image listener that redirects to the store order activity
     */
    private void handleStoreOrders(){
        Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
        startActivity(intent);
    }
}