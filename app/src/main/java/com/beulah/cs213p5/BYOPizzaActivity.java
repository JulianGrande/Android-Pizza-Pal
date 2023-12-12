package com.beulah.cs213p5;// BYOPizzaActivity.java

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.Locale;

public class BYOPizzaActivity extends AppCompatActivity {

    private ListView toppingsListView;
    private ListView userToppingsListView;
    private ArrayAdapter<String> toppingsAdapter;
    private ArrayAdapter<String> userToppingsAdapter;

    private String pizzaSize;
    private String pizzaSauce;
    private String selectedTopping;

    private Pizza pizza;
    private Cashier cashier;

    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        
        pizza = PizzaMaker.createPizza("byo");
        cashier = Cashier.Cashier();

        // Initialize ListView and ArrayAdapter for toppings
        toppingsListView = findViewById(R.id.toppingsView);
        initializeToppingsListView();
        // Initialize ListView and ArrayAdapter for user selected toppings
        userToppingsListView = findViewById(R.id.userToppingsView);
        initializeUserToppingsListView();

        setUpAddButton();
        setUpRemoveButton();
        setUpToppingsClickListener();
        setUpUserToppingsClickListener();
        setUpExtraCheeseSwitch();
        setUpExtraSauceSwitch();
        setUpAddToOrderButton();
        setUpSauceSpinner();
        setUpSizeSpinner();

        totalPrice = findViewById(R.id.totalPrice);
    }



    /**
     * INITIALIZERS
     * LISTENERS
     * HANDLERS
     */


    private void setUpSizeSpinner(){
        Spinner sizeSpinner = findViewById(R.id.sizeSpinner); // Replace with your Spinner's ID
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.pizza_sizes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        pizza.setSize(Size.SMALL);

        // LISTENER
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update the private variable pizzaSize based on the selected item
                pizzaSize = parentView.getItemAtPosition(position).toString();
                pizza.setSize(Size.fromString(pizzaSize));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });
    }

    private void setUpSauceSpinner(){
        Spinner sauceSpinner = findViewById(R.id.sauceSpinner); // Replace with your Spinner's ID
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sauce_selection,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sauceSpinner.setAdapter(adapter);
        BuildYourOwn byoPizza = (BuildYourOwn) pizza;
        byoPizza.setSauce(Sauce.TOMATO);

        // Set the item selected listener for the spinner
        sauceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update the private variable pizzaSize based on the selected item
                pizzaSize = parentView.getItemAtPosition(position).toString();
                // You can do further actions based on the selected item if needed
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });
    }

    private void setUpExtraCheeseSwitch() {
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch extraCheeseSwitch = findViewById(R.id.extraSauce); // Replace with your extraCheese radio button's ID

        extraCheeseSwitch.setOnClickListener(v -> {
            pizza.setExtraCheese(extraCheeseSwitch.isActivated());
            // Handle extra cheese selection logic if needed
        });
    }

    private void setUpExtraSauceSwitch() {
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch extraSauceSwitch = findViewById(R.id.extraSauce); // Replace with your extraSauce radio button's ID

        extraSauceSwitch.setOnClickListener(v -> {
            pizza.setExtraSauce(extraSauceSwitch.isActivated());
        });
    }

    private void setUpAddButton() {
        Button addButton = findViewById(R.id.addToppings);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canAddTopping()){
                    onAddButtonClicked();
                    convertToppingToArrayList();
                }
            }
        });
    }

    private void setUpRemoveButton() {
        Button removeButton = findViewById(R.id.removeToppings);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canRemoveTopping()){
                    onRemoveButtonClicked();
                    convertToppingToArrayList();
                }
            }
        });
    }

    private void setUpAddToOrderButton() {
        Button addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADDITIONAL CODE
                resetView();
            }
        });
    }

    private void setUpToppingsClickListener() {
        toppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTopping = toppingsAdapter.getItem(position);
            }
        });
    }

    private void setUpUserToppingsClickListener() {
        userToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTopping = userToppingsAdapter.getItem(position);
            }
        });
    }

    private void onAddButtonClicked() {
        // Add logic to move selected topping from toppingsView to userToppingsView
//        String selectedTopping = (String) toppingsListView.getItemAtPosition(toppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            toppingsAdapter.remove(selectedTopping);
            userToppingsAdapter.add(selectedTopping);
            resetAdapters();
        }
    }

    private void onRemoveButtonClicked() {
        // Add logic to move selected topping from userToppingsView to toppingsView
//        String selectedTopping = (String) userToppingsListView.getItemAtPosition(userToppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            userToppingsAdapter.remove(selectedTopping);
            toppingsAdapter.add(selectedTopping);
            resetAdapters();
        }
    }

    private void initializeToppingsListView(){
        ArrayList<String> allToppings = new ArrayList<>();
        addToppings(allToppings);
        toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allToppings);
        toppingsListView.setAdapter(toppingsAdapter);
        toppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode
    }

    private void initializeUserToppingsListView(){
        ArrayList<String> userSelectedToppings = new ArrayList<>();
        userToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userSelectedToppings);
        userToppingsListView.setAdapter(userToppingsAdapter);
        userToppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode
    }






    /**
     * HELPER FUNCTIONS
     * PUBLIC METHODS
     * OTHER METHODS
     */

    private void resetAdapters(){
        toppingsListView.setAdapter(toppingsAdapter);
        userToppingsListView.setAdapter(userToppingsAdapter);
        selectedTopping = null;
    }

    private boolean canAddTopping(){
        if (userToppingsAdapter.getCount() == 7){
            Toast.makeText(BYOPizzaActivity.this,
                    "You can add a maximum of 7 toppings.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean canRemoveTopping(){
        if (userToppingsAdapter.getCount() <= 3){
            Toast.makeText(BYOPizzaActivity.this,
                    "At least 3 toppings are required.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void convertToppingToArrayList() {
        ArrayList<Topping> returnList = new ArrayList<>();

        for (int i = 0; i < userToppingsAdapter.getCount(); i++) {
            String topping = userToppingsAdapter.getItem(i);

            if (topping != null) {
                Topping toppingEnum = Topping.fromString(topping);
                returnList.add(toppingEnum);
            }
        }
         BuildYourOwn byoPizza = (BuildYourOwn) pizza;
        byoPizza.setToppings(returnList);
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

    private void resetView(){

        initializeToppingsListView();
        initializeUserToppingsListView();
        setUpAddButton();
        setUpRemoveButton();
        setUpToppingsClickListener();
        setUpUserToppingsClickListener();
        setUpExtraCheeseSwitch();
        setUpExtraSauceSwitch();
        setUpAddToOrderButton();
        setUpSauceSpinner();
        setUpSizeSpinner();
    }

    /**
     * Updates the price and changes the text field
     */
    private void updatePrice(){
        BuildYourOwn byoPizza = (BuildYourOwn)pizza;
        totalPrice.setText(String.format(Locale.ENGLISH, "%.2f", byoPizza.price()));
    }
}
