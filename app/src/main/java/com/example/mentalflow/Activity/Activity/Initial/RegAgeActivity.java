package com.example.mentalflow.Activity.Activity.Initial;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegAgeActivity extends BaseActivity {

    private EditText mAge;
    private Button mNextButton;
    private ImageButton mBackButton;
    private final Context context  = RegAgeActivity.this;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_age; //跳转年龄页
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.reg_back);
        mNextButton=findViewById(R.id.reg_next);
        mAge=findViewById(R.id.reg_age);
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

                String age = mAge.getText().toString().trim();
                if(mAge == null || mAge.length() == 0) {
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else if(!match(age)){
                    Toast.makeText(context, "请输入正确的年龄", Toast.LENGTH_SHORT).show();
                } else {
                    // 将数据添加进数据库！

                    //跳转自测量表页
                    navigateTo(Test0PreActivity.class);
                }
            }
        });
    }

    private boolean match(String age) {
        String regEx = "[0-9]|[1-9][0-9]|1[0-4][0-9]"; //年龄范围在0~149
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }

    private void add_data(String phone,String password,String name,String gender,int age) {

    }
}