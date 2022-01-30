package com.example.mentalflow.Activity.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Activity.Initial.RegAgeActivity;
import com.example.mentalflow.Activity.Dialog.TestBackDialog;
import com.example.mentalflow.Activity.Fragment.TestFragment.TestPreFragment;
import com.example.mentalflow.R;

public class TestActivity extends BaseActivity {

    private int id = 0; //测试id
    private String title = ""; //测试标题
    private String content = ""; //测试内容

    @Override
    protected int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() { }

    @Override
    protected void initData() {

        // 从上一个活动中获取测试编号
        Intent intent = getIntent();
        id = intent.getIntExtra("test_id",0);

        // 根据编号匹配内容
        if(id == 0) {
            // 测试标题
            title = "为了对你的状态有初步一个了解\n请先完成这套自测量表";
            // 测试内容
            content = "量表共10题，大概耗时1分钟。\n包含情绪、心理健康、人际交往、学习、能力五个维度。\n采用五级打分制，每个句子后面有五个答案，\n请根据实际情况选择1个答案。";
        }

        // 传值
        TestPreFragment testPreFragment = new TestPreFragment();
        Bundle b =new Bundle();
        b.putInt("test_id",id);
        b.putString("test_title",title);
        b.putString("test_content",content);
        testPreFragment.setArguments(b);
        setFragment(testPreFragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.test_layout, fragment); //为layout传入fragment
        transaction.commitNow();
    }

    @Override
    public void onBackPressed() //设置对话框中返回的监听事件
    {
        TestBackDialog dialog = new TestBackDialog(this,R.style.DialogTheme);
        dialog.show();
        Button b = (Button) dialog.findViewById(R.id.test_dialog_back);
        b.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,TestActivity.class);
                startActivity(intent);
            }});

    }
}
