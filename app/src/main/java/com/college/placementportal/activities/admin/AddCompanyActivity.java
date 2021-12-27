package com.college.placementportal.activities.admin;

import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.activities.DisplayCompanyActivity;
import com.college.placementportal.models.Company;
import com.college.placementportal.models.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddCompanyActivity extends AppCompatActivity {

    ImageView pic;
    Button submit;
    EditText name,desc,ctc,mincgpa,closingOn;
    CheckBox cs,is,ece,mech,bt,civil;
//    MaterialSpinner offerSpinner;
    AutoCompleteTextView actv;
    String offer;

    private DatabaseReference mDatabase;
    private DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);



        pic=findViewById(R.id.imageView);
        pic.setImageResource(R.drawable.nmamit_logo);

        submit=findViewById(R.id.submit);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        myref= FirebaseDatabase.getInstance().getReference().child("company");
//        offerSpinner = (MaterialSpinner) findViewById(R.id.offerSpinner);
//        offerSpinner.setItems(Arrays.asList(getResources().getStringArray(R.array.offer)));

//        offerSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                offer = item;
//                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
//            }
//        });

//        offerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                offer = offerSpinner.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "offer", Toast.LENGTH_SHORT).show();
//                Object item = adapterView.getItemAtPosition(i);
//                if (item != null) {
//                    Toast.makeText(getApplicationContext(), item.toString(),
//                            Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getApplicationContext(), "Selected",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
//            }
//        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,getResources().getStringArray(R.array.offer));
        //Getting the instance of AutoCompleteTextView
        actv =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=findViewById(R.id.comp_name);
                String dbname=name.getText().toString();

                desc=findViewById(R.id.comp_desc);
                String dbdesc=desc.getText().toString();

                ctc=findViewById(R.id.ctc);
                String dbctc=ctc.getText().toString();

                mincgpa=findViewById(R.id.cgpa);
                String dbmincgpa=mincgpa.getText().toString();

                offer = actv.getText().toString();

                closingOn = findViewById(R.id.closingOn);
                String closingDate = closingOn.getText().toString();




                //cs,is,ece,mech,bt,civil
                ArrayList<String> eligibleBranches = new ArrayList<String>();
                cs=findViewById(R.id.cs);
                is=findViewById(R.id.is);
                ece=findViewById(R.id.ece);
                mech=findViewById(R.id.mech);
                bt=findViewById(R.id.bt);
                civil=findViewById(R.id.civil);


                if(cs.isChecked()){
                    eligibleBranches.add("cs");
                }
                if(is.isChecked()){
                    eligibleBranches.add("is");
                }
                if(bt.isChecked()){
                    eligibleBranches.add("bt");
                }
                if(mech.isChecked()){
                    eligibleBranches.add("mech");
                }
                if(ece.isChecked()){
                    eligibleBranches.add("ece");
                }
                if(civil.isChecked()){
                    eligibleBranches.add("civil");
                }

                writeNewUser(dbname,dbdesc,eligibleBranches,dbctc,offer,dbmincgpa, closingDate);

                Toast.makeText(getApplicationContext(), "Company Added Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DisplayCompanyActivity.class));
                finish();
            }
        });
    }
    //writing student details onto database
    public void writeNewUser(String CompanyName, String companyDescription, ArrayList<String> eligibleBranches, String offeredCTC, String offer, String minCGPA, String closingDate) {


//        Toast.makeText(getApplicationContext(), "CompanyName"+CompanyName, Toast.LENGTH_SHORT).show();

        Company newCompany = new Company(CompanyName, companyDescription, eligibleBranches, offeredCTC, offer, minCGPA, closingDate);
        Map<String, Object> postValues = newCompany.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/company/" + CompanyName, postValues);

        mDatabase.updateChildren(childUpdates);//push to database

    }
}



