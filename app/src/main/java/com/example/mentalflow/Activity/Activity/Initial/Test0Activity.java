package com.example.mentalflow.Activity.Activity.Initial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Fragment.TestFragment.TestPreFragment;
import com.example.mentalflow.R;

public class Test0Activity extends BaseActivity {

    private final int id = 0; //测试id
    private final String title = ""; //测试标题
    private final String content = ""; //测试内容
    private final String[] queList = new String[10]; //问题数组
    private final String[][] optList = new String[1][5]; //选项数组

    @Override
    protected int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() { }

    @Override
    protected void initData() {
        // 从上一个活动中获取测试编号

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

        TestPreFragment testPreFragment = new TestPreFragment();
        Bundle b =new Bundle();
        b.putSerializable("que_list",queList);
        b.putSerializable("opt_list",optList);
        testPreFragment.setArguments(b);
        setFragment(testPreFragment); //传入一个新的页面
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.test_layout, fragment); //为layout传入fragment
        transaction.commitNow();
    }
}
