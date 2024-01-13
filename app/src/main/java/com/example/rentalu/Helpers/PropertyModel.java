package com.example.rentalu.Helpers;

import android.graphics.Bitmap;

public class PropertyModel {

    int ref_list_num;
    String prop_type, bedroom, date_time, furniture, remark, reporter_name;
    float rent_price;
    Bitmap image;

    public PropertyModel(int ref_list_num, String prop_type, String bedroom, String date_time,  float rent_price, String furniture,  String remark, String reporter_name, Bitmap image) {
        this.ref_list_num = ref_list_num;
        this.prop_type = prop_type;
        this.bedroom = bedroom;
        this.date_time = date_time;
        this.furniture = furniture;
        this.remark = remark;
        this.reporter_name = reporter_name;
        this.rent_price = rent_price;
        this.image = image;
    }

    public int getRef_list_num() {
        return ref_list_num;
    }

    public void setRef_list_num(int ref_list_num) {
        this.ref_list_num = ref_list_num;
    }

    public String getProp_type() {
        return prop_type;
    }

    public void setProp_type(String prop_type) {
        this.prop_type = prop_type;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReporter_name() {
        return reporter_name;
    }

    public void setReporter_name(String reporter_name) {
        this.reporter_name = reporter_name;
    }

    public float getRent_price() {
        return rent_price;
    }

    public void setRent_price(float rent_price) {
        this.rent_price = rent_price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
