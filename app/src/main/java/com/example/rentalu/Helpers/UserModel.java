package com.example.rentalu.Helpers;

public class UserModel {

    int user_id;

    String email,name,password,phone_no;

    public UserModel(int user_id, String email, String name, String password, String phone_no) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone_no = phone_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
