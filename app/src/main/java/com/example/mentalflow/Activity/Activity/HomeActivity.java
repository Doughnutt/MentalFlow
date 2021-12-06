package com.example.mentalflow.Activity.Activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Entity.TabEntity;
import com.example.mentalflow.Activity.Fragment.Home.HardwareFragment;
import com.example.mentalflow.Activity.Fragment.Home.HomeFragment;
import com.example.mentalflow.Activity.Fragment.Home.InquiryFragment;
import com.example.mentalflow.Activity.Fragment.Home.MyInfoFragment;
import com.example.mentalflow.Activity.Fragment.Home.Test.TestFragment;
import com.example.mentalflow.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
/*HomeActivity实现Tab联动vp跳转
*绑定5个Fragment
*跳转至五个界面都正常
* 问题：在HomeFragment添加跳转这一步之后出现问题，开始循环跳转至开始界面（我也不知道为什么我找了半天的Bug找不到
* 都详细写注释了也没找到bug我好恨啊
 */
public class HomeActivity extends BaseActivity {
    //导航栏名称组
    private String[] homeTitles = {"主页", "心理测试", "硬件设备", "在线问诊","我的信息"};

    //未选中图标组
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect,R.mipmap.test_unselect,
            R.mipmap.medicalinfo_unselect, R.mipmap.consultation_unselect,R.mipmap.myinfo_unselect};

    //选中图标组
    private int[] mIconSelectIds ={
            R.mipmap.home_select, R.mipmap.test_select,
            R.mipmap.medicalinfo_select, R.mipmap.consultation_select,R.mipmap.myinfo_select};

    private ArrayList<Fragment> homeFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> homeTabEntities = new ArrayList<>();
    private ViewPager homeActivityViewPager;
    private CommonTabLayout homeActivityCommonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        homeActivityViewPager=findViewById(R.id.home_activity_vp);
        homeActivityCommonTabLayout=findViewById(R.id.home_activity_ctl);
    }

    @Override
    protected void initData() {
        homeFragments.add(HomeFragment.newInstance());
        homeFragments.add(TestFragment.newInstance());
        homeFragments.add(HardwareFragment.newInstance());
        homeFragments.add(InquiryFragment.newInstance());
        homeFragments.add(MyInfoFragment.newInstance());

        for (int i = 0; i < homeTitles.length; i++) {
            homeTabEntities.add(new TabEntity(homeTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        homeActivityCommonTabLayout.setTabData(homeTabEntities);
        homeActivityCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position)
            {
                homeActivityViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        homeActivityViewPager.setOffscreenPageLimit(homeFragments.size());
        homeActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                homeActivityCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeActivityViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),homeTitles,homeFragments));
    }
}