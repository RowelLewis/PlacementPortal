package com.college.placementportal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.models.Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayCompanyActivity extends AppCompatActivity {

    DisplayCompanyAdapter displayCompanyAdapter;
    ArrayList<Company> list;
    private RecyclerView recyclerView;
    DatabaseReference mbase; // Create object of theFirebase Realtime Database
    DatabaseReference reference;
    DatabaseReference refRegistered;
    Button register;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_company);

        // Create a instance of the database and get
        // its reference
        mbase = FirebaseDatabase.getInstance().getReference().child("company");
        reference = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Company> options = new FirebaseRecyclerOptions.Builder<Company>()
                .setQuery(mbase, Company.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        displayCompanyAdapter = new DisplayCompanyAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(displayCompanyAdapter);

//        register = findViewById(R.id.register);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "working till here", Toast.LENGTH_SHORT).show();
////                registerUserForCompany();
//            }
//        });
//        displayCompanyAdapter.notifyDataSetChanged();
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart()
    {
        super.onStart();
        displayCompanyAdapter.startListening();
    }

//    private void registerUserForCompany() {
//
//        mUser = mAuth.getCurrentUser();
//
//        String companyName = ((TextView)findViewById(R.id.companyName)).getText().toString();
//
//        refRegistered = reference.child("registered");
//
//        refRegistered.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        displayCompanyAdapter.stopListening();
    }
}