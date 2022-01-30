package com.example.mentalflow.Activity.Activity.Initial;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.R;

public class RegPasswordActivity extends BaseActivity {

    private EditText mCheck;
    private Button mNextButton;
    private ImageButton mBackButton;


    @Override
    protected int initLayout() {
        return R.layout.activity_reg_password;
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
                System.out.println(checkStr);
                int result = check(checkStr);
                if (result == 1) {
                    navigateTo(HomeActivity.class); //跳转主页
                } else if (result == 0) {
                    // 账号密码不匹配
                } else if(result == 2) {
                    navigateTo(RegNameActivity.class); //跳转注册：姓名
                } else if(result == 3) {
                    // 密码格式错误
                }
            }
        });
    }
    // 如果手机号存在，验证密码与手机号是否匹配：匹配返回 1，不匹配返回 0
    // 如果手机号不存在，验证密码是否符合格式：符合返回 2，不符合返回 3
    private int check(String checkStr) {
        return 2;
    }
}