package com.example.mentalflow.Activity.Fragment.TestFragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.mentalflow.R;

import java.util.Objects;

// 测试过程页
public class TestProFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_pro, container, false);
        Bundle b = getArguments();
        String[] queList = (String[]) b.getSerializable("que_list");
        String[][] optList = (String[][]) b.getSerializable("opt_list");
        int test_id = b.getInt("test_id");
        final int[] que_id = {b.getInt("que_id")};

        // 设置这一页的背景
        Resources resources = Objects.requireNonNull(getContext()).getResources();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = resources.getDrawable(b.getInt("que_bg"));
        view.setBackground(drawable);

        // 设置这一页的问题
        final TextView[] textView = {(TextView) view.findViewById(R.id.question_content)};
        textView[0].setText(queList[que_id[0]]);

        // 设置选项
        // 1.记录选项个数
        int cnt = optList[0].length;
        // 2.设置按钮，添加进布局
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.opt_container);
        Button[] options = new Button[cnt]; //数组用于后面监听器的设置
        Drawable drawable1;
        TextView textView1;
        drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
        textView1 = new TextView(view.getContext());
        textView1.setBackground(drawable1);
        linearLayout.addView(textView1);
        for(int i=0;i<cnt;i++) {
            // 为了设置间距，添加一块透明的形状
            drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
            textView1 = new TextView(view.getContext());
            textView1.setBackground(drawable1);
            linearLayout.addView(textView1);
            // 添加按钮
            Button option = new Button(view.getContext());
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
        for(int i=0;i<cnt;i++) {
            int finalI = i;
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j=0;j<cnt;j++) {
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
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        int progress = 100 * que_id[0] / queList.length;
        progressBar.setProgress(progress);

        // 设置按钮
        LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.test_change_button);
        Button button_last = new Button(view.getContext());
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

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // 记录这一页的选项

                // 刷新该页中数据
                TestProFragment testProFragment = new TestProFragment();

                b.putSerializable("que_list",queList);
                b.putSerializable("opt_list",optList);
                b.remove("que_id");
                que_id[0] = que_id[0] - 1;
                b.putInt("que_id", que_id[0]); //传入问题编号
                b.putInt("test_id",test_id); //传入测试编号
                testProFragment.setArguments(b);
                transaction.replace(R.id.test_layout, testProFragment); //跳转结果页
                transaction.commitNow();
            }
        });

        Button button_next = new Button(view.getContext());
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

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if(que_id[0] != queList.length-1) { //如果不是最后一页
                    // 记录这一页的选项

                    // 刷新该页中数据
                    TestProFragment testProFragment = new TestProFragment();

                    b.putSerializable("que_list",queList);
                    b.putSerializable("opt_list",optList);
                    b.remove("que_id");
                    que_id[0] = que_id[0] + 1;
                    b.putInt("que_id", que_id[0]); //传入问题编号
                    b.putInt("test_id",test_id); //传入测试编号
                    testProFragment.setArguments(b);
                    transaction.replace(R.id.test_layout, testProFragment); //跳转结果页
                    transaction.commitNow();
                } else {
                    // 计算结果
                    String test_result = null;
                    // 跳转结果页
                    TestResFragment testResFragment = new TestResFragment();
                    b.putString("test_res",test_result);
                    transaction.replace(R.id.test_layout, testResFragment); //跳转结果页
                    transaction.commitNow();
                }

            }
        });

        TextView textView2;
        Drawable drawable2 = getResources().getDrawable(R.drawable.transparent_shape2);
        textView2 = new TextView(view.getContext());
        textView2.setBackground(drawable2);
        if(que_id[0] == 0) { //第一题
            ViewGroup.LayoutParams params = button_next.getLayoutParams();
            params.width = 600;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("下一题");
            linearLayout1.addView(button_next);
        } else if(que_id[0] == queList.length-1) { //最后一题
            ViewGroup.LayoutParams params = button_last.getLayoutParams();
            params.width = 280;
            params.height = 136;
            button_last.setLayoutParams(params);
            button_last.setText("上一题");
            params = button_next.getLayoutParams();
            params.width = 420;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("结束测试");
            linearLayout1.addView(button_last);
            linearLayout1.addView(textView2);
            linearLayout1.addView(button_next);
        } else {
            ViewGroup.LayoutParams params = button_last.getLayoutParams();
            params.width = 280;
            params.height = 136;
            button_last.setLayoutParams(params);
            button_last.setText("上一题");
            params = button_next.getLayoutParams();
            params.width = 420;
            params.height = 136;
            button_next.setLayoutParams(params);
            button_next.setText("下一题");
            linearLayout1.addView(button_last);
            linearLayout1.addView(textView2);
            linearLayout1.addView(button_next);
        }
        return view;
    }
}
