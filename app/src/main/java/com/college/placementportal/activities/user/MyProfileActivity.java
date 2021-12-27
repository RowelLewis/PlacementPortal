package com.college.placementportal.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.models.Student;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyProfileActivity extends AppCompatActivity {
    EditText et1;
    String email;//passed over from intent
    String key;//key to the database is the email before the @ part
    TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    TextInputEditText fullname,dob,branch,emailid,phone,gender,address;
    Button submit;

    private DatabaseReference mDatabase;
    private DatabaseReference myref;
    String TAG="MyProfileActivitys";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myref= mDatabase.child("student");


        et1=findViewById(R.id.name);
//        Intent intent = getIntent();
//        email= intent.getStringExtra("email");
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();

        int iend = email.indexOf("@"); //this finds the first occurrence of "@"
        //in string thus giving you the index of where it is in the string

        //For example abcd@gmail.com then key is abcd
        key= email.substring(0 , iend); //this will give abcd

        //immediately on openeing the profile , query for details from the database and set the values

        t1=findViewById(R.id.fullname);
        t2=findViewById(R.id.dob);
        t3=findViewById(R.id.branch);
        t4=findViewById(R.id.email);
        t5=findViewById(R.id.phone);
        t6=findViewById(R.id.gender);
        t7=findViewById(R.id.address);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String keyFromDatabase=ds.getKey().toString();
//                    Toast.makeText(getApplicationContext(), keyFromDatabase, Toast.LENGTH_SHORT).show();
                    if(keyFromDatabase.equals(key)){
                        et1.setText(ds.child("fullName").getValue(String.class));
                        t1.getEditText().setText(ds.child("fullName").getValue(String.class));
                        t2.getEditText().setText(ds.child("dateofbirth").getValue(String.class));
                        t3.getEditText().setText(ds.child("branch").getValue(String.class));
                        t4.getEditText().setText(ds.child("mail").getValue(String.class));
                        t5.getEditText().setText(ds.child("phoneNumber").getValue(String.class));
                        t6.getEditText().setText(ds.child("gender").getValue(String.class));
                        t7.getEditText().setText(ds.child("permanentAddress").getValue(String.class));
                    }
                }
//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        //on submit
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a,b,c,d,e,f,g;

                a=t1.getEditText().getText().toString();
                b=t2.getEditText().getText().toString();
                c=t3.getEditText().getText().toString();
                d=t4.getEditText().getText().toString();
                e=t5.getEditText().getText().toString();
                f=t6.getEditText().getText().toString();
                g=t7.getEditText().getText().toString();
                writeNewUser(a,b,c,d,e,f,g);
                Toast.makeText(getApplicationContext(), "Applied changes successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserDashboardActivity.class));
                finish();
            }
        });


    }



    //writing student details onto database
    public void writeNewUser(String a, String b,String c, String d, String e, String f , String g ) {
//        mDatabase.child("message").setValue("Hello World");

//        Toast.makeText(getApplicationContext(), "fullname "+a, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "dob "+b, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "branch "+c, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "email "+d, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "phn "+e, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "gender "+f, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "address "+g, Toast.LENGTH_SHORT).show();
//        String temp="temp";

        Student newStudent = new Student(a,b,c,d,e,f,g);


//        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = newStudent.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/student/" + key, postValues);
        mDatabase.updateChildren(childUpdates);



//        mDatabase.child("student").child(key).setValue(newStudent);
//        mDatabase.child("student").child(key).child("email").setValue("abcd");
    }
}