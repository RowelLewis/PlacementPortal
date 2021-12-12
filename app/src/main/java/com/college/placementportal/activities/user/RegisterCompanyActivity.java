package com.college.placementportal.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.college.placementportal.R;

public class RegisterCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        FirebaseAuth.getInstance().signOut();
    }
}