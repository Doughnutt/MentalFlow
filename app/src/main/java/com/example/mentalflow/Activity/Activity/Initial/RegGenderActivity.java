package com.example.mentalflow.Activity.Activity.Initial;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.R;

public class RegGenderActivity extends BaseActivity {

    private ImageButton maleButton;
    private ImageButton femaleButton;
    private Button mNextButton;
    private ImageButton mBackButton;
    private final Context context = RegGenderActivity.this;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_gender; //跳转性别页
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.reg_back);
        mNextButton=findViewById(R.id.reg_next);
        maleButton=findViewById(R.id.reg_male);
        femaleButton=findViewById(R.id.reg_female);
    }

    // 标记性别被选中
    boolean male = false;
    boolean female = false;

    @Override
    protected void initData() {

        // 如果男被选中
        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 修改female状态
                female = false;
                femaleButton.setActivated(false);
                // 修改male状态
                male = true;
                maleButton.setActivated(true);
            }
        });

        // 如果女被选中
        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 修改female状态
                female = true;
                femaleButton.setActivated(true);
                // 修改male状态
                male = false;
                maleButton.setActivated(false);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegNameActivity.class);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maleButton.isActivated()) { // 如果选了男

                    navigateTo(RegAgeActivity.class);
                } else if(femaleButton.isActivated()) { // 如果选了女

                    navigateTo(RegAgeActivity.class);
                } else { //如果两个都没选
                    Toast.makeText(context, "请选择性别", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}