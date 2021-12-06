package com.example.mentalflow.Activity.Activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mentalflow.Activity.Adapter.MyPagerAdapter;
import com.example.mentalflow.Activity.Entity.TabEntity;
import com.example.mentalflow.Activity.Fragment.Guide.GuideFragment1;
import com.example.mentalflow.Activity.Fragment.Guide.GuideFragment2;
import com.example.mentalflow.Activity.Fragment.Guide.GuideFragment3;
import com.example.mentalflow.Activity.Fragment.Guide.GuideFragment4;
import com.example.mentalflow.Activity.Fragment.Guide.GuideFragment5;
import com.example.mentalflow.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

/*GuideActivity实现Tab联动viewpager(后面简称vp)跳转
*并绑定5个Fragment
*问题：
*1.Tab被Fragment掩盖只能显示出一个灰色圆点（fragment无装饰时显示正常）（这个不改都无伤大雅）
*2.在HomeActivity绑定5个Fragment后跳转正常，但在HomeFragment继续绑定其他界面时，跳转异常，点击button循环回启动页（HomeActivity也有写该问题）
*/
public class GuideActivity extends BaseActivity {
    private ArrayList<Fragment> guideFragments = new ArrayList<>();
    private ViewPager guideViewPager;
    private CommonTabLayout guideCommonTabLayout;
    private ArrayList<CustomTabEntity> guideTabEntity = new ArrayList<>();
    //未选中图标
    private int mIconUnselectIds = R.mipmap.white_circle;
    //选中图标
    private int mIconSelectIds = R.mipmap.gray_circle;

    @Override
    protected int initLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        guideViewPager = findViewById(R.id.guide_vp);
        guideCommonTabLayout = findViewById(R.id.guide_ctl);
    }

    @Override
    protected void initData() {
//        绑定5个界面
        guideFragments.add(GuideFragment1.newInstance());
        guideFragments.add(GuideFragment2.newInstance());
        guideFragments.add(GuideFragment3.newInstance());
        guideFragments.add(GuideFragment4.newInstance());
        guideFragments.add(GuideFragment5.newInstance());
        for (int i = 0; i < guideFragments.size(); i++) {
            guideTabEntity.add(new TabEntity(" ",mIconSelectIds, mIconUnselectIds));
        }
        guideCommonTabLayout.setTabData(guideTabEntity);
        guideCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                guideViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        guideViewPager.setOffscreenPageLimit(guideFragments.size());
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                guideCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        guideViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), guideFragments));

    }
}