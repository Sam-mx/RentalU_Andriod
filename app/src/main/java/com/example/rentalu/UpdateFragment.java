package com.example.rentalu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalu.Adapters.DeleteAdapter;
import com.example.rentalu.Adapters.EditAdapter;
import com.example.rentalu.Helpers.DBHelper;
import com.example.rentalu.Helpers.PropertyModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class UpdateFragment extends Fragment {
    private static final int IMAGE_PERMISSION = 1;
    TextView namer,ref_no;
    RecyclerView recV;
    Button search;
    private String userName;
    private int userID;
    private static final int RESULT_OK = -1;
    Bitmap image;
    private static int RESULT_LOAD_IMAGE = 1;

    EditText pro_type,furn_type,bedroom,RentalPrice,remak;

    ImageView blahh;
    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();
    EditAdapter eadt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getInt("USER_ID");
            userName = getArguments().getString("NAME");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View update = inflater.inflate(R.layout.fragment_update, container, false);

        namer = update.findViewById(R.id.txtnameR);
        search = update.findViewById(R.id.btnSearch);
        ref_no = update.findViewById(R.id.Reference_no);
        recV = update.findViewById(R.id.recyclerView3);


        namer.setText(userName);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i_ref = ref_no.getText().toString();




                try {
                    eadt = new EditAdapter(PropertyModelArrayList, getContext());

                    recV.setHasFixedSize(true);
                    recV.setLayoutManager(new LinearLayoutManager(getContext()));
                    recV.setAdapter(eadt);

                    DBHelper dbHelper = new DBHelper(getContext());
                    ArrayList<PropertyModel> waahh = dbHelper.searchproperty(i_ref,userName);
                    if (waahh.isEmpty()) {
                        // Display a toast message indicating that there is no data
                        Toast.makeText(getContext(), "There is no match data!!! \n   Please Try Again..", Toast.LENGTH_SHORT).show();
                    } else {
                        // Set the data to the adapter
                        eadt.setPropertyModelArrayList(waahh);

                        if (!waahh.isEmpty()) {
                            PropertyModel firstItem = waahh.get(0);
                            pro_type.setText(firstItem.getProp_type());
                            furn_type.setText(firstItem.getFurniture());
                            bedroom.setText(firstItem.getBedroom());
                            RentalPrice.setText(String.valueOf(firstItem.getRent_price()));
                            remak.setText(firstItem.getRemark());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        return update;
    }


}