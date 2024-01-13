package com.example.rentalu.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalu.Helpers.DBHelper;
import com.example.rentalu.Helpers.PropertyModel;
import com.example.rentalu.HomeFragment;
import com.example.rentalu.Navigation;
import com.example.rentalu.R;
import com.example.rentalu.UpdateFragment;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.ViewHolder> {

    Context context;
    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();

    public EditAdapter(ArrayList<PropertyModel> PropertyModelArrayList,Context context) {
        this.PropertyModelArrayList =PropertyModelArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.update_card_view,parent,false);
        EditAdapter.ViewHolder viewHolder=new EditAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PropertyModel propertylist =PropertyModelArrayList.get(position);
        holder.pro_type.setText(propertylist.getProp_type());
        holder.furn_type.setText(propertylist.getFurniture());
        holder.bedroom.setText(propertylist.getBedroom());

        holder.remak.setText(propertylist.getRemark());
        holder.RentalPrice.setText(String.valueOf(propertylist.getRent_price()));

        holder.ImageView.setImageBitmap(propertylist.getImage());

    }

    public void setPropertyModelArrayList(ArrayList<PropertyModel> propertyModelArrayList) {
        this.PropertyModelArrayList = propertyModelArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return PropertyModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        EditText pro_type,furn_type,bedroom,RentalPrice,remak;

        ImageView ImageView;

        CardView cardView;
        Button btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pro_type = itemView.findViewById(R.id.txtEtype);
            furn_type =itemView.findViewById(R.id.txtEfurnit);
            bedroom = itemView.findViewById(R.id.txtEbed);
            RentalPrice = itemView.findViewById(R.id.txtEprice);
            remak = itemView.findViewById(R.id.txtEremark);
            ImageView = itemView.findViewById(R.id.imgEphoto);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the clicked item in the adapter
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        PropertyModel propertylist = PropertyModelArrayList.get(position);

                        // Get the updated values from the EditText fields
                        String updatedPropType = pro_type.getText().toString();
                        String updatedFurnType = furn_type.getText().toString();
                        String updatedBedroom = bedroom.getText().toString();
                        String updatedRentalPrice = RentalPrice.getText().toString();
                        String updatedRemark = remak.getText().toString();

                        // Update the PropertyModel with the new values
                        propertylist.setProp_type(updatedPropType);
                        propertylist.setFurniture(updatedFurnType);
                        propertylist.setBedroom(updatedBedroom);
                        propertylist.setRent_price(Float.parseFloat(updatedRentalPrice));
                        propertylist.setRemark(updatedRemark);

                        // Notify the adapter that the data has changed
                        notifyItemChanged(position);

                        // Update the data in the SQLite database
                        DBHelper dbHelper = new DBHelper(itemView.getContext());
                        dbHelper.UpdateProperty(String.valueOf(propertylist.getRef_list_num()),updatedPropType, updatedBedroom,  Float.parseFloat(updatedRentalPrice), updatedFurnType, updatedRemark, propertylist.getReporter_name(), convertBitmapToByteArray(propertylist.getImage()));

                        Toast.makeText(context.getApplicationContext(),"Post is Updated successfully",Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = ((Navigation) context).getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, new HomeFragment())
                                .commit();
                    }
                }
            });
        }
    }
    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}