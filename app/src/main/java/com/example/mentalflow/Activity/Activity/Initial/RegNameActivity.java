package com.example.mentalflow.Activity.Activity.Initial;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegNameActivity extends BaseActivity {

    private EditText mName;
    private Button mNextButton;
    private ImageButton mBackButton;
    private final Context context = RegNameActivity.this;

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
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String password = intent.getStringExtra("password");

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegPasswordActivity.class);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mName==null || mName.length()==0) {
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    String name = mName.getText().toString().trim();
                    if (!match(name)) {// 昵称不符合要求
                        Toast.makeText(context, "昵称必须包含2~10个字符", Toast.LENGTH_SHORT).show();
                    } else { //记录昵称并跳转
                        Intent intent = new Intent(RegNameActivity.this, RegGenderActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("password", password);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }
                }
            }
        });
    }
    //验证昵称是否符合要求
    private boolean match(String name) {
        String regEx = ".{2,10}"; //昵称为字数从2到10的任意字符
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
