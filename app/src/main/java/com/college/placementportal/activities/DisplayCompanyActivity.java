package com.college.placementportal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.college.placementportal.R;
import com.college.placementportal.databinding.ActivityDisplayCompanyBinding;

public class DisplayCompanyActivity extends AppCompatActivity {

    ActivityDisplayCompanyBinding binding;
    CardView cardview;
    Context context;
//    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutparams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_company);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_company);
        context = getApplicationContext();
//        binding.ll.
//        CreateCardViewProgrammatically();

//        Toast.makeText(getApplicationContext(), "Is it working", Toast.LENGTH_SHORT).show();
    }
//    public void CreateCardViewProgrammatically(){
//        cardview = new CardView(context);
//
//        layoutparams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//
//        cardview.setLayoutParams(layoutparams);
//        cardview.setRadius(15);
//        cardview.setPadding(25, 25, 25, 25);
//        cardview.setCardBackgroundColor(Color.MAGENTA);
//        cardview.setMaxCardElevation(30);
//        cardview.setMaxCardElevation(6);
//
//        binding.linearLayout.addView(cardview);

//    }
}

//public class MainActivity extends AppCompatActivity {
//    Button button;
//    Context context;
//    CardView cardview;
//    LayoutParams layoutparams;
//    TextView textview;
//    RelativeLayout relativeLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button = (Button)findViewById(R.id.button);
//        context = getApplicationContext();
//        relativeLayout = (RelativeLayout)findViewById(R.id.relativelayout1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CreateCardViewProgrammatically();
//            }
//        });
//    }
//}