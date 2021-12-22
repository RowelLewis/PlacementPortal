package com.college.placementportal.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.auth.SigninActivity;
import com.college.placementportal.databinding.ActivityChangePasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {
    private static final String TAG = "ChangePasswordActivity";
    ActivityChangePasswordBinding binding;
    
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        user = FirebaseAuth.getInstance().getCurrentUser();

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String currentPasswordPassword = binding.currentPassword.getText().toString();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(), binding.currentPassword.getText().toString());

                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful() && validatePassword()) {
                                    user.updatePassword(binding.newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Password updated");
                                                Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), UserDashboardActivity.class));
                                                finish();
                                            } else {
                                                Log.d(TAG, "Error password not updated");
                                            }
                                        }
                                    });
                                } else {
                                    Log.d(TAG, "Error auth failed");
                                    Toast.makeText(getApplicationContext(), "Invalid current password\nPlease enter correct current password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
//                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");



    private boolean validatePassword() {
        String newPassword = binding.newPassword.getText().toString().trim();
            // if password field is empty
            // it will display error message "Field can not be empty"
            if (newPassword.isEmpty()) {
                binding.newPassword.setError("Field can not be empty");
                Toast.makeText(getApplicationContext(), "Field can not be empty",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            // if password does not matches to the pattern
            // it will display an error message "Password is too weak"
            else if (!PASSWORD_PATTERN.matcher(newPassword).matches()) {
                binding.newPassword.setError("Password is too weak");
                Toast.makeText(getApplicationContext(), "Password is too weak",
                        Toast.LENGTH_SHORT).show();
                return false;
            } else {
                binding.newPassword.setError(null);
                return true;
            }
    }
}