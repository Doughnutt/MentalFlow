package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.R;

// 测试过程页
public class TestProFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_pro, container, false);
        Bundle b = getArguments();
        assert b != null;
        int[] selected_opt = (int[]) b.getSerializable("selected_opt");
        int test_category = b.getInt("test_category",0);
        String[] queList = (String[]) b.getSerializable("que_list");
        int queSize = b.getInt("que_size");
        String[][] optList = (String[][]) b.getSerializable("opt_list");
        int optSize = b.getInt("opt_size");
        int test_id = b.getInt("test_id");
        final int[] que_id = {b.getInt("que_id")};

        // 设置这一页的背景
        Resources resources = requireContext().getResources();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = resources.getDrawable(b.getInt("que_bg"));
        view.setBackground(drawable);
        if(optSize == 2 || optSize == 3) {
            view.setPadding(0,200,0,0);
        } else if(optSize == 4) {
            view.setPadding(0,100,0,0);
        }

        // 设置这一页的问题
        TextView textView = (TextView) view.findViewById(R.id.question_content);
        String now_que = queList[que_id[0]];
        textView.setText(now_que);
        if(test_id == 1 || test_id == 10 || test_id == 12 || test_id == 13 || test_id == 15) {
            int color = getResources().getColor(R.color.white); //必须这样设置颜色
            textView.setTextColor(color);
        }
        if(now_que.length() > 20) {
            textView.setTextSize(17);
        }

        // 设置选项
        // 1.记录选项个数
        // 2.设置按钮，添加进布局
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.opt_container);
        Button[] options = new Button[optSize]; //数组用于后面监听器的设置
        Drawable drawable1;
        TextView textView1;
        if(test_id != 1) {
            drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
            textView1 = new TextView(view.getContext());
            textView1.setBackground(drawable1);
            linearLayout.addView(textView1);
        }
        for(int i = 0; i< optSize; i++) {
            // 为了设置间距，添加一块透明的形状
            if(test_id == 1) {
                drawable1 = getResources().getDrawable(R.drawable.transparent_shape3);
            } else {
                drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
            }
            textView1 = new TextView(view.getContext());
            textView1.setBackground(drawable1);
            linearLayout.addView(textView1);
            // 添加按钮
            Button option = new Button(view.getContext());
            option.setPadding(10,5,10,5);
            if(optSize > 5) {
                LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1);
                option.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            }
            if(optList[1][0]==null || optList[1][0].length()==0) { //如果只有一种选项
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
        if(test_id != 1) {
            drawable1 = getResources().getDrawable(R.drawable.transparent_shape1);
            textView1 = new TextView(view.getContext());
            textView1.setBackground(drawable1);
            linearLayout.addView(textView1);
        }

        // 3.设置按钮监听器
        for(int i = 0; i< optSize; i++) {
            int finalI = i;
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j = 0; j< optSize; j++) {
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
        if(test_id == 1) { //因为测试1有7个选项 放不下
            progressBar.setVisibility(View.GONE); //设置不存在
        } else {
            int progress = 100 * que_id[0] / queSize;
            progressBar.setProgress(progress);
        }
        ProgressBar progressBar1 = (ProgressBar) view.findViewById(R.id.progress1_bar);
        if(test_id == 1) { //测试1
            int progress = 100 * que_id[0] / queSize;
            progressBar1.setProgress(progress);
        } else {
            progressBar1.setVisibility(View.GONE); //设置不存在
        }

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

                // 刷新该页中数据
                TestProFragment testProFragment = new TestProFragment();

                b.putInt("test_category",test_category);
                b.putSerializable("que_list",queList);
                b.putSerializable("opt_list",optList);
                b.putInt("que_size",queSize);
                b.putInt("opt_size",optSize);
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

                int selected_id = -1; //记录被选择的选项id
                for(int i=0;i<optSize;i++) {
                    if(options[i].isActivated()) {
                        selected_id = i;
                        break;
                    }
                }

                if(selected_id == -1) { //如果没有选项被选
                    Toast.makeText(getContext(),"请选择一个选项",Toast.LENGTH_SHORT).show();

                } else if(que_id[0] != queSize-1) { //如果不是最后一页
                    // 记录这一页的选项
                    selected_opt[que_id[0]] = selected_id; //更新选项
                    b.putSerializable("selected_opt",selected_opt);

                    // 刷新该页中数据
                    TestProFragment testProFragment = new TestProFragment();
                    b.putInt("test_category",test_category);
                    b.putSerializable("que_list",queList);
                    b.putSerializable("opt_list",optList);
                    b.putInt("que_size",queSize);
                    b.putInt("opt_size",optSize);
                    b.remove("que_id");
                    que_id[0] = que_id[0] + 1;
                    b.putInt("que_id", que_id[0]); //传入问题编号
                    b.putInt("test_id",test_id); //传入测试编号
                    testProFragment.setArguments(b);
                    transaction.replace(R.id.test_layout, testProFragment); //跳转结果页
                    transaction.commitNow();
                } else {
                    // 计算结果
                    String test_result = cal(selected_opt,test_id,queSize);

                    // 跳转结果页
                    TestResFragment testResFragment = new TestResFragment();
                    b.putInt("test_category",test_category);
                    b.putInt("test_id",test_id);
                    b.putString("test_result",test_result);
                    testResFragment.setArguments(b);
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
        } else if(que_id[0] == queSize-1) { //最后一题
            ViewGroup.LayoutParams params = button_last.getLayoutParams();
            params.width = 280;
            params.height = 136;
            button_last.setLayoutParams(params);
            button_last.setText("上一题");
            if(test_id == 1 || test_id == 12 || test_id == 13) {
                int color = getResources().getColor(R.color.white); //必须这样设置颜色
                button_last.setTextColor(color);
            }
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
            if(test_id == 1 || test_id == 12 || test_id == 13) {
                int color = getResources().getColor(R.color.white); //必须这样设置颜色
                button_last.setTextColor(color);
            }
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

    private String cal(int[] opt, int id,int queSize) { //计算并返回结果

        String res = "";
        // 计算结果
        if(id == 1) {
            int sum = 0;
            for(int i = 0; i < queSize; i ++) {
                sum += (7 - opt[i]);
            }
            res = "量表采用七级计分法，领悟社会支持总分由所有条目分累加，以总分反映个体感受到的社会支持总程度。\n总分在12—36之间为低支持状态；总分在37—60之间为中间支持状态；总分在61—84之间为高支持状态。总分越高，说明个体的社会支持越高。\n";
            res += "你的得分是" + sum + "，";
            if(sum >= 12 && sum <= 36) {
                res += "代表你处于低支持状态。";
            } else if(sum <= 60) {
                res += "代表你处于中间支持状态。";
            } else {
                res += "代表你处于高支持状态。";
            }
        } else if(id == 2) {
            int sum = 0;
            int[] sco = new int[4]; //分别冒险性、好奇性、想象力、挑战性的得分
            for(int i=0;i<queSize;i++) {
                if(i == 11 || i == 28 || i == 34 || i == 44 || i == 47) {
                    opt[i] = opt[i] + 1;
                } else {
                    opt[i] = 3 - opt[i];
                }
            }
            sco[0] += opt[0]+opt[4]+opt[20]+opt[23]+opt[24]+opt[27]+opt[28]+opt[34]+opt[35]+opt[42]+opt[43];
            sco[1] += opt[1]+opt[7]+opt[10]+opt[11]+opt[18]+opt[26]+opt[32]+opt[33]+opt[36]+opt[37]+opt[38]+opt[46]+opt[47]+opt[48];
            sco[2] += opt[5]+opt[12]+opt[13]+opt[15]+opt[19]+opt[21]+opt[22]+opt[29]+opt[30]+opt[31]+opt[39]+opt[44]+opt[45];
            sco[3] += opt[2]+opt[3]+opt[6]+opt[8]+opt[9]+opt[14]+opt[16]+opt[17]+opt[25]+opt[40]+opt[41]+opt[49];
            sum = sco[0]+sco[1]+sco[2]+sco[3];

            res = "本量表结果包含冒险性、好奇性、想象力、挑战性四项，得分高说明能力强，得分低说明能力差。你的得分情况如下：\n";
            res += "冒险性："+sco[0]+" 好奇性："+sco[1]+" 想象力："+sco[2]+" 挑战性："+sco[3]+"\n";
            res += "你最终获得总分："+sum;
        } else if(id == 3) {
            int sum = 0;
            for(int i=0;i< queSize;i++) {
                sum += 1-opt[i];
            }
            res = "你的得分为"+sum+"\n";
            if(sum <= 9) {
                res += "你的测试结果表明你的情绪紧张度处于正常范围，不需要采取任何措施。";
            } else if(sum <=19) {
                res += "你的测试结果表明你有轻度紧张症。\n";
                res += "对于轻度紧张症可以采取保护性措施，如用阅读、书法、绘画、养花、钓鱼等进行自我调节，松弛紧张状态。积极参加体育活动，增强体质，工作之后的文娱活动等也可缓解紧张、消除疲劳。还应当养成有规律的生活习惯，适当增加营养，提高意志力。";
            } else if(sum <=24) {
                res += "你的测试结果表明你有中度紧张症。\n";
                res += "你需要进行健康检查，或心理咨询及心理治疗。";
                res += "\n缓解紧张情绪，专家给出以下建议：\n1．避开紧张源。\n2．采用自我防御机制。\n3．培养良好的性格。\n4．适度的体育锻炼。\n5．身体放松法。";
            } else {
                res += "你的测试结果表明你有重度紧张症。\n";
                res += "你需要进行健康检查，或心理咨询及心理治疗。";
                res += "\n缓解紧张情绪，专家给出以下建议：\n1．避开紧张源。\n2．采用自我防御机制。\n3．培养良好的性格。\n4．适度的体育锻炼。\n5．身体放松法。";
            }
        } else if(id == 4) {
            res = "测试结果包含四个部分：情绪孤立、社交孤立、情绪孤独、社交孤独。\n";
            // 情绪孤立
            int tmp = 0;
            for(int i=0;i<8;i++) {
                tmp += 3 - opt[i];
            }
            res += "你的情绪孤立得分为"+tmp+"，";
            if(tmp < 6) {
                res += "说明在情绪方面无或几无孤立。";
            } else if(tmp <= 8) {
                res += "说明在情绪方面偶尔存在孤立现象。";
            } else if(tmp <= 12) {
                res += "说明在情绪方面存在属于一般人的孤立现象。";
            } else {
                res += "说明在情绪方面你的孤立问题严重。";
            }
            res += "\n";
            // 社交孤立
            tmp = 0;
            for(int i=8;i<15;i++) {
                tmp += 3 - opt[i];
            }
            res += "你的社交孤立得分为"+tmp+"，";
            if(tmp < 6) {
                res += "说明在社交方面无或几无孤立。";
            } else if(tmp <= 8) {
                res += "说明在社交方面存在一般人的孤立现象。";
            } else if(tmp <= 12) {
                res += "说明在社交方面存在高于一般人的孤立现象。";
            } else {
                res += "说明在社交方面你的孤立问题严重。";
            }
            res += "\n";
            // 情绪孤独
            tmp = 0;
            for(int i=15;i<23;i++) {
                tmp += 3 - opt[i];
            }
            res += "你的情绪孤独得分为"+tmp+"，";
            if(tmp < 6) {
                res += "说明在情绪方面无或几无孤独。";
            } else if(tmp <= 10) {
                res += "说明在情绪方面存在一般人的孤独现象。";
            } else if(tmp <= 14) {
                res += "说明在情绪方面存在高于一般人的孤独现象。";
            } else {
                res += "说明在情绪方面你的孤独问题严重。";
            }
            res += "\n";
            // 社交孤独
            tmp = 0;
            for(int i=23;i<30;i++) {
                tmp += 3 - opt[i];
            }
            res += "你的社交孤独得分为"+tmp+"，";
            if(tmp <= 4) {
                res += "说明在社交方面无或几无孤独。";
            } else if(tmp <= 9) {
                res += "说明在社交方面存在一般人的孤独现象。";
            } else if(tmp <= 13) {
                res += "说明在社交方面存在高于一般人的孤独现象。";
            } else {
                res += "说明在社交方面你的孤独问题严重。";
            }
        } else if(id == 5) {
            int sum = 0;
            for (int j=0;j<queSize;j++) {
                if (opt[j] == 0) {
                    sum += 2;
                } else if (opt[j] == 2) {
                    sum += 1;
                }
            }
            res = "你的测试结果总分为"+sum+"。\n";
            if(sum <=20) {
                res += "你的情绪稳定，自信心强，具有较强的美感、道德感和理智感。你有一定的社会活动能力，能理解周围的人们的感情，顾全大局。你一定是个性情爽朗、受欢迎的人。";
            } else if(sum <= 40) {
                res += "你情绪基本稳定，但较为深沉，对事情的考虑过于冷静，处世淡漠消极，不善于发挥自己的个性。你的自信心受到压抑，办事热情忽高忽低，瞻前顾后，踌躇不前。";
            } else {
                res += "你的情绪极不稳定，日常烦恼太多，使你自己的心情处于紧张和矛盾之中。\n如果你得分在50分以上，则可能是一种危险信号，你很可能需要请心里医生或精神科医生进一步诊断。";
            }
        } else if(id == 6) {
            int a = 0,b = 0,c = 0,d = 0;
            for(int i =0;i<queSize;i++) {
                if(opt[i] == 0) {
                    a ++;
                } else if (opt[i] == 1) {
                    b ++;
                } else if(opt[i] == 2) {
                    c ++;
                } else {
                    d ++;
                }
            }

            if(d >= a && d >= b && d >= c) {
                res = "你有积极而理智的处事态度，遇事表现出较强克制能力。尊重他人，对人诚恳坦率，不喜欢虚假和装模作样。结果是人们尊重你，愿意和你交往，建立友情关系。";
            } else if(c > d && c >= b && c >= a) {
                res = "你具有一定的处事所需要的克制力，能把怨气或不满意情绪隐藏起来，比前面两种人更善于处理人与人之间的关系，只是有时为人不够真诚坦率，结果是使人们感到你有时表现得比较虚伪或不能完全理解你。";
            } else if(b > c && b > d && b >= a) {
                res = "你的出世能力较差，不善于待人接物，往往属于好斗型，遇不顺利的事容易暴跳如雷，甚至粗鲁地骂人。表明看来，你颇能占上风，其实得不到他人对你的尊重，结果是使人们憎恶你或害怕和疏远你。";
            } else {
                res = "你的出世态度过于消极，凡事与世无争，实际上心中并不一定服气，对任何有争论性的事，你都不愿意表态，希望他人作决定或承担责任。但人们了解你的时候，也许会同情你，但以后又会产生反感。 ";
            }

        } else if(id == 7) {
            int sum = 0;
            for(int i=0;i<queSize;i++) {
                if(i == 2 || i == 5 || i == 9 || i == 14) {
                    sum += opt[i] + 1;
                } else {
                    sum += 5 - opt[i];
                }
            }
            res = "量表总分从15分（社交焦虑程度最低）到75分（社交焦虑程度最高），焦虑程度与总分呈正比，大学生平均分为38.9，标准差为9.7。\n";
            res += "你的焦虑程度得分为：" +sum+"分。";
        } else if(id == 8) {
            int sum = 0;
            for(int i=0;i<queSize;i++) {
                if(i % 2 == 0 || i == 7) {
                    sum += opt[i];
                } else {
                    sum += 2-opt[i];
                }
            }
            res = "最终得分为："+sum+"分，";
            if(sum <= 5) {
                res += "你的社会适应能力很差。\n";
            } else if(sum <= 16) {
                res += "你的社会适应能力较差。\n";
            } else if(sum <= 28) {
                res += "你的社会适应能力一般。\n";
            } else if(sum <= 34) {
                res += "你的社会适应能力良好。\n";
            } else if(sum <= 40) {
                res += "你的社会适应能力很强。\n";
            }
            res += "如果你在本测验中的得分较低，你不必忧心忡忡，因为一个人的社会适应能力是随着年龄增长、知识、经验的丰富而不断增强的。只要你有信心，努力学习，加强锻炼，一定会成为适应社会的成功者。";
        } else if(id == 9) {
            int a = 0,b = 0; //a记录回避，b记录苦恼
            for(int i=0;i<queSize;i++) {
                if(i==1||i==4||i==7||i==9||i==10||i==12||i==13||i==15||i==17||i==19||i==20||i==22||i==23||i==25) {
                    opt[i] = 1 - opt[i];
                }
            }
            a = opt[1]+opt[3]+opt[7]+opt[8]+opt[12]+opt[16]+opt[17]+opt[18]+opt[20]+opt[21]+opt[23]+opt[24];
            res = "在社交回避方面，你获得的分数为："+a+"，";
            if(a<(4.14+2.62)) {
                res += "你的表现正常，没有这方面问题。\n";
            } else if(a<(4.14+2*2.62)) {
                res += "你在这方面可能存在一定程度的问题，需要接受进一步的专业人员的检查。\n";
            } else{
                res += "你存在这方面的问题，需要接受专业的帮助。\n";
            }
            b = opt[0]+opt[2]+opt[4]+opt[5]+opt[6]+opt[9]+opt[10]+opt[11]+opt[13]+opt[14]+opt[15]+opt[19]+opt[22];
            res += "在社交苦恼方面，你获得的分数为："+b+"，";
            if(b<(3.92+3.1)) {
                res += "你的表现正常，没有这方面问题。\n";
            } else if(b<(3.92+2*3.1)) {
                res += "你在这方面可能存在一定程度的问题，需要接受进一步的专业人员的检查。\n";
            } else{
                res += "你存在这方面的问题，需要接受专业的帮助。\n";
            }
            res += "总体来看，你获得的分数为："+(a+b)+"，";
            if(b<(8.03+4.64)) {
                res += "你的表现正常，没有社交问题。\n";
            } else if(b<(8.03+2*4.64)) {
                res += "你可能存在一定程度的社交问题，需要接受进一步的专业人员的检查。\n";
            } else{
                res += "你存在社交问题，需要接受专业的帮助。\n";
            }
        } else if(id == 10) {
            int sum = 0;
            for(int i=0;i<queSize;i++) {
                if(i==5||i==7||i==11||i==13||i==15||i==16||i==17||i==19||i==20||i==21||i==22||i==24) {
                    sum += 5 - opt[i];
                } else {
                    sum += opt[i]+1;
                }
            }
            res = "对于本测试，得高分者人际信任度也高。 总分从25分（信赖程度最低）至125分（信赖程度最高），中间值为75分。\n";
            res += "你得到的分数为："+sum+"。";
        } else if(id == 11) {
            int sum = 0;
            for (int i=0;i<queSize;i++) {
                sum += opt[i];
            }
            res = "你得到的分数为："+sum+"，";
            if(sum<10) {
                res += "表明你很健康，无抑郁。";
            } else if(sum <= 15) {
                res += "表明你有轻度情绪不良，要注意调节。";
            } else if(sum <= 25) {
                res += "表明已有抑郁，要去看心理医生了。";
            } else {
                res += "表明你的抑郁已经比较严重了，必须看心理医生。";
            }
        } else if(id == 12) {
            int sum = 0;
            for (int i=0;i<15;i++) {
                sum += opt[i];
            }
            for (int i=15;i<30;i++) {
                sum += 1-opt[i];
            }
            res = "你得到的分数为："+sum+"。\n";
            if(sum<10) {
                res += "你的心理承受能力差，你遇到困难易灰心，常常有挫折感。";
            } else if(sum <= 20) {
                res += "你的心理承受能力一般，你能轻松地承受一些小的压力，但遇到大的打击时，还是容易产生心理危机。";
            } else {
                res += "你的心理承受能力很强，你能在各种艰难困苦面前保持旺盛的斗志。";
            }
        } else if(id == 13) {
            res = "总分范围是10—40分，分值越高，自尊程度越高。\n";
            int sum = 0;
            for (int i=0;i<queSize;i++) {
                if(i==0||i==1||i==3||i==5||i==6||i==7) {
                    sum += 4-opt[i];
                } else {
                    sum += 1+opt[i];
                }
            }
            res += "你得到的分数为："+sum+"。";
        } else if(id == 14) {
            int sum = 0;
            for (int i=0;i<queSize;i++) {
                sum += 3-opt[i];
            }
            res += "你得到的分数为："+sum+"。\n";
            if(sum <= 24) {
                res += "你的焦虑等级为“镇定”，考试前基本没有紧张不安的状态，能正常地复习与应考。";
            } else if(sum <= 49) {
                res += "你的焦虑等级为“轻度焦虑”，在考试前较短的一段时间内，会感到紧张和害怕，但不影响复习和应考。";
            } else if(sum <= 74) {
                res += "你的焦虑等级为”中度焦虑“，在考试前较长的一段时间内，感到紧张害怕和忧虑，复习效率降低，睡眠饮食受影响，有必要进行调整。";
            } else {
                res += "你的焦虑等级为”重度焦虑“，在考试前很长的一段时间内，感到忧虑、恐惧，会产生各种心因性疾病，严重影响复习与考试的进行，有必要进行心理咨询和心理治疗。";
            }
        } else if(id == 15) {
            int[][] sco = new int[4][2];
            for(int i=0;i<queSize;i++) {
                if(i==0||i==4||i==8||i==12||i==16||i==20||i==24||i==28||i==32||i==36||i==40) {
                    sco[0][opt[i]]++;
                } else if(i==1||i==5||i==9||i==13||i==17||i==21||i==25||i==29||i==33||i==37||i==41) {
                    sco[1][opt[i]]++;
                } else if(i==2||i==6||i==10||i==14||i==18||i==22||i==26||i==30||i==34||i==38||i==42) {
                    sco[2][opt[i]]++;
                } else {
                    sco[3][opt[i]]++;
                }
            }
            res="根据测试结果，将从四个方面分析你的学习风格。每个分数表示你的程度差异，分数越高，程度越强烈。\n";
            //第一类
            if(sco[0][0]>sco[0][1]) {
                res += "你属于活跃型的学习风格，分数为";
            } else {
                res += "你属于沉思型的学习风格，分数为";
            }
            int tmp = Math.abs(sco[0][0]-sco[0][1]);
            res += tmp+"，";
            if(tmp > 5) {
                res += "程度强烈";
            } else if(tmp == 5) {
                res += "程度一般";
            } else {
                res += "程度较弱";
            }
            res += "\n";
            // 第二类
            if(sco[1][0]>sco[1][1]) {
                res += "你属于感悟型的学习风格，分数为";
            } else {
                res += "你属于直觉型的学习风格，分数为";
            }
            tmp = Math.abs(sco[1][0]-sco[1][1]);
            res += tmp+"，";
            if(tmp > 5) {
                res += "程度强烈";
            } else if(tmp == 5) {
                res += "程度一般";
            } else {
                res += "程度较弱";
            }
            res += "\n";
            // 第三类
            if(sco[2][0]>sco[2][1]) {
                res += "你属于视觉型的学习风格，分数为";
            } else {
                res += "你属于言语型的学习风格，分数为";
            }
            tmp = Math.abs(sco[2][0]-sco[2][1]);
            res += tmp+"，";
            if(tmp > 5) {
                res += "程度强烈";
            } else if(tmp == 5) {
                res += "程度一般";
            } else {
                res += "程度较弱";
            }
            res += "\n";
            // 第四类
            if(sco[3][0]>sco[3][1]) {
                res += "你属于序列型的学习风格，分数为";
            } else {
                res += "你属于综合型的学习风格，分数为";
            }
            tmp = Math.abs(sco[3][0]-sco[3][1]);
            res += tmp+"，";
            if(tmp > 5) {
                res += "程度强烈";
            } else if(tmp == 5) {
                res += "程度一般";
            } else {
                res += "程度较弱";
            }
        }

        return res;
    }
}
