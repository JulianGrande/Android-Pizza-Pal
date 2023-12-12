package com.beulah.cs213p5;// BYOPizzaActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    private CheckBox extraCheese;
    private CheckBox extraSauce;
    private TextView totalPrice;

    /**
     * First time creation handler for page.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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
        updatePrice();
    }

    /**
     * Initialize the size spinner.
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
        BuildYourOwn byoPizza = (BuildYourOwn) pizza;
        byoPizza.setSize(Size.SMALL);

        // LISTENER
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Update the private variable pizzaSize based on the selected item
                pizzaSize = parentView.getItemAtPosition(position).toString();
                BuildYourOwn byoPizza = (BuildYourOwn) pizza;
                byoPizza.setSize(Size.fromString(pizzaSize));
                updatePrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });
    }

    /**
     *  Initialize the Sauce Spinner.
     */
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
                updatePrice();
                // You can do further actions based on the selected item if needed
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });
    }

    /**
     * Initialize the extra cheese checkbox.
     */
    private void setUpExtraCheeseSwitch() {
        extraCheese = findViewById(R.id.extraCheese); // Replace with your extraCheese radio button's ID

        extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the CheckBox state change here
                pizza.setExtraCheese(isChecked);
                updatePrice();
            }
        });
    }

    /**
     * Initialize the extra sauce checkbox.
     */
    private void setUpExtraSauceSwitch() {
        extraSauce = findViewById(R.id.extraSauce); // Replace with your extraSauce radio button's ID

        extraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the CheckBox state change here
                pizza.setExtraSauce(isChecked);
                updatePrice();
            }
        });
    }

    /**
     * Initialize the Add button.
     */
    private void setUpAddButton() {
        Button addButton = findViewById(R.id.addToppings);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canAddTopping()){
                    onAddButtonClicked();
                    convertToppingToArrayList();
                    updatePrice();
                }
            }
        });
    }

    /**
     * Initialize the Remove button.
     */
    private void setUpRemoveButton() {
        Button removeButton = findViewById(R.id.removeToppings);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canRemoveTopping()){
                    onRemoveButtonClicked();
                    convertToppingToArrayList();
                    updatePrice();
                }
            }
        });
    }

    /**
     * Initialize the add to order button.
     */
    private void setUpAddToOrderButton() {
        Button addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ADDITIONAL
                BuildYourOwn byoPizza = (BuildYourOwn) pizza;
                if(byoPizza.toppings.size() < 3){
                    Toast.makeText(BYOPizzaActivity.this, "You need at least 3 toppings!", Toast.LENGTH_SHORT).show();
                    return;
                }
                cashier.addToOrder(byoPizza);
                resetView();
                finish();
            }
        });
    }

    /**
     * Create a listener for the toppings adapter.
     */
    private void setUpToppingsClickListener() {
        toppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTopping = toppingsAdapter.getItem(position);
            }
        });
    }

    /**
     * Create a listener for the selected toppings adapter.
     */
    private void setUpUserToppingsClickListener() {
        userToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTopping = userToppingsAdapter.getItem(position);
            }
        });
    }

    /**
     * Transfer topping to selected topping adapter.
     */
    private void onAddButtonClicked() {
        // Add logic to move selected topping from toppingsView to userToppingsView
//        String selectedTopping = (String) toppingsListView.getItemAtPosition(toppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            toppingsAdapter.remove(selectedTopping);
            userToppingsAdapter.add(selectedTopping);
            resetAdapters();
        }
    }

    /**
     * Transfer topping back list of toppings.
     */
    private void onRemoveButtonClicked() {
        // Add logic to move selected topping from userToppingsView to toppingsView
//        String selectedTopping = (String) userToppingsListView.getItemAtPosition(userToppingsListView.getSelectedItemPosition());
        if (selectedTopping != null) {
            userToppingsAdapter.remove(selectedTopping);
            toppingsAdapter.add(selectedTopping);
            resetAdapters();
        }
    }

    /**
     * Initialize the ListView for toppings.
     */
    private void initializeToppingsListView(){
        ArrayList<String> allToppings = new ArrayList<>();
        addToppings(allToppings);
        toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allToppings);
        toppingsListView.setAdapter(toppingsAdapter);
        toppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode
    }

    /**
     * Initialize the ListView for selected toppings.
     */
    private void initializeUserToppingsListView(){
        ArrayList<String> userSelectedToppings = new ArrayList<>();
        userToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userSelectedToppings);
        userToppingsListView.setAdapter(userToppingsAdapter);
        userToppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Enable single choice mode
    }

    /**
     * Reset the ListView adapters after topping switch.
     */
    private void resetAdapters(){
        toppingsListView.setAdapter(toppingsAdapter);
        userToppingsListView.setAdapter(userToppingsAdapter);
        selectedTopping = null;
    }

    /**
     * Check if a topping can be added
     * @return true if it can be added and false otherwise.
     */
    private boolean canAddTopping(){
        if (userToppingsAdapter.getCount() == 7){
            Toast.makeText(BYOPizzaActivity.this,
                    "You can add a maximum of 7 toppings.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Check if a topping can be removed (if there's at least 4 toppings left)
     * @return true if topping can be removed and false otherwise.
     */
    private boolean canRemoveTopping(){
        if (userToppingsAdapter.getCount() <= 3){
            Toast.makeText(BYOPizzaActivity.this,
                    "At least 3 toppings are required.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Convert topping adapters to an arraylist so it can be inputted into the pizza class.
     */
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

    /**
     * Simple function to add all available toppings to the ArrayList.
     * @param allToppings Arraylist to which the toppings need to be added.
     */
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

    /**
     * Reset the view after order is complete.
     */
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
        extraCheese.setChecked(false);
        extraSauce.setChecked(false);
        pizza = PizzaMaker.createPizza("byo");
        updatePrice();
    }

    /**
     * Updates the price and changes the text field
     */
    private void updatePrice(){
        BuildYourOwn byoPizza = (BuildYourOwn)pizza;
        totalPrice.setText(String.format("%.2f", byoPizza.price()));
    }
}
