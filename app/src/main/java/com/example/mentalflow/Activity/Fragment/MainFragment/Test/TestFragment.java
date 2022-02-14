package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class TestFragment extends BaseFragment {
    private ViewPager testViewPager;
    private SlidingTabLayout slidingTabLayout; //滑动布局
    //标签名称组
    private String[] mTitles = {"推荐", "能力","情绪","人际","心理","学习"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private int now_category = 0;

    public static TestFragment newInstance(int now) { //要传入当前需要展示的分类
        TestFragment fragment = new TestFragment();
        fragment.now_category = now;
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        testViewPager = mRootView.findViewById(R.id.test_vp);
        slidingTabLayout = mRootView.findViewById(R.id.test_tl);
    }

    @Override
    protected void initData() {
       getTestCategoryList();
    }

    private void getTestCategoryList() {
        for(String title:mTitles){
            mFragments.add(TestAbilityFragment.newInstance(title)); //对于每一个类别建立一个新的 fragment
            mAdapter = new MyPagerAdapter(getFragmentManager(),mTitles,mFragments);
            testViewPager.setAdapter(mAdapter);
            slidingTabLayout.setViewPager(testViewPager);
            slidingTabLayout.setCurrentTab(this.now_category); //设置当前处于哪一个分类
        }

    }
}