package com.beulah.cs213p5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * After making a selection in Recycler View, pizza selection is shown here
 * @author Julian Grande, Vansh Sharma
 */
public class SpecialtyExtraActivity extends AppCompatActivity {

    private Cashier cashier;
    private ImageView pizzaPic;
    private ListView toppings;
    private TextView title;
    private RadioButton extraSauce;
    private RadioButton extraCheese;
    private RadioButton small;
    private RadioButton medium;
    private RadioButton large;
    private Button addToOrder;
    private TextView price;
    private Pizza pizza;
    private PizzaMaker pizzaMaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_extra);
        cashier = Cashier.Cashier();
        pizzaMaker = new PizzaMaker();
        pizzaPic.findViewById(R.id.specialtyExtraPic);
        toppings.findViewById(R.id.specialtyExtraLV);
        title.findViewById(R.id.specialtyExtraTitle);
        extraSauce.findViewById(R.id.specialtyExtraSauce);
        extraCheese.findViewById(R.id.specialtyExtraCheese);
        small.findViewById(R.id.specialtyExtraSmall);
        medium.findViewById(R.id.specialtyExtraMedium);
        large.findViewById(R.id.specialtyExtraLarge);
        addToOrder.findViewById(R.id.specialtyExtraATO);
        price.findViewById(R.id.specialtyExtraPrice);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("ITEM"));
        pizzaPic.setImageResource(intent.getIntExtra("PICID", R.drawable.your_order_pizza));
        pizza = pizzaMaker.createPizza(intent.getStringExtra("ITEM"));
        price.setText(String.format("%.2f", pizza.price()));
    }

    private void extrasListener(RadioGroup group, int ID){
        pizza.setExtraSauce(extraSauce.isActivated());
        pizza.setExtraCheese(extraCheese.isActivated());
        price.setText(String.format("%.2f", pizza.price()));
    }

}
