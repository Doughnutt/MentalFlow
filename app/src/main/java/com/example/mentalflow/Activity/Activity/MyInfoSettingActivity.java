package com.example.mentalflow.Activity.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Dialog.MyinfoAgeDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoGenderDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoIntroDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoNameDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoPswDialog;
import com.example.mentalflow.R;

// 修改用户信息
public class MyInfoSettingActivity extends BaseActivity{
    private FrameLayout mBt1;
    private FrameLayout mBt2;
    private FrameLayout mBt3;
    private FrameLayout mBt4;
    private FrameLayout mBt5;
    private ImageButton back;

    @Override
    protected int initLayout() {
        return R.layout.activity_setting_myinfo;
    }

    @Override
    protected void initView() {
        mBt1=findViewById(R.id.myinfo_mod_name_bt);
        mBt2=findViewById(R.id.myinfo_mod_psw_bt);
        mBt3=findViewById(R.id.myinfo_mod_age_bt);
        mBt4=findViewById(R.id.myinfo_mod_gender_bt);
        mBt5=findViewById(R.id.myinfo_mod_intro_bt);
        back=findViewById(R.id.myinfo_setting_back);
    }

    @Override
    protected void initData() {

        //返回键
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
