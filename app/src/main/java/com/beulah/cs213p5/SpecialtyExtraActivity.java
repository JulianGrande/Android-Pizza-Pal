package com.beulah.cs213p5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
    private RadioGroup sizeGroup;
    private Pizza pizza;
    private PizzaMaker pizzaMaker;
    private ArrayAdapter<String> toppingsAdapter;
    private ArrayList<String> toppingStringList;
    private Intent intent;

    /**
     * Initializes all important fields with values belonging to the current pizza type, and handles adding to order
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_extra);
        setAllFields();
        intent = getIntent();
        setResourcesForFields();
        extraSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setExtraSauce(extraSauce.isActivated());
                price.setText(String.format("%.2f", pizza.price()));
            }
        });
        extraCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pizza.setExtraCheese(extraCheese.isActivated());
                price.setText(String.format("%.2f", pizza.price()));
            }
        });
        sizeGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(small.isActivated()){
                    pizza.setSize(Size.SMALL);
                }
                if(medium.isActivated()){
                    pizza.setSize(Size.MEDIUM);
                }
                if(large.isActivated()){
                    pizza.setSize(Size.LARGE);
                }
                price.setText(String.format("%.2f", pizza.price()));
            }
        });
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashier.addToOrder(pizza);
                Toast.makeText(getApplicationContext(), "Pizza Added To Order!", Toast.LENGTH_SHORT).show();
            }
        });
        populateListViewToppings();
    }


    /**
     * Populates the list view with the toppings of the specialty pizza
     */
    private void populateListViewToppings(){

        ArrayList<Topping> toppingsEnum = pizza.getToppings();
        toppingStringList = new ArrayList<>();
        for(Topping t: toppingsEnum){
            toppingStringList.add(t.getName());
        }
        toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toppingStringList);
        toppings.setAdapter(toppingsAdapter);
        toppings.setChoiceMode(ListView.CHOICE_MODE_NONE);

    }

    /**
     * Helper method that finds ID's for required fields
     */
    private void setAllFields(){
        cashier = Cashier.Cashier();
        pizzaMaker = new PizzaMaker();
        pizzaPic = findViewById(R.id.specialtyExtraPic);
        toppings = findViewById(R.id.specialtyExtraLV);
        title = findViewById(R.id.specialtyExtraTitle);
        extraSauce = findViewById(R.id.specialtyExtraSauce);
        extraCheese = findViewById(R.id.specialtyExtraCheese);
        small = findViewById(R.id.specialtyExtraSmall);
        medium = findViewById(R.id.specialtyExtraMedium);
        large = findViewById(R.id.specialtyExtraLarge);
        addToOrder = findViewById(R.id.specialtyExtraATO);
        price = findViewById(R.id.specialtyExtraPrice);
        sizeGroup = findViewById(R.id.sizeGroup);
    }

    /**
     * Helper method that sets the correct resources for the title, image view, and price fields
     */
    private void setResourcesForFields(){
        title.setText(intent.getStringExtra("ITEM"));
        pizzaPic.setImageResource(intent.getIntExtra("PICID", R.drawable.your_order_pizza));
        if(intent.getStringExtra("ITEM") != null){pizza = pizzaMaker.createPizza(intent.getStringExtra("ITEM"));}
        price.setText(String.format("%.2f", pizza.price()));
    }

}
