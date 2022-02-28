package com.example.mentalflow.Activity.Fragment.MainFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.Activity.Activity.MyInfoModActivity;
import com.example.mentalflow.Activity.Activity.MyInfoSettingActivity;
import com.example.mentalflow.Activity.Activity.TestActivity;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoFragment extends BaseFragment {

    private TextView mName;
    private ImageView mPhoto;
    private Button mButton1; //修改个人信息
    private Button mButton2; //收藏夹
    private Button mButton3; //消息中心
    private Button mButton4; //设置

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance() {
        MyInfoFragment fragment = new MyInfoFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my_info;
    }

    @Override
    protected void initView() {
        mName = mRootView.findViewById(R.id.my_info_name);
        mPhoto = mRootView.findViewById(R.id.my_info_photo);
        mButton1 = mRootView.findViewById(R.id.myinfo_btn1);
        mButton2 = mRootView.findViewById(R.id.myinfo_btn2);
        mButton3 = mRootView.findViewById(R.id.myinfo_btn3);
        mButton4 = mRootView.findViewById(R.id.myinfo_btn4);
    }

    @Override
    protected void initData() {
        // 读取文件: UserInfo
        SharedPreferences pref = getContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String name = pref.getString("name","");
        String gender = pref.getString("gender","");
        mName.setText(name);
        if(gender.equals("男")) {
            mPhoto.setImageResource(R.mipmap.my_info_male);
        } else {
            mPhoto.setImageResource(R.mipmap.my_info_female);
        }

        //修改个人信息
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInfoModActivity.class);
                startActivity(intent);
            }
        });

        //设置
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyInfoSettingActivity.class);
                startActivity(intent);
            }
        });
    }
}