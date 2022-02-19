package com.example.mentalflow.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Unbinder unbinder;


    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView=inflater.inflate(initLayout(),container,false);
            initView();
        }
//        unbinder= ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        initData();
    }

    @Override
    public void  onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    protected abstract int initLayout();//初始化布局
    protected abstract void initView();//初始化控件
    protected abstract void initData();//初始化数据
    public void navigateTo(Class cls) {
        Intent in = new Intent(getActivity(), cls);
        startActivity(in);
    }
    public void navigateToWithValue(Class cls,String name,int id) {
        Intent in = new Intent(getActivity(), cls);
        in.putExtra(name,id);
        startActivity(in);
    }
    public void buttonNavigateTo(Button btn, Class cls) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(cls);
            }
        });
    }
}