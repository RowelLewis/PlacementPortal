package com.college.placementportal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.activities.admin.AdminDashboardActivity;
import com.college.placementportal.activities.user.RegisterCompanyActivity;
import com.college.placementportal.activities.user.UserDashboardActivity;
import com.college.placementportal.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private Button siginBTN;
    private EditText email;
    private EditText password;
    private TextView signinView, forgotPass, createAcc;

    private static final String adminEmail = "admin@gmail.com";
    private static final String adminPassword = "admin123";

    ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            reload();
        }

        setContentView(R.layout.activity_signin);

        //get user email and password from activity_signin and get the user signed in and procced the user to dashboard
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        binding.signinView.setText("Sign In");


        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), binding.email.getText().toString(), Toast.LENGTH_SHORT).show();
                if(validateEmail() && validatePassword()) {
                    signIn(binding.email.getText().toString(), binding.password.getText().toString());
                }
            }
        });

        binding.forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, ForgotPasswordActivity.class));
            }
        });

        binding.createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, CreateAccountActivity.class));
            }
        });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        if(adminEmail.equals(email) && adminPassword.equals(password)) {
            startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
            finish();
        }
//        firebase.auth().onAuthStateChanged(authUser => {
//            if(authUser.user.emailVerified){ //This will return true or false
//    //            Toast.makeText(getApplicationContext(), "Email Verified", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(getApplicationContext(), "Email not verified.\nPlease verify email", Toast.LENGTH_SHORT).show();
//            }
//        });

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(!user.isEmailVerified()) {
                                Toast.makeText(getApplicationContext(), "Please verify email before login", Toast.LENGTH_SHORT).show();
                                signOut();
                                finish();
                            }
                            startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //test if this works
                notifyUser(e.getLocalizedMessage());
            }

            private void notifyUser(String localizedMessage) {
                Toast.makeText(SigninActivity.this, localizedMessage, Toast.LENGTH_SHORT).show();
            }
        });
        // [END sign_in_with_email]
    }


    private void reload() {
        //redirect user to respective dashboard
//        Toast.makeText(getApplicationContext(), "To be redirected to dashboard (Implementation coming soon....)", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, UserDashboardActivity.class));
        finish();
    }
    public void signOut() {
        // [START auth_sign_out]
        mAuth.getInstance().signOut();
        // [END auth_sign_out]
    }

    private void updateUI(FirebaseUser user) {

        Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
        reload();
    }



    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
//                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");

    private boolean validateEmail() {

        // Extract input from EditText
        String emailInput = binding.email.getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
//            email.setError("Field can not be empty");
            Toast.makeText(SigninActivity.this, "Field can not be empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
//            email.setError("Please enter a valid email address");
            Toast.makeText(SigninActivity.this, "Please enter a valid email address",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = binding.password.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
//            password.setError("Field can not be empty");
            Toast.makeText(SigninActivity.this, "Field can not be empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
//            password.setError("Password is too weak");
            Toast.makeText(SigninActivity.this, "Password is too weak",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            password.setError(null);
            return true;
        }
    }
}