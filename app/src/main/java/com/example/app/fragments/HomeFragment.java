package com.example.app.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.adapters.HomeHorAdapter;
import com.example.app.adapters.HomeVerAdapter;
import com.example.app.adapters.UpdateVerticalRec;
import com.example.app.models.HomeHorModel;
import com.example.app.models.HomeVerModel;

import java.util.ArrayList;
import java.util.Locale;

public class HomeFragment extends Fragment implements UpdateVerticalRec {
    RecyclerView hor_RecycleVew,ver_RecycleVew;
    TextView name;
    ArrayList<HomeHorModel> list1;
    HomeHorAdapter adapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        //////////Horizontal RecycleView

        hor_RecycleVew=(RecyclerView) view.findViewById(R.id.home_hor_rec);

        list1=new ArrayList<>();

        list1.add(new HomeHorModel(R.drawable.mazdoorg,"Mazdoor"));
        list1.add(new HomeHorModel(R.drawable.plumber,"Plumber"));
        list1.add(new HomeHorModel(R.drawable.electrician,"Electrician"));
        list1.add(new HomeHorModel(R.drawable.cardd,"Car"));
        list1.add(new HomeHorModel(R.drawable.gadremov,"security"));

        adapter1=new HomeHorAdapter(list1,getActivity(),this);
        hor_RecycleVew.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        hor_RecycleVew.setLayoutManager(linearLayoutManager1);

        //////////Vertical RecycleView

        ver_RecycleVew=(RecyclerView) view.findViewById(R.id.home_ver_rec);

        ArrayList<HomeVerModel> list2=new ArrayList<>();

        HomeVerAdapter adapter2=new HomeVerAdapter(list2,getContext());
        ver_RecycleVew.setAdapter(adapter2);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        ver_RecycleVew.setLayoutManager(linearLayoutManager2);

        return view;
    }

    @Override
    public void callBack(ArrayList<HomeVerModel> list, int position) {
        HomeVerAdapter adapter3=new HomeVerAdapter(list,getContext());
        ver_RecycleVew.setAdapter(adapter3);
    }
}