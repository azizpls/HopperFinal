package com.example.hopper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // pulling out references to UI elements on login screen
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private Button btnLogin;
    private Button btnFBLogin;
    private TextView tvExistSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    /*
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnFBLogin = findViewById(R.id.btnFBLogin);
        tvExistSignup = findViewById(R.id.tvExistSignup);
*/
    }


}
