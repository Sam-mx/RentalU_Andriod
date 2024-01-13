package com.example.rentalu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.jar.Attributes;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private int userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userId=getIntent().getIntExtra("USER_ID",-1);
        userName = getIntent().getStringExtra("NAME");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        else if(item.getItemId()== R.id.nav_add){
            Insert_Fragment insertFragment = new Insert_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("USER_ID", userId);
            bundle.putString("NAME", userName);
            insertFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, insertFragment).commit();
        }
        else if(item.getItemId()== R.id.nav_edit){
            UpdateFragment updateFragment = new UpdateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("USER_ID", userId);
            bundle.putString("NAME", userName);
            updateFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  updateFragment).commit();
        }
        else if(item.getItemId()== R.id.nav_delete){
            DeleteFragment deleteFragment = new DeleteFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("USER_ID", userId);
            bundle.putString("NAME", userName);
            deleteFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, deleteFragment).commit();
        }
        else if(item.getItemId()== R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
        }
        else if(item.getItemId()== R.id.nav_logout){
            Toast.makeText(this, "Thank you for using this Application!! \n             ^-^ See you again ^-^", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Navigation.this, Login.class);
            startActivity(i);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //noinspection deprecation
            super.onBackPressed();
        }
    }
}