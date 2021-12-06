package com.example.mentalflow.Activity.Activity;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import com.example.mentalflow.R;
/*
*起始页
*3S后自动跳入下一界面
*/

public class MainActivity extends BaseActivity {

    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
//           转入GuideActivity界面
            navigateTo(GuideActivity.class);

        }
    };


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        延缓3s
        handler.postDelayed(runnable,3000);
//        finish();
    }


}