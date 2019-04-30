package com.example.hopper.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hopper.LoginActivity;
import com.example.hopper.R;
import com.parse.ParseUser;

import static android.support.constraint.Constraints.TAG;

public class ProfileFragment extends Fragment {
    private TextView tvGreeting;
    private TextView tvContactUs;
    private TextView tvSignout;
    private TextView tvProfileTitle;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvGreeting = view.findViewById(R.id.tvGreeting);
        tvContactUs = view.findViewById(R.id.tvContact);
        tvSignout = view.findViewById(R.id.tvSignout);

        tvSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });



    }

    private void goLoginActivity() {
        Log.d(TAG,  "Navigating to Login Activity");

        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        //finish();
    }
}
