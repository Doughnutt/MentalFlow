package com.example.mentalflow.Activity.Activity.Initial;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.R;

public class LoginActivity extends BaseActivity {
    private EditText mPhoneNumber;
    private Button mLoginButton;


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginButton=findViewById(R.id.login_next);
        mPhoneNumber=findViewById(R.id.login_et);
    }

    @Override
    protected void initData() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=mPhoneNumber.getText().toString().trim();
                login(phone);
                navigateTo(CheckActivity.class);
            }
        });
    }
    //正则表达式
    private void login(String phone) {
    }
    }