package com.college.placementportal.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Html;

import com.college.placementportal.R;
import com.college.placementportal.databinding.ActivityRulesBinding;

public class RulesActivity extends AppCompatActivity {

    ActivityRulesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rules);

        binding.t1.setText(Html.fromHtml(("<h1>"+getString(R.string.rulesInfo)+"</h1>")));
    }
}