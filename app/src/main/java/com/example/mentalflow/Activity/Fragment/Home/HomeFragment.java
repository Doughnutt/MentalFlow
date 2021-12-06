package com.example.mentalflow.Activity.Fragment.Home;

import android.widget.ImageButton;

import com.example.mentalflow.Activity.Activity.ArticleActivity;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;


public class HomeFragment extends BaseFragment {

    private ImageButton homeImageButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        homeImageButton=mRootView.findViewById(R.id.home_ib);
    }

//    @Override
    protected void initData() {
      homeImageButton.setOnClickListener(v -> navigateTo(ArticleActivity.class));
    }
}