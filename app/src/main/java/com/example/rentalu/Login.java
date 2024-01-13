package com.example.rentalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.google.android.material.navigation.NavigationBarItemView;

public class Login extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    Button btnLogin, btnSignup;
    EditText txtemail,txtpassword;
    int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnSignIn);
        btnSignup =findViewById(R.id.btnSignUp);
        txtemail =findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();

                if (validate()) {

                    DBHelper.User user = dbHelper.getUser(email, password);

                    if (user != null) {
                        int userId = user.getUserId();
                        String name = user.getName();
                        Toast.makeText(getApplicationContext(), "Logged In Successfully" , Toast.LENGTH_SHORT).show();
                        Intent toView = new Intent(Login.this, Navigation.class);
                        toView.putExtra("USER_ID", userId);
                        toView.putExtra("NAME",name);
                        startActivity(toView);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please fill Email and password", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean validate() {

                String emailInput = txtemail.getText().toString().trim();
                String passwordInput = txtpassword.getText().toString().trim();

                if(emailInput.isEmpty()) {
                    txtemail.setError("Email is required");
                    txtemail.requestFocus();
                    return false;
                }

                if(passwordInput.isEmpty()) {
                    txtpassword.setError("Password is required");
                    txtpassword.requestFocus();
                    return false;
                }

                return true;
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
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