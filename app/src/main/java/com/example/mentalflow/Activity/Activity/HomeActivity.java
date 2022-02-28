package com.example.mentalflow.Activity.Activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Entity.TabEntity;
import com.example.mentalflow.Activity.Fragment.MainFragment.HardwareFragment;
import com.example.mentalflow.Activity.Fragment.MainFragment.Home.HomeFragment;
import com.example.mentalflow.Activity.Fragment.MainFragment.InquiryFragment;
import com.example.mentalflow.Activity.Fragment.MainFragment.MyInfoFragment;
import com.example.mentalflow.Activity.Fragment.MainFragment.Test.TestFragment;
import com.example.mentalflow.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

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

    private ArrayList<Fragment> fragments = new ArrayList<>();
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
        Intent intent = getIntent();
        int home_category = intent.getIntExtra("home_category",0); //获取上个活动传入的类别值
        int test_category = intent.getIntExtra("test_category",0); //获取上个活动传入的类别值
        fragments.add(HomeFragment.newInstance(home_category));
        fragments.add(TestFragment.newInstance(test_category));
        fragments.add(HardwareFragment.newInstance());
        fragments.add(InquiryFragment.newInstance());
        fragments.add(MyInfoFragment.newInstance());

        for (int i = 0; i < homeTitles.length; i++) {
            //标题，选中的icon，未选中的icon
            homeTabEntities.add(new TabEntity(homeTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        // 通过传值记录跳转回哪个页面，下面的执行顺序不能换
        int select_page = intent.getIntExtra("select_page",0);

        homeActivityViewPager.setOffscreenPageLimit(fragments.size());

        homeActivityCommonTabLayout.setTabData(homeTabEntities);

        homeActivityViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),homeTitles, fragments));

        homeActivityCommonTabLayout.setCurrentTab(select_page); //设置导航栏
        homeActivityViewPager.setCurrentItem(select_page); //设置fragment

        homeActivityCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
//            绑定图标和viewpager
            @Override
            public void onTabSelect(int position)
            {
//                System.out.println(position);
                homeActivityViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        homeActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                System.out.println(position);
                homeActivityCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private long firstTime;// 记录点击返回时第一次的时间毫秒值
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){// 点击了返回按键
            exitApp(2000);// 退出应用
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出应用
     * @param timeInterval 设置第二次点击退出的时间间隔
     */
    private void exitApp(long timeInterval) {
        if(System.currentTimeMillis() - firstTime >= timeInterval){
            Toast.makeText(mContext,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            firstTime = System.currentTimeMillis();
        }else {
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }
}