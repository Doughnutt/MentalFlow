package com.example.mentalflow.Activity.Activity.Initial;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.R;

public class CheckActivity extends BaseActivity {

    private EditText mCheck;
    private Button mNextButton;
    private ImageButton mBackButton;


    @Override
    protected int initLayout() {
        return R.layout.activity_check;
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.check_back);
        mNextButton=findViewById(R.id.check_next);
        mCheck=findViewById(R.id.check_et);
    }

    @Override
    protected void initData() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkStr=mCheck.getText().toString().trim();
                check(checkStr);
                navigateTo(HomeActivity.class);
            }
        });
    }
    //验证码发放与对比方法
    private void check(String checkStr) {
    }
}