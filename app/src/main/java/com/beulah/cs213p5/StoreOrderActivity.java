package com.beulah.cs213p5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity {

    private Cashier cashier;
    private ListView itemList;
    private Spinner orderList;
    private ArrayAdapter<Integer> orderListAdapter;

    private ArrayAdapter<String> pizzas;
    private ArrayList<String> pizzasAsStrings;
    private Order selectedOrder = null;
    private int orderIndex;
    private ArrayList<Order> orders;
    private ArrayList<Integer> orderNumsBackEnd;
    private Button cancelOrder;
    private TextView orderPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        itemList = findViewById(R.id.orderItemsListView);
        orderList = findViewById(R.id.orderPickSpinner);
        cancelOrder = findViewById(R.id.cancelOrder);
        orderPrice = findViewById(R.id.storeOrderPrice);

        cashier = Cashier.Cashier();
        orders = cashier.getStoreOrder().getOrders();
        orderNumsBackEnd = new ArrayList<>();
        for(int i = 0; i < orders.size(); i++){
            orderNumsBackEnd.add(orders.get(i).getOrderNum());
        }
        orderListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumsBackEnd);
        orderListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderList.setAdapter(orderListAdapter);
        orderList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderIndex = orderNumsBackEnd.get(position);
                selectedOrder = orders.get(orderIndex);
                setOrderFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //leave blank
            }
        });
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialog();
            }
        });
    }

    private void cancelDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Order?");
        builder.setMessage("Are you sure you want to remove this order?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(selectedOrder != null){
                    for (int i = 0; i < orders.size(); i++){
                        if(selectedOrder.getOrderNum() == orders.get(i).getOrderNum()){
                            orders.remove(i);
                            orderNumsBackEnd.remove(i);
                            orderListAdapter.notifyDataSetChanged();
                            orderList.setAdapter(orderListAdapter);
                        }
                    }
                    orderPrice.setText("");
                }
            }
        });
    }

    private void setOrderFields(){

        orderPrice.setText(String.format("%.2f", selectedOrder.getOrderTotal()));
        for(int i = 0; i < selectedOrder.getPizzas().size(); i++){
            pizzasAsStrings.add(selectedOrder.getPizzas().get(i).toString());

        }
        pizzas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzasAsStrings);
        itemList.setAdapter(pizzas);
        itemList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

}
