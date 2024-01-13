package com.example.rentalu;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rentalu.Adapters.Adapter;
import com.example.rentalu.Helpers.DBHelper;
import com.example.rentalu.Helpers.PropertyModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();

    Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View home = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = home.findViewById(R.id.recyclerView);


        try {
            adapter = new Adapter(PropertyModelArrayList, this.getContext());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(adapter);

            DBHelper dbHelper = new DBHelper(this.getContext());
            ArrayList<PropertyModel> blahh = dbHelper.readProperty();
            adapter.setPropertyModelArrayList(blahh);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return home;
    }

}