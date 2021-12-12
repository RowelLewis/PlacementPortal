package com.college.placementportal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.college.placementportal.R;

public class AddCompanyActivity extends AppCompatActivity {

    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        pic=findViewById(R.id.imageView);
        pic.setImageResource(R.drawable.nmamit_logo);
    }
}