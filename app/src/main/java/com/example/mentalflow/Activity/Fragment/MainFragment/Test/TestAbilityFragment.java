package com.example.mentalflow.Activity.Fragment.MainFragment.Test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalflow.Activity.Adapter.TestRecyclerAdapter;
import com.example.mentalflow.Activity.Entity.TestCard;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestAbilityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestAbilityFragment extends Fragment {
    private String title;
    private RecyclerView recyclerView;
    private List<TestCard> testCardsList = new ArrayList<>();
    private TestRecyclerAdapter testRecyclerAdapter;

    public TestAbilityFragment() {
        // Required empty public constructor
    }


    public static TestAbilityFragment newInstance(String title) {
        TestAbilityFragment fragment = new TestAbilityFragment();
        fragment.title=title;
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
        View v=inflater.inflate(R.layout.fragment_test_ability, container, false);
        recyclerView=v.findViewById(R.id.test_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        initTestView();
        testRecyclerAdapter =new TestRecyclerAdapter(getActivity(),testCardsList);
        recyclerView.setAdapter(testRecyclerAdapter);
        return v;
    }

    private void initTestView() {
        TestCard psyEndurance=new TestCard("心理承受能力测试",R.mipmap.test_1);
        testCardsList.add(psyEndurance);
        TestCard creationTest=new TestCard("威廉斯创造力倾向量表",R.mipmap.test_2);
        testCardsList.add(creationTest);
    }
}