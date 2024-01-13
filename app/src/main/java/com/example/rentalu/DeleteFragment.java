package com.example.rentalu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalu.Adapters.DeleteAdapter;
import com.example.rentalu.Helpers.DBHelper;
import com.example.rentalu.Helpers.PropertyModel;

import java.util.ArrayList;


public class DeleteFragment extends Fragment {
    private int userID;

    TextView namer,ref_no;
    RecyclerView recV;
    Button search, delete;
    private String userName;

    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();

    DeleteAdapter dapter;
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
        View deletefrag =  inflater.inflate(R.layout.fragment_delete, container, false);

        namer = deletefrag.findViewById(R.id.txtnameR);
        search = deletefrag.findViewById(R.id.btnSearch);
        delete = deletefrag.findViewById(R.id.btnDelete);
        ref_no = deletefrag.findViewById(R.id.Reference_no);
        recV = deletefrag.findViewById(R.id.recyclerView2);


        namer.setText(userName);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String i_ref = ref_no.getText().toString();




                try {
                    dapter = new DeleteAdapter(PropertyModelArrayList, getContext());

                    recV.setHasFixedSize(true);
                    recV.setLayoutManager(new LinearLayoutManager(getContext()));
                    recV.setAdapter(dapter);

                    DBHelper dbHelper = new DBHelper(getContext());
                    ArrayList<PropertyModel> waahh = dbHelper.searchproperty(i_ref,userName);
                    if (waahh.isEmpty()) {
                        // Display a toast message indicating that there is no data
                        Toast.makeText(getContext(), "There is no match data!!! \n   Please Try Again..", Toast.LENGTH_SHORT).show();
                    } else {
                        // Set the data to the adapter
                        dapter.setPropertyModelArrayList(waahh);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i_ref = ref_no.getText().toString();

                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.deleteProperty(i_ref,userName);
                Toast.makeText(getActivity().getApplicationContext(),"Post is Deleted successfully",Toast.LENGTH_SHORT).show();

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

            }
        });


        return deletefrag;
    }
}