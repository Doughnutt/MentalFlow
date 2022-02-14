package com.example.mentalflow.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mentalflow.Activity.Dialog.TestBackDialog;
import com.example.mentalflow.Activity.Fragment.MainFragment.Test.TestPreFragment;
import com.example.mentalflow.Activity.Fragment.MainFragment.Test.TestProFragment;
import com.example.mentalflow.R;

public class TestActivity extends BaseActivity {

    private int id = 0; //测试id
    private int category = 0; //测试类别
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

        // 从主页中获取测试编号
        Intent intent = getIntent();
        id = intent.getIntExtra("test_id",0);
        category = intent.getIntExtra("test_category",0);

        // 根据编号匹配内容
        if(id == 1) {
            title = "领悟社会支持量表";// 测试标题
            // 测试内容
            content = "测试包含12个句子，每一个句子下有7个答案。\n请你根据自己的实际情况在每句后面选择一个答案。例如，选择“极不同意”，即说明您的实际情况与这一句子极不相符；选择“极同意”，即说明你的实际情况与这一句子极相符；选择“中立”表示中间状态。余类推。\n共计12题，预计耗时1分钟。";
        } else if(id == 2) {
            title = "威廉斯创造力倾向量表";
            content = "这是一份帮助你了解自己创造力的练习。\n在下列句子中，如果你发现某些句子所描述的情形很适合你，则选择“完全符合”；若有些句子只是在部分时候适合你，选择“部分适合”；如果有些句子对你来说，根本是不可能的，选择“完全不符”。\n共计50题，预计耗时5分钟。";
        } else if(id == 3) {
            title = "情绪紧张度测试";
            content = "生活节奏的加快、社会竞争的加剧以及频繁遭遇挫折等情况，都会使人产生紧张感。一个人如果长期处于紧张状态，就会降低身体免疫系统的抵抗能力，使人不能有效地适应外界环境而罹患各种疾病。因此，长期过度的紧张对人体是有害的。那么你的情绪紧张度怎样呢？\n回答下列题目，选择“有”或“无”。\n共计29道题目，预计耗时2分钟。";
        } else if(id == 4) {
            title = "情绪—社交孤独测试";
            content = "本问卷的目的是帮助你了解生活中的实际状况以及你对此时的体验如何。例如，你有个伴侣，但因关系不和，你并不觉得有伴侣。\n请根据最近两周酌情况回答，选择最适合于你的答案。\n共计30题，预计耗时2分钟。";
        } else if(id == 5) {
            title = "情绪稳定性测试";
            content = "情绪稳定是一个人心理健康成熟的标志，根据自己最近两周的情绪状况，完成下列题目，从三个选项中选择最符合自己的选项。\n共计30题，预计耗时2分钟。";
        } else if(id == 6) {
            title = "处世能力测验";
            content = "处世能力测试描述个体的社交能力，理解和利用情绪信息处理当前事件的能力，应变能力和在复杂人际冲突中解决问题的能力。\n处世能力强的个体在从事各种各样的、灵活多变的工作中，要求能够顾全大局、求同存异，果断而又细心，持重而又灵活，并且能够适度交际。\n考虑下列社会情境，如果你面临这种情境，选择与你的表现更相符合的选项。\n共计14题，预计耗时3分钟。";
        } else if(id == 7) {
            title = "交往焦虑量表";
            content = "请认真阅读下面的每个条目，并决定其陈述对你适用或真实的程度。选择最适合你的选项。\n共计15题，预计耗时2分钟。";
        } else if(id == 8) {
            title = "社会适应能力测试量表";
            content = "社会适应能力,是指一个人在心理上适应社会生活和社会环境的能力.社会适应能力的高低,从某种意义上说,表明一个人的成熟程度。\n下面的测验题目可帮助你更好地了解自己的社会适应能力.请选择最适合自己的选项。\n共计20题，预计耗时2分钟。";
        } else if(id == 9) {
            title = "社交回避及苦恼量表";
            content = "社交回避及苦恼分别指回避社会交往的倾向及身临其境时的苦恼感受。回避是一种行为表现，苦恼则为情感反应。\n回答下列题目，选择“是”或“否”。\n共计28道题目，预计耗时3分钟。";
        } else if(id == 10) {
            title = "信任量表";
            content = "使用以下标准表明你对下列每一陈述同意或不同意的程度：完全同意、部分同意、同意与不同意相等、部分不同意、完全不同意。\n请共计25题，预计耗时5分钟。";
        } else if(id == 11) {
            title = "贝克抑郁自评量表";
            content = "整个量表包括下面21组项目，每组有4句陈述，可根据一周来的感觉，选择最适合自己的陈述。\n共计21题，预计耗时5分钟。";
        } else if(id == 12) {
            title = "心理承受能力测试";
            content = "下面测试题可以测试出你的心理承受能力。完成测试，选择符合的描述。\n共计30题，预计耗时5分钟。";
        } else if(id == 13) {
            title = "自尊量表";
            content = "这个量表是用来了解您是怎样看待自己的。请仔细阅读下面的句子，选择最符合您情况的选项。\n请注意，这里要回答的是您实际上认为您自己怎样，而不是回答您认为您应该怎样。答案无正确与错误或好与坏之分，请按照您的真实情况来描述您自己。\n共计10题，预计耗时2分钟。";
        } else if(id == 14) {
            title = "考试焦虑自测量表";
            content = "下面的每一个句子都是你可能有的或曾经出现过的一般感受或体验，请认真阅读每一个句子，这里的答案无正确、错误之分，回答每一个问题时不必用太多时间去思考，但回答必须是最符合你通常感受的情况每一个问题都要回答。\n每题有4个备选答案，根据自己的实际情况，选择相应的选项，每题只能选择一个答案。\n共计33题，预计耗时8分钟。";
        } else if(id == 15) {
            title = "学习风格调查问卷";
            content = "所罗门（Soloman）从信息加工、感知、输入、理解四个方面将学习风格分为4个组对8种类型，它们是：活跃型与沉思型、感悟型与直觉型、视觉型与言语型、序列型与综合型，并设计了具有很强操作性的学习风格量表，可以较好地进行学习风格的测试。您属于什么样的学习风格？\n不妨利用下面的自测问卷表和分析表来测试一下。选择最符合自己的选项。\n共计44题，预计耗时15分钟。";
        }

        // 传值
        TestPreFragment testPreFragment = new TestPreFragment();
        Bundle b =new Bundle();
        b.putInt("test_id",id);
        b.putInt("test_category",category);
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
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.test_layout);
        if(current instanceof TestProFragment) {
            TestBackDialog dialog = new TestBackDialog(this, R.style.DialogTheme);
            dialog.show();
            Button b = (Button) dialog.findViewById(R.id.test_dialog_back);
            b.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TestActivity.this,HomeActivity.class);
                    intent.putExtra("select_page",1);
                    intent.putExtra("test_category",category);
                    startActivity(intent);
                }});
        } else {
            Intent intent = new Intent(TestActivity.this,HomeActivity.class);
            intent.putExtra("select_page",1);
            intent.putExtra("test_category",category);
            startActivity(intent);
        }
    }
}
