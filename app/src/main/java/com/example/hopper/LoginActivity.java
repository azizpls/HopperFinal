package com.example.hopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // pulling out references to UI elements on login screen
    private EditText etEmailAddress;
    private EditText etPassword;
    private ImageView ivLogin;
    private TextView tvRegisterMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            goMainActivity();
        } else {
            // show the signup or login screen

            etEmailAddress = findViewById(R.id.etEmailAddress);
            etPassword = findViewById(R.id.etPassword);
            tvRegisterMover = findViewById(R.id.tvRegisterMover);
            ivLogin = findViewById(R.id.ivLogin);


            ivLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = etEmailAddress.getText().toString();
                    String password = etPassword.getText().toString();
                    login(username, password);
                }
            });

            tvRegisterMover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goRegisterActivity();
                }
            });

        }
    }

    private void login(String username, String password) {



        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if( e != null){
                    //TODO: better error handling
                    Log.e(TAG,  "Issue with login");
                    e.printStackTrace();
                    return;
                }

                //TODO: navigate to new activity if the user has signed properly
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Log.d(TAG,  "Navigating to Main Activity");

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

    private void goRegisterActivity() {

        Log.d(TAG,  "Navigating to Register Activity");

        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }


}
