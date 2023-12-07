package com.beulah.cs213p5;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialtyPizzaActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Pizza pizza;
    private PizzaMaker pizzaMaker;

    //Speciality pizza stuff here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        Intent intent = getIntent();
        Cashier cashier = Cashier.Cashier();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
