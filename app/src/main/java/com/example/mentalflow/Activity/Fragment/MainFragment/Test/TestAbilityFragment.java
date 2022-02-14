package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.Activity.Activity.Initial.RegPasswordActivity;
import com.example.mentalflow.Activity.Adapter.TestRecyclerAdapter;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.PretestRes;
import com.example.mentalflow.Activity.Entity.TestCard;
import com.example.mentalflow.Activity.Entity.UserInfo;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestAbilityFragment extends Fragment {
    private String title;
    private RecyclerView recyclerView;
    private List<TestCard> testCardsList = new ArrayList<>();
    private TestRecyclerAdapter testRecyclerAdapter;

    public static TestAbilityFragment newInstance(String title) { //
        TestAbilityFragment fragment = new TestAbilityFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            PretestRes pretestRes = (PretestRes) msg.obj;
            // 将读取到的数据存储文件
            SharedPreferences.Editor editor = getContext().getSharedPreferences("TestRec",Context.MODE_PRIVATE).edit();
            editor.putInt("ability",pretestRes.getAbility());
            editor.putInt("emotion",pretestRes.getEmotion());
            editor.putInt("relationship",pretestRes.getRelationship());
            editor.putInt("psychology", pretestRes.getPsychology());
            editor.putInt("study", pretestRes.getStudy());
            editor.apply();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_test_ability, container, false);
        recyclerView=v.findViewById(R.id.test_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        if(testCardsList.size() == 0){ //只有第一次需要添加测试内容
            initTestView();
            System.out.println(testCardsList.size());
        }
        testRecyclerAdapter =new TestRecyclerAdapter(getActivity(),testCardsList);
        recyclerView.setAdapter(testRecyclerAdapter);
        return v;
    }

    private void initTestView() {
        // 为每个类别填入测试
        if(title.equals("推荐")) {
            // 读取用户id
            SharedPreferences pref = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            int id = pref.getInt("id",0);
            //根据数据库信息推荐测试
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DBOperator dbOperator = new DBOperator(); //调用数据库
                    Object res = dbOperator.test_search_pretest(id);
                    Message msg = Message.obtain();
                    msg.obj = res;
                    handler.sendMessage(msg);
                }
            }).start();

            // 从文件中读取数据
            pref = getContext().getSharedPreferences("TestRec", Context.MODE_PRIVATE);
            PretestRes pretestRes = new PretestRes();
            pretestRes.setAbility(pref.getInt("ability",0));
            pretestRes.setEmotion(pref.getInt("emotion",0));
            pretestRes.setRelationship(pref.getInt("relationship",0));
            pretestRes.setPsychology(pref.getInt("psychology",0));
            pretestRes.setStudy(pref.getInt("study",0));
            // 推荐规则是少于4分不推荐，9或10分全部推荐，4~8分推荐部分
            // 能力
            if (pretestRes.getAbility() >= 9) {
                TestCard testCard = new TestCard(1,0, R.mipmap.test1_card);
                testCardsList.add(testCard);
                testCard = new TestCard(2,0, R.mipmap.test2_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getAbility() >=4) {
                TestCard testCard = new TestCard(1,0, R.mipmap.test1_card);
                testCardsList.add(testCard);
            }
            // 情绪
            if (pretestRes.getEmotion() >= 9) {
                TestCard testCard = new TestCard(3,0, R.mipmap.test3_card);
                testCardsList.add(testCard);
                testCard = new TestCard(4,0, R.mipmap.test4_card);
                testCardsList.add(testCard);
                testCard = new TestCard(5,0, R.mipmap.test5_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getEmotion() >=7) {
                TestCard testCard = new TestCard(3,0, R.mipmap.test3_card);
                testCardsList.add(testCard);
                testCard = new TestCard(5,0, R.mipmap.test5_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getEmotion() >=4) {
                TestCard testCard = new TestCard(3,0, R.mipmap.test3_card);
                testCardsList.add(testCard);
            }
            // 人际
            if (pretestRes.getRelationship() >= 9) {
                TestCard testCard = new TestCard(6,0, R.mipmap.test6_card);
                testCardsList.add(testCard);
                testCard = new TestCard(7,0, R.mipmap.test7_card);
                testCardsList.add(testCard);
                testCard = new TestCard(8,0, R.mipmap.test8_card);
                testCardsList.add(testCard);
                testCard = new TestCard(9,0, R.mipmap.test9_card);
                testCardsList.add(testCard);
                testCard = new TestCard(10,0, R.mipmap.test10_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getRelationship() >=7) {
                TestCard testCard = new TestCard(7,0, R.mipmap.test7_card);
                testCardsList.add(testCard);
                testCard = new TestCard(8,0, R.mipmap.test8_card);
                testCardsList.add(testCard);
                testCard = new TestCard(9,0, R.mipmap.test9_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getRelationship() >=4) {
                TestCard testCard = new TestCard(7,0, R.mipmap.test7_card);
                testCardsList.add(testCard);
            }
            // 心理
            if (pretestRes.getPsychology() >= 9) {
                TestCard testCard = new TestCard(11,0, R.mipmap.test11_card);
                testCardsList.add(testCard);
                testCard = new TestCard(12,0, R.mipmap.test12_card);
                testCardsList.add(testCard);
                testCard = new TestCard(13,0, R.mipmap.test13_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getPsychology() >= 7) {
                TestCard testCard = new TestCard(11,0, R.mipmap.test11_card);
                testCardsList.add(testCard);
                testCard = new TestCard(12,0, R.mipmap.test12_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getPsychology() >= 4) {
                TestCard testCard = new TestCard(12,0, R.mipmap.test12_card);
                testCardsList.add(testCard);
            }
            // 学习
            if (pretestRes.getStudy() >= 9) {
                TestCard testCard = new TestCard(14,0, R.mipmap.test14_card);
                testCardsList.add(testCard);
                testCard = new TestCard(15,0, R.mipmap.test15_card);
                testCardsList.add(testCard);
            } else if(pretestRes.getStudy() >= 4) {
                TestCard testCard = new TestCard(15,0, R.mipmap.test15_card);
                testCardsList.add(testCard);
            }
        } else if(title.equals("能力")) {
            TestCard testCard = new TestCard(1,1, R.mipmap.test1_card);
            testCardsList.add(testCard);
            testCard = new TestCard(2,1, R.mipmap.test2_card);
            testCardsList.add(testCard);
        } else if(title.equals("情绪")) {
            TestCard testCard = new TestCard(3,2, R.mipmap.test3_card);
            testCardsList.add(testCard);
            testCard = new TestCard(4,2, R.mipmap.test4_card);
            testCardsList.add(testCard);
            testCard = new TestCard(5,2, R.mipmap.test5_card);
            testCardsList.add(testCard);
        } else if(title.equals("人际")) {
            TestCard testCard = new TestCard(6,3, R.mipmap.test6_card);
            testCardsList.add(testCard);
            testCard = new TestCard(7,3, R.mipmap.test7_card);
            testCardsList.add(testCard);
            testCard = new TestCard(8,3, R.mipmap.test8_card);
            testCardsList.add(testCard);
            testCard = new TestCard(9,3, R.mipmap.test9_card);
            testCardsList.add(testCard);
            testCard = new TestCard(10,3, R.mipmap.test10_card);
            testCardsList.add(testCard);
        } else if(title.equals("心理")) {
            TestCard testCard = new TestCard(11,4, R.mipmap.test11_card);
            testCardsList.add(testCard);
            testCard = new TestCard(12,4, R.mipmap.test12_card);
            testCardsList.add(testCard);
            testCard = new TestCard(13,4, R.mipmap.test13_card);
            testCardsList.add(testCard);
        } else if(title.equals("学习")) {
            TestCard testCard = new TestCard(14,5, R.mipmap.test14_card);
            testCardsList.add(testCard);
            testCard = new TestCard(15,5, R.mipmap.test15_card);
            testCardsList.add(testCard);
        }
    }
}