package com.example.app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;
import com.example.app.adapters.CartAdapter;
import com.example.app.database.DBHelper;
import com.example.app.models.CartModel;

import java.util.ArrayList;

public class MyCartFragment extends Fragment {
    RecyclerView cart_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_cart, container, false);

        cart_recyclerView=(RecyclerView) view.findViewById(R.id.cart_rec);

        DBHelper helper=new DBHelper(getContext());

        ArrayList<CartModel> list=helper.getOrders();

        CartAdapter adapter1=new CartAdapter(list,getContext());
        cart_recyclerView.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        cart_recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}