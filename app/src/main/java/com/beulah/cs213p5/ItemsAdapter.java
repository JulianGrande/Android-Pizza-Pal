package com.beulah.cs213p5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for instantiating a Recycler View, allows for each row to open a new activity to add a pizza to the order
 * @author Julian Grande, Vansh Sharma
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {

    private Context context;
    private ArrayList<Items> items;

    /**
     * parameterized constructor
     * @param context current context
     * @param items list of items
     */
    public ItemsAdapter(Context context, ArrayList<Items> items){
        this.context = context;
        this.items = items;
    }

    /**
     * Inflator method for row layout for items in Recycler
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return view
     */
    @NonNull
    @Override
    public ItemsAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ItemsHolder(view);
    }

    /**
     * Assigns data/values to each element of the row view for its given position/index
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsHolder holder, int position) {

        holder.pizza_name.setText(items.get(position).getPizzaName());
        holder.pizza_image.setImageResource(items.get(position).getPizzaImage());
        holder.pizza_row.setText(String.format("%.2f", items.get(position).getPizzaPrice()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecialtyExtraActivity.class);
                intent.putExtra("ITEM", items.get(holder.getAbsoluteAdapterPosition()).getPizzaName());
                intent.putExtra("PICID", items.get(holder.getAbsoluteAdapterPosition()).getPizzaImage());
                context.startActivity(intent);
            }
        });

    }

    /**
     * get number of items in the items list
     * @return number of items in list
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Row view layout file similar to onCreate method
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder{

        private TextView pizza_name;
        private ImageView pizza_image;
        private TextView pizza_row;
        private ConstraintLayout parentLayout;

        public ItemsHolder(@NonNull View itemView){
            super(itemView);
            //make simple view for one pizza and add these attributes,
            //name, image, and row as price
            pizza_name = itemView.findViewById(R.id.pizza_flavor);
            pizza_image = itemView.findViewById(R.id.im_item);
            pizza_row = itemView.findViewById(R.id.pizza_price);
            parentLayout = itemView.findViewById(R.id.rowLayout);
        }

    }
}
