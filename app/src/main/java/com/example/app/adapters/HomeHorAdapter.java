package com.example.app.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.models.HomeHorModel;
import com.example.app.models.HomeVerModel;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    ArrayList<HomeHorModel> list;
    Activity activity;
    UpdateVerticalRec updateVerticalRec;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(ArrayList<HomeHorModel> list, Activity activity, UpdateVerticalRec updateVerticalRec) {
        this.list = list;
        this.activity = activity;
        this.updateVerticalRec = updateVerticalRec;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeHorModel mdl = list.get(position);
        holder.image.setImageResource(mdl.getImage());
        holder.text.setText(mdl.getName());

        if (check) {
            ArrayList<HomeVerModel> list = new ArrayList<>();

            list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.pizza_bottle, "Pizza & Bottle", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00",  "60"));
            list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "60"));

            updateVerticalRec.callBack(list, position);
            check = false;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "120"));
                    list.add(new HomeVerModel(R.drawable.pizza_bottle, "Pizza & Bottle", "10:00 - 23:00","120"));
                    list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "120"));
                    list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", " 120"));
                    list.add(new HomeVerModel(R.drawable.pizza, "Pizza", "10:00 - 23:00", "120"));

                    updateVerticalRec.callBack(list, position);
                }
                if (position == 1) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.burger, "Burger", "10:00 - 23:00", "150"));
                    list.add(new HomeVerModel(R.drawable.burger_bottle, "Burger", "10:00 - 23:00", "150"));
                    list.add(new HomeVerModel(R.drawable.burger, "Burger", "10:00 - 23:00",  "150"));
                    list.add(new HomeVerModel(R.drawable.burger, "Burger", "10:00 - 23:00",  "150"));
                    list.add(new HomeVerModel(R.drawable.burger, "Burger", "10:00 - 23:00", "150"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 2) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.fries, "Fries", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.fries, "Fries", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.fries, "Fries", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.fries, "Fries", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.fries, "Fries", "05hr30min", "3000"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 3) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.ice_cream, "Ice Cream", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.ice_cream, "Ice Cream", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.ice_cream, "Ice Cream", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.ice_cream, "Ice Cream", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.ice_cream, "Ice Cream", "05hr30min", "3000"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 4) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.sandwich, "Sandwich", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.sandwich, "Sandwich", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.sandwich, "Sandwich", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.sandwich, "Sandwich", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.sandwich, "Sandwich", "05hr30min", "3000"));
                    updateVerticalRec.callBack(list, position);
                }
            }
        });

        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.hor_img);
            text=(TextView) itemView.findViewById(R.id.hor_text);
            cardView=(CardView) itemView.findViewById(R.id.hor_cardView);
        }
    }
}