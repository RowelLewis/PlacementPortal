package com.college.placementportal.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.college.placementportal.R;
import com.college.placementportal.auth.SigninActivity;
import com.college.placementportal.models.Company;
import com.college.placementportal.models.Registered;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DisplayCompanyAdapter extends FirebaseRecyclerAdapter<Company, DisplayCompanyAdapter.personsViewholder> {

    private static final String TAG = "DisplayCompanyAdapter";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private DatabaseReference registeredDBRef;
    Context context;

    public DisplayCompanyAdapter (@NonNull FirebaseRecyclerOptions<Company> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull Company model) {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
//        holder.firstname.setText(model.getFirstname());
//
//        // Add lastname from model class (here
//        // "person.class")to appropriate view in Card
//        // view (here "person.xml")
//        holder.lastname.setText(model.getLastname());
//
//        // Add age from model class (here
//        // "person.class")to appropriate view in Card
//        // view (here "person.xml")
//        holder.age.setText(model.getAge());


        //other method
//        Company company=list.get(position);

        holder.companyName.setText(model.getCompanyName());
        holder.companyDescription.setText(model.getCompanyDescription());

        String setBranches = "";
        ArrayList<String> branches=model.getEligibleBranches();
//    String x=(String)=branches.size();
//        Toast.makeText(context.getApplicationContext(), "branch size:" + branches.size(), Toast.LENGTH_SHORT).show();
        for(int i=0;i<branches.size();i++){
            setBranches=setBranches+branches.get(i)+" ";
        }
//        Toast.makeText(context.getApplicationContext(), setBranches, Toast.LENGTH_SHORT).show();

        holder.eligibleBranches.setText(setBranches);

        holder.offeredCTC.setText(model.getOfferedCTC() + " LPA");


        String offer = model.getOffer();
        holder.offer.setText(offer);

        holder.minCGPA.setText(model.getMinCGPA());

        holder.closingOn.setText(model.getClosingOn());

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser().getEmail().equals("testaxxount32@gmail.com")) {
            holder.action1.setVisibility(View.GONE);
            holder.registered.setVisibility(View.GONE);
            holder.closed.setVisibility(View.GONE);
        }
        else {
            holder.action1.setVisibility(View.VISIBLE);
            holder.action1.setText("Register");
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUser = mAuth.getCurrentUser();
//        mDatabase.child("registered").child(model.getCompanyName()).child(mUser.getEmail().substring(0, mUser.getEmail().indexOf("@"))).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(!task.isSuccessful()) {
//                    Log.e("DisplayCompanyAdapter", "Error getting data", task.getException());
//                } else {
//                    Log.e("DisplayCompanyAdapter", "Working here"+task.getResult().getValue().toString(), task.getException());
//                    if(task.getResult().getValue(String.class).equals("1")) {
//                        holder.action1.setVisibility(View.GONE);
//                        holder.registered.setVisibility(View.VISIBLE);
//                        holder.closed.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });

        String username = mUser.getEmail().substring(0, mUser.getEmail().indexOf("@"));
        registeredDBRef = mDatabase.child("registered").child(model.getCompanyName()).child("studentID").child(username);
        Log.d("displayCompanyAdapter", "line 125 "+registeredDBRef);

//        if(registeredDBRef.get().getResult().getValue().equals("1")) {
//            Log.d("DisplayCompanyAdapter", "line 137: "+registeredDBRef.get().getResult().getValue());
////            holder.action1.setVisibility(View.GONE);
////            holder.registered.setVisibility(View.VISIBLE);s
////            holder.closed.setVisibility(View.GONE);
//        }
        registeredDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DisplayCompanyAdapter", "line 145: "+snapshot.getValue());
                if(snapshot.exists() && snapshot.getValue().equals("1")) {
                    holder.action1.setVisibility(View.GONE);
                    holder.registered.setVisibility(View.VISIBLE);
                    holder.closed.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        registeredDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("displayCompanyAdapter", "in data change "+username);
//                for(DataSnapshot data: snapshot.getChildren()) {
////                    Log.d("displayCompanyAdapter", "in for loop");
//                    Log.d("displayCompanyAdapter", "key:"+ data.child(username).getKey()+"val: "+data.child(username).getValue());
//                    if(!data.child(username).getValue().toString().equals("null") ){
//                        Log.d("displayCompanyAdapter", "in if condition "+data.child(username));
//                        holder.action1.setVisibility(View.GONE);
//                        holder.registered.setVisibility(View.VISIBLE);
//                        holder.closed.setVisibility(View.GONE);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate;
        Date closingOn;
        try {
            closingOn = dateFormatter.parse(model.getClosingOn());

            todayDate = dateFormatter.parse(dateFormatter.format(new Date()));

            if(!todayDate.before(closingOn)) {
                holder.closed.setVisibility(View.VISIBLE);
                holder.action1.setVisibility(View.GONE);
                holder.registered.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            System.out.println("something is wrong");
            e.printStackTrace();
        }
        holder.action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentIdToRegistered(model.getCompanyName(), mUser.getEmail());
                context.startActivity(new Intent(context.getApplicationContext(), DisplayCompanyActivity.class));
                ((Activity)context).finish();
            }
        });
    }

    private void addStudentIdToRegistered(String companyName, String userEmail) {

//        String key = mDatabase.child("registered").child(companyName).child("studentID").push().getKey();
//        mDatabase.child("registered").child(companyName).child("studentID").child(key).setValue(userEmail);

        String key = userEmail.substring(0, userEmail.indexOf("@"));
        mDatabase.child("registered").child(companyName).child("studentID").child(key).setValue("1");


//        Registered newRegisteration = new Registered(companyName, userEmail);
//        Map<String, Object> postValues = newRegisteration.toMap();
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/registered/"+companyName+"/"+key, postValues);
    }


    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item, parent, false);
        context = parent.getContext();
        return new DisplayCompanyAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder  extends RecyclerView.ViewHolder {

        TextView companyName,companyDescription,eligibleBranches,offeredCTC, offer, minCGPA, closingOn, closed, registered;
        Button action1;
        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            companyName=(TextView)itemView.findViewById(R.id.companyName);
            companyDescription=(TextView)itemView.findViewById(R.id.companyDesc);
            eligibleBranches=(TextView)itemView.findViewById(R.id.branches);
            offeredCTC=(TextView)itemView.findViewById(R.id.ctc);
            offer=(TextView)itemView.findViewById(R.id.offer);
            minCGPA=(TextView)itemView.findViewById(R.id.mincgpa);
            closingOn=(TextView) itemView.findViewById(R.id.closingOn);
            closed=(TextView) itemView.findViewById(R.id.closed);
            action1=(Button) itemView.findViewById(R.id.register);
            registered=(TextView) itemView.findViewById(R.id.registered);
        }
    }
}
