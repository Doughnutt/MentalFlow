package com.example.mentalflow.Activity.Activity.Initial;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.R;

public class RegNameActivity extends BaseActivity {

    private EditText mName;
    private Button mNextButton;
    private ImageButton mBackButton;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_name; //跳转注册昵称页面
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.reg_back);
        mNextButton=findViewById(R.id.reg_next);
        mName=findViewById(R.id.reg_name);
    }

    @Override
    protected void initData() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(CheckActivity.class);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkStr=mName.getText().toString().trim();
                if(check(checkStr)) {
                    navigateTo(RegGenderActivity.class); //跳转性别
                } else {
                    // 姓名不符合要求
                }
            }
        });
    }
    //验证昵称是否符合要求
    private boolean check(String checkStr) {
        return true;
    }
}
