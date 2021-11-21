package com.college.placementportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.college.placementportal.auth.SigninActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, SigninActivity.class));
        finish();
//        setContentView(R.layout.activity_main);
    }
}