package com.example.mentalflow.Activity.Fragment.TestFragment;

import android.annotation.SuppressLint;
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
import com.example.mentalflow.R;

import org.w3c.dom.Text;

import java.util.Objects;

// 测试启动页
public class TestPreFragment extends Fragment {
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_pre, container, false);
        Bundle b = getArguments();
        assert b != null;
        int id = b.getInt("test_id"); //获取id
        String title = b.getString("test_title"); //获取标题
        String content = b.getString("test_content"); //获取内容

        // 设置背景
        Resources resources = Objects.requireNonNull(getContext()).getResources();
        Drawable drawable = null;
        if (id == 0) {
            drawable = resources.getDrawable(R.mipmap.test0_bg1);
        }
        view.setBackground(drawable);

        // 显示内容
        TextView textView1 = (TextView) view.findViewById(R.id.test_pre_title);
        textView1.setText(title);
        TextView textView2 = (TextView) view.findViewById(R.id.test_pre_content);
        textView2.setText(content);

        // 准备数据
        String[] queList = new String[10]; //问题数组
        String[][] optList = new String[1][5]; //选项数组
        int queBg = 0; //问题背景
        if (id == 0) { //初量表
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
            // 问题背景
            queBg = R.mipmap.test0_bg2;
        }

        Button button = (Button) view.findViewById(R.id.test_start); //开始测试按钮
        int finalQueBg = queBg;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = getArguments();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                TestProFragment testProFragment = new TestProFragment();

                b.putSerializable("que_list",queList);
                b.putSerializable("opt_list",optList);
                b.putInt("test_id",id); //传入测试编号
                b.putInt("que_id",0); //传入问题编号
                b.putInt("que_bg", finalQueBg); //传入背景图片
                testProFragment.setArguments(b);

                transaction.replace(R.id.test_layout, testProFragment); //替换fragment为测试过程页
                transaction.commitNow();
            }
        });
        return view;
    }
}