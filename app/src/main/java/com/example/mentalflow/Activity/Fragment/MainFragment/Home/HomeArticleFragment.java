package com.example.mentalflow.Activity.Fragment.MainFragment.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalflow.Activity.Adapter.ArticleRecyclerAdapter;
import com.example.mentalflow.Activity.Entity.ArticleCard;

import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;


public class HomeArticleFragment extends Fragment {
    private String title;
    private RecyclerView recyclerView;
    private List<ArticleCard> articleCardList = new ArrayList<>();
    private ArticleRecyclerAdapter articleRecyclerAdapter;

    public HomeArticleFragment(){
        // Required empty public constructor
    }
    public static HomeArticleFragment newInstance(String title) {
        HomeArticleFragment fragment = new HomeArticleFragment();
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
        View v=inflater.inflate(R.layout.fragment_home_article, container, false);
        recyclerView=v.findViewById(R.id.home_article_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        initTestView();
        articleRecyclerAdapter =new ArticleRecyclerAdapter(getActivity(),articleCardList);
        recyclerView.setAdapter(articleRecyclerAdapter);
        return v;
    }

    private void initTestView() {
        ArticleCard articleCard1 = new ArticleCard("今日文章", R.mipmap.icon_colored, "青少年", "XXXX");
        articleCardList.add(articleCard1);
        ArticleCard articleCard2 = new ArticleCard("文章展示", R.mipmap.icon_colored, "情感", "XXXXX");
        articleCardList.add(articleCard2);
        ArticleCard articleCard3 = new ArticleCard("今日文章", R.mipmap.icon_colored, "青少年", "XXXX");
        articleCardList.add(articleCard3);
        ArticleCard articleCard4 = new ArticleCard("文章展示", R.mipmap.icon_colored, "情感", "XXXXX");
        articleCardList.add(articleCard4);
    }
}