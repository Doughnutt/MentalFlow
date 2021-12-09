package com.example.mentalflow.Activity.Fragment.MainFragment.Home;

import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Activity.ArticleActivity;
import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment {

    private ImageButton homeImageButton;
    private ViewPager homeViewPager;
    private SlidingTabLayout slidingTabLayout;
    private AppBarLayout appBarLayout;

    //标签名称组
    private String[] mTitles = {"推荐", "心理","科普","婚恋","家庭","教育"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
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
        homeViewPager=mRootView.findViewById(R.id.home_vp);
        slidingTabLayout=mRootView.findViewById(R.id.home_tl);
        appBarLayout = mRootView.findViewById(R.id.home_app_bar_layout);
    }

//    @Override
    protected void initData() {
      homeImageButton.setOnClickListener(v -> navigateTo(ArticleActivity.class));
        getArticleCategoryList();
    }

    private void getArticleCategoryList() {
        for(String title:mTitles){
            mFragments.add(HomeArticleFragment.newInstance(title));
            mAdapter=new MyPagerAdapter(getFragmentManager(),mTitles,mFragments);
            homeViewPager.setAdapter(mAdapter);
            slidingTabLayout.setViewPager(homeViewPager);
        }
    }
}