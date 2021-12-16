package com.college.placementportal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.databinding.ActivityForgotPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ForgotPasswordActivity";
    ActivityForgotPasswordBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(binding.emailAddress.getText().toString());
            }
        });
    }

    private void sendEmail(String emailAddress) {
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage() + "\n Create new account", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}