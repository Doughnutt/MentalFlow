package com.example.mentalflow.Activity.Fragment.MainFragment;

import androidx.fragment.app.Fragment;

import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HardwareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HardwareFragment extends BaseFragment {

    public HardwareFragment() {
        // Required empty public constructor
    }


    public static HardwareFragment newInstance() {
        HardwareFragment fragment = new HardwareFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_hardware;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}