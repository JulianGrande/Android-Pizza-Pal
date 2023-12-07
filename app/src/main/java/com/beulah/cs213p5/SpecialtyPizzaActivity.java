package com.beulah.cs213p5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        Cashier cashier = Cashier.Cashier();
    }

}
