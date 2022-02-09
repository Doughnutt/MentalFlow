package com.example.mentalflow.Activity.Activity.Initial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Fragment.TestFragment.TestPreFragment;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Test0PreActivity extends BaseActivity {

    private Button button;
    private final String[] queList = new String[10]; //问题数组
    private final String[][] optList = new String[1][5]; //选项数组

    @Override
    protected int initLayout() {
        return R.layout.activity_test0_pre;
    } //初量表准备页面

    @Override
    protected void initView() {
        button = findViewById(R.id.test0_start);
    }

    @Override
    protected void initData() {

        // 测试中的问题
        queList[0] = "近一周内我感觉情绪波动较大。";
        queList[1] = "我感到悲伤、失败或绝望。";
        queList[2] = "对于环境变换我感到害怕。";
        queList[3] = "遇到困难时有些人（朋友、亲戚、同事）会出现在我身边。";
        queList[4] = "在重要考试前几天我感到烦躁或坐立不安。";
        queList[5] = "我对很久以前或近期发生的事情感到不安或害怕。";
        queList[6] = "我的工作、睡眠或进食存在问题。";
        queList[7] = "在人多的时候我感到尴尬或无话可说。";
        queList[8] = "我时常对学习感到厌倦和烦躁。";
        queList[9] = "我对自己的能力和职业倾向感到困惑。";

        // 问题中的选项
        optList[0][0] = "极同意";
        optList[0][1] = "稍同意";
        optList[0][2] = "中立";
        optList[0][3] = "稍不同意";
        optList[0][4] = "极不同意";


        // 添加开始按钮点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test0PreActivity.this, Test0ProActivity.class);
                Bundle b =new Bundle();
                b.putInt("que_id",0); //传入跳转哪一个问题
                b.putSerializable("que_list",queList);
                b.putSerializable("opt_list",optList);
                int[] selected_opt = new int[10];
                Arrays.fill(selected_opt, -1); //初始化数组：将未选的选项标记为-1
                b.putSerializable("selected_opt",selected_opt);
                intent.putExtras(b);
                startActivity(intent); //跳转初量表过程页
                overridePendingTransition(0,0);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // 重载返回键监听事件，使进入自测量表后无法返回
        Toast.makeText(mContext,"此处无法返回，如需修改个人信息，请在完成注册后进入主页修改",Toast.LENGTH_LONG).show();
    }
}
