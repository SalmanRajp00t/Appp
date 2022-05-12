package com.example.app.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.OrderDetailActivity;
import com.example.app.R;
import com.example.app.database.DBHelper;
import com.example.app.models.CartModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<CartModel> list;
    Context context;

    public CartAdapter(ArrayList<CartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mycart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel mdl=list.get(position);
        holder.image.setImageResource(mdl.getImage());
        holder.name.setText(mdl.getName());
        holder.order.setText(mdl.getOrderNumber());
        holder.timing.setText(mdl.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, OrderDetailActivity.class);
                intent.putExtra("id",Integer.parseInt(mdl.getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Item");
                builder.setMessage("Are you sure to delete this item?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper helper=new DBHelper(context);
                        int id=Integer.parseInt(mdl.getOrderNumber());
                        boolean delete=helper.deleteOrder(id);
                        if (delete){
                            Toast.makeText(context, "Order is deleted.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,order,timing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.cart_img);
            name=(TextView) itemView.findViewById(R.id.cart_name);
            order=(TextView) itemView.findViewById(R.id.orderNumber);
            timing=(TextView) itemView.findViewById(R.id.cart_price);
        }
    }
}