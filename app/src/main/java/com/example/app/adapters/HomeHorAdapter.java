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

            list.add(new HomeVerModel(R.drawable.mazdoorg, "Mazdoor", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.mazdoor3, "Marble", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.carpainter, "painters", "10:00 - 23:00",  "60"));
            list.add(new HomeVerModel(R.drawable.mazdoo2, "Mistri", "10:00 - 23:00", "60"));
            list.add(new HomeVerModel(R.drawable.mazdooor1, "Driver", "10:00 - 23:00", "60"));

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

                    list.add(new HomeVerModel(R.drawable.mazdoorg, "Mazdoor", "10:00 - 23:00", "120"));
                    list.add(new HomeVerModel(R.drawable.mazdoo2, "Marble", "10:00 - 23:00","120"));
                    list.add(new HomeVerModel(R.drawable.mazdoor3, "painters", "10:00 - 23:00", "120"));
                    list.add(new HomeVerModel(R.drawable.carpainter, "Mistri", "10:00 - 23:00", " 120"));
                    list.add(new HomeVerModel(R.drawable.mazdooor1, "Driver", "10:00 - 23:00", "120"));

                    updateVerticalRec.callBack(list, position);
                }
                if (position == 1) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.leakage1, "Bathroom Fitting", "10:00 - 23:00", "150"));
                    list.add(new HomeVerModel(R.drawable.leakage3, "Leakage Repair", "10:00 - 23:00", "150"));
                    list.add(new HomeVerModel(R.drawable.leakage4, "General Pipe Fitting", "10:00 - 23:00",  "150"));
                    list.add(new HomeVerModel(R.drawable.leakege2, "Bath Tub", "10:00 - 23:00",  "150"));
                    list.add(new HomeVerModel(R.drawable.leakage1, "Bathroom assesories", "10:00 - 23:00", "150"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 2) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.electrician1, "Mobile Repair", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.electrician2, "Car Electrician", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.electrician3, "Home Electrician", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.electrician4, "Motor Winding", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.electrician1, "Electrician", "05hr30min", "3000"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 3) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.car1, "Car sale", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.car2, "Car Driver", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.car3, "Car repair", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.car4, "rent a car", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.car4, "car washer", "05hr30min", "3000"));
                    updateVerticalRec.callBack(list, position);
                }
                if (position == 4) {
                    ArrayList<HomeVerModel> list = new ArrayList<>();

                    list.add(new HomeVerModel(R.drawable.secuitry4, "security", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.security1, "Home Security", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.security3, "University", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.scurity2, "College", "05hr30min", "3000"));
                    list.add(new HomeVerModel(R.drawable.secuitry4, "Local Area", "05hr30min", "3000"));
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