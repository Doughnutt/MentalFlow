package com.example.mentalflow.Activity.Activity.Initial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.R;

public class Test0ProActivity extends BaseActivity {

    private TextView textView1;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private LinearLayout linearLayout1;
    private Button[] options = new Button[5]; //数组用于后面监听器的设置

    @Override
    protected int initLayout() {
        return R.layout.activity_test0_pro;
    } //初量表测试页面

    @Override
    protected void initView() {
        textView1 = findViewById(R.id.test0_question_content);
        linearLayout = findViewById(R.id.test0_opt_container);
        progressBar = findViewById(R.id.test0_progress_bar);
        linearLayout1 = findViewById(R.id.test0_change_button);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {

        // 获取数据
        Bundle b = getIntent().getExtras();
        String[] queList = (String[]) b.getSerializable("que_list");
        String[][] optList = (String[][]) b.getSerializable("opt_list");
        final int[] que_id = {b.getInt("que_id")};

        textView1.setText(queList[que_id[0]]);//设置问题

        // 设置选项
        Drawable drawable1;
        TextView textView2;
        drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
        textView2 = new TextView(mContext);
        textView2.setBackground(drawable1);
        linearLayout.addView(textView2);
        for(int i=0;i<optList[0].length;i++) {
            // 为了设置间距，添加一块透明的形状
            drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
            textView2 = new TextView(mContext);
            textView2.setBackground(drawable1);
            linearLayout.addView(textView2);
            // 添加按钮
            Button option = new Button(mContext);
            if(optList.length==1) { //如果只有一种选项
                option.setText(optList[0][i]);
            } else { //如果每个问题的选项不同
                option.setText(optList[que_id[0]][i]);
            }
            option.setStateListAnimator(null);
            option.setTextSize(14);
            option.setTextColor(getResources().getColor(R.color.black));
            option.setBackground(getResources().getDrawable(R.drawable.test_option_button));
            option.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(option); //将按钮添加进布局
            options[i] = option;
        }
        // 3.设置按钮监听器
        for(int i=0;i<optList[0].length;i++) {
            int finalI = i;
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j=0;j<optList[0].length;j++) {
                        if(j!=finalI) {
                            options[j].setActivated(false); //其他按钮样式恢复
                            options[j].setTextColor(getResources().getColor(R.color.black));
                        } else {
                            options[j].setActivated(true); //该按钮样式改变
                            options[j].setTextColor(getResources().getColor(R.color.white));
                        }
                    }
                }
            });
        }

        // 设置进度条
        int progress = 100 * que_id[0] / queList.length;
        progressBar.setProgress(progress);

        // 设置按钮
        // 上一题
        Button button_last = new Button(mContext);
        button_last.setStateListAnimator(null);
        button_last.setTextSize(14);
        button_last.setTextColor(getResources().getColor(R.color.dark_gray));
        button_last.setBackground(getResources().getDrawable(R.drawable.transparent_button));
        button_last.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        button_last.setOnClickListener(new View.OnClickListener() { //上一题跳转
            @Override
            public void onClick(View v) {

                // 跳转该页
                b.putSerializable("que_list",queList);
                b.putSerializable("opt_list",optList);
                b.remove("que_id");
                que_id[0] = que_id[0] - 1;
                b.putInt("que_id", que_id[0]); //传入问题编号
                Intent intent = new Intent(Test0ProActivity.this,Test0ProActivity.class);
                intent.putExtras(b);
                startActivity(intent);
                overridePendingTransition(R.anim.empty,R.anim.empty);
            }
        });

        //下一题
        Button button_next = new Button(mContext);
        button_next.setStateListAnimator(null);
        button_next.setTextSize(14);
        button_next.setTextColor(getResources().getColor(R.color.white));
        button_next.setBackground(getResources().getDrawable(R.drawable.black_button));
        button_next.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        button_next.setOnClickListener(new View.OnClickListener() { //下一题跳转
            @Override
            public void onClick(View v) {

                int selected_id = -1; //记录被选择的选项id
                for(int i=0;i<options.length;i++) {
                    if(options[i].isActivated()) {
                        selected_id = i;
                        break;
                    }
                }

                if(selected_id == -1) { //如果没有选项被选
                    Toast.makeText(mContext,"请选择一个选项",Toast.LENGTH_SHORT).show();

                } else if(que_id[0] != queList.length-1) { //如果不是最后一页
                    // 记录这一页的选项
                    Intent intent = new Intent(Test0ProActivity.this,Test0ProActivity.class);
                    int[] selected_opt = (int[]) b.getSerializable("selected_opt"); //获取之前的选择
                    selected_opt[que_id[0]] = selected_id; //更新选项
                    b.putSerializable("selected_opt",selected_opt);

                    // 跳转该页
                    b.putSerializable("que_list",queList);
                    b.putSerializable("opt_list",optList);
                    b.remove("que_id");
                    que_id[0] = que_id[0] + 1;
                    b.putInt("que_id", que_id[0]); //传入问题编号

                    intent.putExtras(b);
                    startActivity(intent);
                    overridePendingTransition(R.anim.empty,R.anim.empty);
                } else { //如果是最后一页
                    // 读取选项，处理结果
                    int[] selected_opt = (int[]) b.getSerializable("selected_opt");
                    selected_opt[que_id[0]] = selected_id; //更新选项
                    cal(selected_opt);

                    // 跳转主页
                    navigateTo(HomeActivity.class);
                }
            }
        });

        TextView textView3;
        Drawable drawable2 = getResources().getDrawable(R.drawable.transparent_shape2);
        textView3 = new TextView(mContext);
        textView3.setBackground(drawable2);
        if(que_id[0] == 0) { //第一题
            ViewGroup.LayoutParams params = button_next.getLayoutParams();
            params.width = 600;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("下一题");
            linearLayout1.addView(button_next);
        } else if(que_id[0] == queList.length-1) { //最后一题
            ViewGroup.LayoutParams params = button_last.getLayoutParams();
            params.width = 250;
            params.height = 136;
            button_last.setLayoutParams(params);
            button_last.setText("上一题");
            params = button_next.getLayoutParams();
            params.width = 450;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("进入主页");
            linearLayout1.addView(button_last);
            linearLayout1.addView(textView3);
            linearLayout1.addView(button_next);
        } else {
            ViewGroup.LayoutParams params = button_last.getLayoutParams();
            params.width = 250;
            params.height = 136;
            button_last.setLayoutParams(params);
            button_last.setText("上一题");
            params = button_next.getLayoutParams();
            params.width = 450;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("下一题");
            linearLayout1.addView(button_last);
            linearLayout1.addView(textView3);
            linearLayout1.addView(button_next);
        }
    }

    private void cal(int[] opt) { //计算结果，存储数据库

        // 计算结果
        int[] res = new int[5];
        res[0] += 10-opt[0]-opt[5];
        res[1] += 10-opt[1]-opt[6];
        res[2] += 10-opt[2]-opt[7];
        res[3] += 10-opt[4]-opt[8];
        res[4] += 10-opt[3]-opt[9];

        // 从文件UserInfo中读取用户id
        SharedPreferences pref = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int id = pref.getInt("id",0);

        // 存储数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBOperator dbOperator = new DBOperator(); //调用数据库
                dbOperator.reg_insert_test0(id,res);
            }
        }).start();
    }
}
