package com.example.mentalflow.Activity.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.mentalflow.R;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }
    protected abstract int initLayout();//初始化布局
    protected abstract void initView();//初始化控件
    protected abstract void initData();//初始化数据
    public void navigateTo(Class cls) { //定义方法执行跳转
        Intent in = new Intent(mContext, cls);
        startActivity(in);
        overridePendingTransition(R.anim.empty,R.anim.empty);
    }
}