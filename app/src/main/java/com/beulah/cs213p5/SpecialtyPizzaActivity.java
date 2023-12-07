package com.beulah.cs213p5;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpecialtyPizzaActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    //Speciality pizza stuff here
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        Cashier cashier = Cashier.Cashier();

        String[] specialty_pizzas = {"Deluxe", "Meatzza", "Pepperoni", "Seafood",
                                        "Supreme"};
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.
                                                support_simple_spinner_dropdown_item,
                                                specialty_pizzas);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int postion, long id){
        String selectedPizza = spinner.getSelectedItem().toString;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){
        //leave blank
    }


}
