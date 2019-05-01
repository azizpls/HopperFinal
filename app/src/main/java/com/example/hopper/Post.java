package com.example.hopper;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;


@ParseClassName("Post")
public class Post extends ParseObject {


    // creating keys for different desired parse attributes
    public static final String KEY_DETAILS = "Details";
    public static final String END_TIME = "Date";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_BUSINESS_NAME = "BusinessName";
    //    public static final String KEY_CREATED_AT = "createdAt";
    private CountDownTimer countDownTimer;
    private TextView tvCountdown;




    // set and get methods for desired attribute
    public String getDescription() {
        return getString(KEY_DETAILS);
    }
    public Date getEndDate() {

        return getDate(END_TIME);
    }


    public void setDescription(String description) {
        put(KEY_DETAILS, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public String getUser() {
        return getString(KEY_BUSINESS_NAME);
    }

    public void setUser(ParseUser parseUser) {
        put(KEY_BUSINESS_NAME, parseUser);
    }




}