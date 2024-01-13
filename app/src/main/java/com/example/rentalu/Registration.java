package com.example.rentalu;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentalu.Adapters.OnSwipeTouchListener;
import com.example.rentalu.Helpers.DBHelper;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    Button btnLogin, btnSignup;
    EditText email,password,cpassword,phone,name;
    int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        btnLogin = findViewById(R.id.btnSignIn);
        btnSignup =findViewById(R.id.btnSignUp);
        email =findViewById(R.id.txtemail);
        password = findViewById(R.id.txtpassword);
        cpassword=findViewById(R.id.txtConfirmpassword);
        phone = findViewById(R.id.txtPhone);
        name = findViewById(R.id.txtname);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    if(password.getText().toString().equals(cpassword.getText().toString()))
                    {
                        String s_name = name.getText().toString();
                        String s_pass = password.getText().toString();
                        String s_phone = phone.getText().toString();
                        String s_email = email.getText().toString();

                        DBHelper register = new DBHelper(getApplicationContext());

                        register.addUser(s_email,s_name,s_pass,s_phone);
                        Toast.makeText(getApplicationContext(),"Sign up successfully",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Registration.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            private boolean validate() {
                String nameInput = name.getText().toString().trim();
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                String cpasswordInput = cpassword.getText().toString().trim();
                String phoneInput = phone.getText().toString().trim();

                if(nameInput.isEmpty()) {
                    name.setError("Name is required");
                    name.requestFocus();
                    return false;
                }

                if(emailInput.isEmpty()) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return false;
                }

                if(!isValidEmail(emailInput)) {
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    return false;
                }

                if(passwordInput.isEmpty()) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return false;
                }

                if(cpasswordInput.isEmpty()) {
                    cpassword.setError("Confirm password is required");
                    cpassword.requestFocus();
                    return false;
                }

                if(phoneInput.isEmpty()) {
                    phone.setError("Phone number is required");
                    phone.requestFocus();
                    return false;
                }

                return true;
            }

            private boolean isValidEmail(String email) {
                String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
                return Pattern.compile(emailRegex).matcher(email).matches();
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                finish();
            }
        });


        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });
    }
}