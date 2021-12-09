package com.example.mentalflow.Activity.Fragment.MainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalflow.Activity.Activity.HospitalActivity;
import com.example.mentalflow.Activity.Adapter.DoctorRecyclerAdapter;
import com.example.mentalflow.Activity.Entity.DoctorCard;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InquiryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InquiryFragment extends Fragment {

    private ImageButton imageInquiryButton;
    //实体类
    private List<DoctorCard> doctorCardList = new ArrayList<>();
    //定义view用来设置fragment的layout
    private View view;
    //定义RecyclerView
    private RecyclerView recyclerView;
    //定义RecyclerView适配器
    private DoctorRecyclerAdapter doctorRecyclerAdapter;
    public InquiryFragment() {
        // Required empty public constructor
    }


    public static InquiryFragment newInstance() {
        InquiryFragment fragment = new InquiryFragment();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_inquiry,container,false);
        initRecyclerView();
        initDocCard();
        //点击事件
        imageInquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), HospitalActivity.class);
                startActivity(in);
            }
        });

        return view;
    }



    protected void initRecyclerView() {

        recyclerView = view.findViewById(R.id.inquiry_rv);
        imageInquiryButton=view.findViewById(R.id.inquiry_btn);
        doctorRecyclerAdapter =new DoctorRecyclerAdapter(getActivity(),doctorCardList);
        recyclerView.setAdapter(doctorRecyclerAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        }


    private void initDocCard(){
        DoctorCard doctorCard1=new DoctorCard("张三",R.mipmap.doc_round_f,"青少年","xxxxxxxxxxxxxxxxxxxxxx");
        doctorCardList.add(doctorCard1);
        DoctorCard doctorCard2=new DoctorCard("李四",R.mipmap.doc_round_m,"青少年","xxxxxxxxxxxxxxxxxxxxxxxxx");
        doctorCardList.add(doctorCard2);
        DoctorCard doctorCard3=new DoctorCard("王五",R.mipmap.doc_round_f,"青少年","xxxxxxxxxxxxxxxxxxxxxxxxx");
        doctorCardList.add(doctorCard3);
    }
}