package com.example.hopper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    // pulling out references to UI elements on login screen
    private EditText etEmailAddress;
    private EditText etPassword;
    private EditText etFirstName;
    private EditText etLastName;
    private ImageView ivSignup;
    private TextView tvExistLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //Removes top status bar

        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        ivSignup = findViewById(R.id.ivSignUp);
        ivSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = etFirstName.getText().toString();
                String lastname = etLastName.getText().toString();
                String emailAddress = etEmailAddress.getText().toString();
                String password = etPassword.getText().toString();

                register(firstname, lastname,emailAddress, password);

            }
        });







    }

    private void register(String firstname, String lastname, String emailAddress, String password) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.set
        user.setUsername();
        user.setPassword(password);
        user.setEmail(emailAddress);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    goMainActivity();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "Issue with Registration");
                    e.printStackTrace();
                    return;
                }
            }
        });


    }

    private void goMainActivity() {
        Log.d(TAG,  "Navigating to Main Activity");

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    }


}
