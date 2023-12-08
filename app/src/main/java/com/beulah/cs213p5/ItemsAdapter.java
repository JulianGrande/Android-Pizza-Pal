package com.beulah.cs213p5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {

    private Context context;
    private ArrayList<Items> items;

    @NonNull
    @Override
    public ItemsAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ItemsHolder extends RecyclerView.ViewHolder{

        private TextView pizza_name;
        private ImageView pizza_image;
        private TextView pizza_row;
        private Button add_to_order;

        public ItemsHolder(View itemView){
            super(itemView);
            //make simple view for one pizza and add these attributes,
            //name, image, and row as toppings list
            //in seperate xml file follow video:
            //https://youtu.be/x-2qtxmxYE8
            //then go back to special activity and set up listener for selected pizza
            //and populate price text field
            //and handle back end adding pizza to cashier
        }

    }
}
