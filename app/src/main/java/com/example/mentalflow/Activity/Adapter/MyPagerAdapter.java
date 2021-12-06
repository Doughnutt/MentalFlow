package com.example.mentalflow.Activity.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.view.View;

import com.example.mentalflow.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*
*用于实现界面联动Tab跳转的适配器
* */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private ArrayList<Fragment> mFragments;
    private List<View> mViewList;


    public MyPagerAdapter(@NonNull @NotNull FragmentManager fm, String[] titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.mTitles=titles;
        this.mFragments=fragments;
    }

    public MyPagerAdapter(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}