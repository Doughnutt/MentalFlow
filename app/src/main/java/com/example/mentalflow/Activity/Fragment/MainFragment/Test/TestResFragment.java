package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.R;

// 测试结果页
public class TestResFragment extends Fragment {
    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_res, container, false);
        Bundle b = getArguments();
        assert b != null;
        int test_category = b.getInt("test_category"); //测试类别
        int id = b.getInt("test_id"); //测试id
        String result = b.getString("test_result"); //获取测试结果

        // 设置背景，顺便记录测试属于的分类
        Resources resources = requireContext().getResources();
        Drawable drawable = null;
        if (id == 1) {
            drawable = resources.getDrawable(R.mipmap.test1_bg1); //设置准备背景
        } else if(id == 2) {
            drawable = resources.getDrawable(R.mipmap.test2_bg1);
        } else if(id == 3) {
            drawable = resources.getDrawable(R.mipmap.test3_bg1);
        } else if(id == 4) {
            drawable = resources.getDrawable(R.mipmap.test4_bg1);
        } else if(id == 5) {
            drawable = resources.getDrawable(R.mipmap.test5_bg1);
        } else if(id == 6) {
            drawable = resources.getDrawable(R.mipmap.test6_bg1);
        } else if(id == 7) {
            drawable = resources.getDrawable(R.mipmap.test7_bg1);
        } else if(id == 8) {
            drawable = resources.getDrawable(R.mipmap.test8_bg1);
        } else if(id == 9) {
            drawable = resources.getDrawable(R.mipmap.test9_bg1);
        } else if(id == 10) {
            drawable = resources.getDrawable(R.mipmap.test10_bg1);
        } else if(id == 11) {
            drawable = resources.getDrawable(R.mipmap.test11_bg1);
        } else if(id == 12) {
            drawable = resources.getDrawable(R.mipmap.test12_bg1);
        } else if(id == 13) {
            drawable = resources.getDrawable(R.mipmap.test13_bg1);
        } else if(id == 14) {
            drawable = resources.getDrawable(R.mipmap.test14_bg1);
        } else if(id == 15) {
            drawable = resources.getDrawable(R.mipmap.test15_bg1);
        }
        view.setBackground(drawable);

        // 显示测试结果
        TextView textView1 = (TextView) view.findViewById(R.id.test_res_title);
        TextView textView2 = (TextView) view.findViewById(R.id.test_res_content);
        textView2.setText(result);
        if(id == 3 || id == 5 || id == 7 || id == 8 || id == 11) {
            int color = getResources().getColor(R.color.black); //必须这样设置颜色
            textView1.setTextColor(color);
            textView2.setTextColor(color);
        }

        // 结束测试按钮
        Button button = (Button) view.findViewById(R.id.test_end); //开始测试按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("select_page",1); //返回到测试首页
                intent.putExtra("test_category",test_category); //返回到这一测试类别
                startActivity(intent);
            }
        });
        return view;
    }
}
