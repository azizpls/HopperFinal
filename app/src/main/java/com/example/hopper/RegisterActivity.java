package com.example.hopper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    // pulling out references to UI elements on login screen
    private EditText etSignupEmail;
    private EditText etSignupPassword;
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnSignup;
    private Button btnFBSignup;
    private TextView tvExistLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

     /*
        etSignupEmail = findViewById(R.id.etSignupEmail);
        etSignupPassword = findViewById(R.id.etSignupPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnFBSignup = findViewById(R.id.btnFBSignup);
        tvExistLogin = findViewById(R.id.tvExistLogin);
     */
    }


}
