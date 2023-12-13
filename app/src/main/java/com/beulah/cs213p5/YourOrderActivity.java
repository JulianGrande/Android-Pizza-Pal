package com.beulah.cs213p5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Handles logic for viewing the current order, removing pizzas, and viewing totals and taxes
 * @author Julian Grande, Vansh Sharma
 */
public class YourOrderActivity extends AppCompatActivity {

    private ListView yourOrderList;
    private TextView subtotalYourOrder;
    private TextView taxesYourOrder;
    private TextView totalYourOrder;
    private TextView yourOrderNum;
    private Button cancelOrderYourOrder;
    private Button placeOrderYourOrder;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> ordersAsStrings;
    private Cashier cashier;
    private String pizzaToRemove;
    private ArrayList<Pizza> pizzas;
    private static final double TAX_RATE = 0.06625;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        setAllFields();
        pizzas = new ArrayList<>();
        cashier = Cashier.Cashier();
        if(cashier.getOrder().getPizzas() != null){
            pizzas = cashier.getOrder().getPizzas();
            Toast.makeText(this, pizzas.toString(), Toast.LENGTH_LONG).show();
        }
        ordersAsStrings = new ArrayList<>();
        for(int i = 0; i < pizzas.size(); i++){
            ordersAsStrings.add(pizzas.get(i).toString());
        }
        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersAsStrings);
        yourOrderList.setAdapter(ordersAdapter);
        yourOrderList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        calculatePrices();
        yourOrderNum.setText(Integer.toString(cashier.getOrder().getOrderNum()));
        yourOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pizzaToRemove = (String)parent.getItemAtPosition(position);
            }
        });
        cancelOrderYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDialog();
            }
        });
        placeOrderYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YourOrderActivity.this, "Listener triggered", Toast.LENGTH_SHORT).show();
                cashier.addToStore();
                finish();
            }
        });
    }

    private void removeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Pizza?");
        builder.setMessage("Are you sure you want to remove this pizza?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(pizzaToRemove != null){
                    for(int j = 0; j < pizzas.size(); j++){
                        if(pizzaToRemove.equalsIgnoreCase(pizzas.get(j).toString())){
                            ordersAsStrings.remove(j);
                            pizzas.remove(j);
                            ordersAdapter.notifyDataSetChanged();
                            yourOrderList.setAdapter(ordersAdapter);
                            break;
                        }
                    }
                    calculatePrices();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //leave blank
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setAllFields(){
        yourOrderList = findViewById(R.id.yourOrdersList);
        subtotalYourOrder = findViewById(R.id.subtotalYourOrder);
        taxesYourOrder = findViewById(R.id.taxesYourOrder);
        totalYourOrder = findViewById(R.id.totalYourOrder);
        cancelOrderYourOrder = findViewById(R.id.cancelOrderYourOrder);
        placeOrderYourOrder = findViewById(R.id.placeOrderYourOrder);
        yourOrderNum = findViewById(R.id.yourOrderNum);
    }

    private void calculatePrices(){

        double sub = 0;
        double totalPrice = 0;
        double taxes = 0;

        for(int i = 0; i < pizzas.size(); i++){
            sub = sub + pizzas.get(i).price();
        }

        taxes = sub * TAX_RATE;
        totalPrice = sub+taxes;
        cashier.getOrder().setOrderTotal(totalPrice);

        subtotalYourOrder.setText(String.format("%.2f", sub));
        taxesYourOrder.setText(String.format("%.2f", taxes));
        totalYourOrder.setText(String.format("%.2f", totalPrice));

    }
}
