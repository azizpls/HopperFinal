package com.example.hopper;

import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hopper.fragments.MapFragment;
import com.example.hopper.fragments.PostFragment;
import com.example.hopper.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();




        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //TODO: swap fragment here
                        fragment = new PostFragment();
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_map:
                        //TODO: swap fragment here
                        Toast.makeText(MainActivity.this, "MapFragment!", Toast.LENGTH_SHORT).show();
                        fragment = new MapFragment();
                        break;
                    case R.id.action_profile:
                        default:
                            //TODO: swap fragment here

                            fragment = new ProfileFragment();
                            Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                            break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

    }



}
