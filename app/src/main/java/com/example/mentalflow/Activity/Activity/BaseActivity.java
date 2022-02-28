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

    private long firstTime;// 记录点击返回时第一次的时间毫秒值
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){// 点击了返回按键
            exitApp(2000);// 退出应用
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出应用
     * @param timeInterval 设置第二次点击退出的时间间隔
     */
    private void exitApp(long timeInterval) {
        if(System.currentTimeMillis() - firstTime >= timeInterval){
            Toast.makeText(mContext,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            firstTime = System.currentTimeMillis();
        }else {
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }
}