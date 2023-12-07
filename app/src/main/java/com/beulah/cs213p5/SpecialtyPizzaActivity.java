package com.beulah.cs213p5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpecialtyPizzaActivity extends AppCompatActivity {

    //Speciality pizza stuff here
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        Cashier cashier = Cashier.Cashier();
    }


}
