package com.example.mentalflow.Activity.Fragment.MainFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
    }
}