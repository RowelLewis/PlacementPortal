package com.college.placementportal.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.college.placementportal.R;
import com.college.placementportal.activities.DisplayCompanyActivity;
import com.college.placementportal.auth.SigninActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    Button b1,b2, logoutAdmin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        mAuth = FirebaseAuth.getInstance();

        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        logoutAdmin=findViewById(R.id.logoutAdmin);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(AdminDashboardActivity.this, AddCompanyActivity.class);
                startActivity(a);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(AdminDashboardActivity.this, DisplayCompanyActivity.class);
                startActivity(a);
            }
        });

        logoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                finish();
            }
        });
    }
}