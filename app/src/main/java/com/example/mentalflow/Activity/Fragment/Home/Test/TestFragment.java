package com.example.mentalflow.Activity.Fragment.Home.Test;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends BaseFragment {
    private ViewPager testViewPager;
    private SlidingTabLayout slidingTabLayout;
    //标签名称组
    private String[] mTitles = {"推荐", "能力"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    public TestFragment() {
        // Required empty public constructor
    }


    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
       testViewPager=mRootView.findViewById(R.id.test_vp);
        slidingTabLayout=mRootView.findViewById(R.id.test_tl);
    }

    @Override
    protected void initData() {
       getTestCategoryList();
    }

    private void getTestCategoryList() {
        for(String title:mTitles){
            mFragments.add(TestAbilityFragment.newInstance(title));
            mAdapter=new MyPagerAdapter(getFragmentManager(),mTitles,mFragments);
            testViewPager.setAdapter(mAdapter);
            slidingTabLayout.setViewPager(testViewPager);
        }

        }
}