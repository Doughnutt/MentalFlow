package com.example.mentalflow.Activity.Fragment.MainFragment;

import androidx.fragment.app.Fragment;

import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoFragment extends BaseFragment {

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

    }

    @Override
    protected void initData() {

    }
}