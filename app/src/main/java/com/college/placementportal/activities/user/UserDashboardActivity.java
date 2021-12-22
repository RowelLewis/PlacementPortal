package com.college.placementportal.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.auth.SigninActivity;
import com.college.placementportal.databinding.ActivityUserDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserDashboardActivity extends AppCompatActivity {

    ActivityUserDashboardBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();



        setContentView(R.layout.activity_user_dashboard);

        binding = DataBindingUtil.setContentView( this, R.layout.activity_user_dashboard);

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        binding.rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RulesActivity.class));
            }
        });
        binding.myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
            }
        });
        binding.registercompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterCompanyActivity.class));
            }
        });
        binding.changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), LogoutUser.class));
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                reload();
            }
        });
    }
    private void reload() {
        //redirect user to respective dashboard
//        Toast.makeText(getApplicationContext(), "To be redirected to dashboard (Implementation coming soon....)", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SigninActivity.class));
        finish();
    }
}