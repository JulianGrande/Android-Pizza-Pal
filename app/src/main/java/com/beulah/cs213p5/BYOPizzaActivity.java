package com.beulah.cs213p5;// BYOPizzaActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BYOPizzaActivity extends AppCompatActivity {

    private ListView toppingsListView;
    private ListView userToppingsListView;
    private ArrayAdapter<String> toppingsAdapter;
    private ArrayAdapter<String> userToppingsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);

        // Initialize ListView and ArrayAdapter for toppings
        toppingsListView = findViewById(R.id.toppingsView);
        ArrayList<String> allToppings = new ArrayList<>();
        addToppings(allToppings);
        toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allToppings);
        toppingsListView.setAdapter(toppingsAdapter);
        toppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode

        // Initialize ListView and ArrayAdapter for user selected toppings
        userToppingsListView = findViewById(R.id.userToppingsView);
        ArrayList<String> userSelectedToppings = new ArrayList<>();
        userToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userSelectedToppings);
        userToppingsListView.setAdapter(userToppingsAdapter);
        userToppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode

    }

    private void setUpAddButton() {
        Button addButton = findViewById(R.id.addToppings);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
    }

    private void setUpRemoveButton() {
        Button removeButton = findViewById(R.id.removeToppings);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveButtonClicked();
            }
        });
    }

//    private void setUpToppingsClickListener() {
//        toppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                handleToppingsListItemClicked();
//            }
//        });
//    }
//
//    private void setUpUserToppingsClickListener() {
//        userToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                handleUserToppingsListItemClicked();
//            }
//        });
//    }

    private void onAddButtonClicked() {
        // Add logic to move selected topping from toppingsView to userToppingsView
        String selectedTopping = (String) toppingsListView.getItemAtPosition(toppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            toppingsAdapter.remove(selectedTopping);
            userToppingsAdapter.add(selectedTopping);
        }
    }

    private void onRemoveButtonClicked() {
        // Add logic to move selected topping from userToppingsView to toppingsView
        String selectedTopping = (String) userToppingsListView.getItemAtPosition(userToppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            userToppingsAdapter.remove(selectedTopping);
            toppingsAdapter.add(selectedTopping);
        }
    }




    private void addToppings(ArrayList<String> allToppings){
        allToppings.add("Sausage");
        allToppings.add("Pepperoni");
        allToppings.add("Green Pepper");
        allToppings.add("Onion");
        allToppings.add("Mushroom");
        allToppings.add("Ham");
        allToppings.add("Bacon");
        allToppings.add("Black Olive");
        allToppings.add("Beef");
        allToppings.add("Spinach");
        allToppings.add("Shrimp");
        allToppings.add("Squid");
        allToppings.add("Crab Meat");
        allToppings.add("Buffalo Chicken");
    }
}
