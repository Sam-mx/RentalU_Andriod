package com.example.rentalu.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalu.Helpers.PropertyModel;
import com.example.rentalu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<PropertyModel> PropertyModelArrayList =new ArrayList<>();

    public Adapter(ArrayList<PropertyModel> PropertyModelArrayList,Context context) {
        this.PropertyModelArrayList =PropertyModelArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.custom_card_view,parent,false);
        Adapter.ViewHolder viewHolder=new Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final PropertyModel propertylist =PropertyModelArrayList.get(position);
            holder.RefNo.setText(String.valueOf(propertylist.getRef_list_num()));
            holder.rep_name.setText(propertylist.getReporter_name());
            holder.pro_type.setText(propertylist.getProp_type());
            holder.furn_type.setText(propertylist.getFurniture());
            holder.bedroom.setText(propertylist.getBedroom());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(propertylist.getDate_time());

            // Format the Date object as needed
            SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String formattedDate = displayFormat.format(date);

            holder.d_t.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        TextView rep_name,pro_type,furn_type,bedroom,RentalPrice,remak,d_t,RefNo;

        ImageView ImageView;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           rep_name =itemView.findViewById(R.id.RePorter_Name);
           pro_type = itemView.findViewById(R.id.PropertyType);
           furn_type =itemView.findViewById(R.id.FurnitType);
           bedroom = itemView.findViewById(R.id.Bedroom);
           RentalPrice = itemView.findViewById(R.id.Price);
           remak = itemView.findViewById(R.id.Remark);
           d_t = itemView.findViewById(R.id.Date);
           ImageView = itemView.findViewById(R.id.imageView);
           RefNo = itemView.findViewById(R.id.RefNo);
        }
    }
}