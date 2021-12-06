package com.example.mentalflow.Activity.Fragment.Guide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuideFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideFragment1 extends Fragment {


    public GuideFragment1() {
        // Required empty public constructor
    }

    public static GuideFragment1 newInstance() {
        GuideFragment1 fragment = new GuideFragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide1, container, false);
    }
}