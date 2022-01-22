package com.example.mentalflow.Activity.Activity.Initial;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.R;

public class RegAgeActivity extends BaseActivity {

    private ImageButton maleButton;
    private ImageButton femaleButton;
    private Button mNextButton;
    private ImageButton mBackButton;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_age; //跳转年龄页
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.reg_back);
        mNextButton=findViewById(R.id.reg_next);
        maleButton=findViewById(R.id.reg_male);
        femaleButton=findViewById(R.id.reg_female);
    }

    @Override
    protected void initData() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegGenderActivity.class);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(Test0Activity.class); //跳转自测量表页
            }
        });
    }
}