package com.example.mentalflow.Activity.Fragment.Guide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalflow.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuideFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuideFragment2 extends Fragment {


    public GuideFragment2() {
        // Required empty public constructor
    }

    public static GuideFragment2 newInstance() {
        GuideFragment2 fragment = new GuideFragment2();
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
        return inflater.inflate(R.layout.fragment_guide2, container, false);
    }
}