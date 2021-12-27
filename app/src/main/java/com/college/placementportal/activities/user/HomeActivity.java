package com.college.placementportal.activities.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.databinding.ActivityHomeBinding;
import com.college.placementportal.models.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mDatabase;
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("student").child(mUser.getEmail().substring(0, mUser.getEmail().indexOf("@")));

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    Student student = snapshot.getValue(Student.class);
//                Toast.makeText(getApplicationContext(), student.get(), Toast.LENGTH_SHORT).show();
                    binding.fullName.setText(student.getFullName());
                    binding.branch.setText(student.getBranch());
                    binding.email.setText(mUser.getEmail());
                    binding.phone.setText(student.getPhoneNumber());
                    binding.gender.setText(student.getGender());
                    binding.dob.setText(student.getDateofbirth());
                    binding.permanentAddress.setText(student.getPermanentAddress());
                } else {
                    binding.addProDet.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}