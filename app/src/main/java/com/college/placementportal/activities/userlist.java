package com.college.placementportal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.activities.DisplayCompanyAdapter;
import com.college.placementportal.models.Company;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    DisplayCompanyAdapter myAdapter;
    ArrayList<Company> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("company");
        list = new ArrayList<Company>();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(), "Here!", Toast.LENGTH_SHORT).show();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Toast.makeText(getApplicationContext(), "im in!", Toast.LENGTH_SHORT).show();
                    String keyFromDatabase = dataSnapshot.getKey().toString();
                    Toast.makeText(getApplicationContext(), keyFromDatabase, Toast.LENGTH_SHORT).show();
                    Company company = dataSnapshot.getValue(Company.class);
                    Toast.makeText(getApplicationContext(), company.getCompanyName(), Toast.LENGTH_SHORT).show();
                    list.add(company);
                }
                myAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Database error!", Toast.LENGTH_SHORT).show();
            }
        });

//        recyclerView=findViewById(R.id.userlist);
//        recyclerView.setHasFixedSize(true);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//
//        myAdapter=new MyAdapter(this,list);
//        recyclerView.setAdapter(myAdapter);
    }
}