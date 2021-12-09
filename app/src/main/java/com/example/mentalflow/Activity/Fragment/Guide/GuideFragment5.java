package com.example.mentalflow.Activity.Fragment.Guide;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;

import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.Activity.Activity.Initial.LoginActivity;
import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuideFragment5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideFragment5 extends BaseFragment {
private Button mGuideButton;

    public GuideFragment5() {
        // Required empty public constructor
    }

    public static GuideFragment5 newInstance() {
        GuideFragment5 fragment = new GuideFragment5();
        return fragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_guide5;
    }

    @Override
    protected void initView() {
        mGuideButton=mRootView.findViewById(R.id.gf5_btn);
    }

    @Override
    protected void initData() {
        mGuideButton.setOnClickListener(v -> navigateTo(LoginActivity.class));
//        getActivity().finish();
    }
}