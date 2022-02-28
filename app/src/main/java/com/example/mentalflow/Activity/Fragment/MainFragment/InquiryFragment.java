package com.example.mentalflow.Activity.Fragment.MainFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalflow.Activity.Activity.HospitalActivity;
import com.example.mentalflow.Activity.Adapter.DoctorRecyclerAdapter;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.ArticleCard;
import com.example.mentalflow.Activity.Entity.DoctorCard;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InquiryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InquiryFragment extends BaseFragment {

    private ImageButton imageInquiryButton;
    //实体类
    private List<DoctorCard> doctorCardList = new ArrayList<>();
    //定义RecyclerView
    private RecyclerView recyclerView;
    //定义RecyclerView适配器
    private DoctorRecyclerAdapter doctorRecyclerAdapter;
    private static final int GET_TEXT=1;
    private static final int INIT_TEXT=0;
//    统计卡片数量
    public InquiryFragment() {
        // Required empty public constructor
    }


    public static InquiryFragment newInstance() {
        InquiryFragment fragment = new InquiryFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_inquiry;
    }

    @Override
    protected void initView() {
        recyclerView = mRootView.findViewById(R.id.inquiry_rv);
        imageInquiryButton=mRootView.findViewById(R.id.inquiry_btn);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //只有第一次需要添加测试内容
        if (doctorCardList.size() == 0) initDocCard();
        doctorRecyclerAdapter =new DoctorRecyclerAdapter(getActivity(),doctorCardList);
        recyclerView.setAdapter(doctorRecyclerAdapter);
        imageInquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), HospitalActivity.class);
                startActivity(in);
            }
        });
    }

    private void initDocCard(){
        DBOperator dbOperator = new DBOperator(); //调用数据库
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<DoctorCard> list=dbOperator.getDoctorIntrodCard();
                if(!list.isEmpty()||list.size()!=0){
                    for (int i = 0; i < list.size() ;i++) {
                        doctorCardList.add(list.get(i));
                    }
                    Message msg = new Message();
                    msg.what = GET_TEXT;
                    handler.sendMessage(msg);
                }
                else{
                    Message msg = new Message();
                    msg.what = INIT_TEXT;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    private Handler handler = new Handler(Looper.myLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GET_TEXT:
                    System.out.println("已得到的相关医生信息内容");
//                  刷新页面以展示绑定数据
//                    mRootView.invalidate();
                    break;
                case INIT_TEXT:
                    System.out.println("未得相关医生信息内容，error");
                    break;
                default:
                    break;
            }
        }
    };
}