package com.college.placementportal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.activities.user.RegisterCompanyActivity;
import com.college.placementportal.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            //take user to the dashboard
            reload();
        }

        setContentView(R.layout.activity_create_account);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail() && validatePassword()) {
                    createAccount(binding.email.getText().toString(), binding.password.getText().toString());
                }
            }
        });

    }
    private void createAccount(String email, String password) {
        // [START sign_in_with_email]
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void reload() {
        //redirect user to respective dashboard
//        Toast.makeText(getApplicationContext(), "To be redirected to dashboard (Implementation coming soon....)", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RegisterCompanyActivity.class));
        finish();
    }
    private void updateUI(FirebaseUser user) {
        Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
        reload();
    }
    private boolean validateEmail() {

        // Extract input from EditText
        String emailInput = binding.email.getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
//            email.setError("Field can not be empty");
            Toast.makeText(CreateAccountActivity.this, "Field can not be empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            email.setError("Please enter a valid email address");
            Toast.makeText(CreateAccountActivity.this, "Please enter a valid email address",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            email.setError(null);
            return true;
        }
    }
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
//                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");
    private boolean validatePassword() {
        String passwordInput = binding.password.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
//            password.setError("Field can not be empty");
            Toast.makeText(CreateAccountActivity.this, "Field can not be empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
//            password.setError("Password is too weak");
            Toast.makeText(CreateAccountActivity.this, "Password is too weak",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            password.setError(null);
            return true;
        }
    }
}