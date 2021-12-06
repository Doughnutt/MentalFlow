package com.example.mentalflow.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mentalflow.Activity.Adapter.ArticleRecyclerAdapter;
import com.example.mentalflow.Activity.Entity.ArticleCard;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends BaseActivity {
    //private ImageButton imageButton;
    //实体类
    private List<ArticleCard> articleCardList = new ArrayList<>();
    //定义view用来设置fragment的layout
    private View view;
    //定义RecyclerView
    private RecyclerView recyclerView;
    //定义RecyclerView适配器
    private ArticleRecyclerAdapter articleRecyclerAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {
        initArticles();
        recyclerView =findViewById(R.id.article_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //设置item分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        articleRecyclerAdapter =new ArticleRecyclerAdapter(this,articleCardList);
        recyclerView.setAdapter(articleRecyclerAdapter);
    }

    private void initArticles() {
        ArticleCard articleCard1 = new ArticleCard("今日文章", R.mipmap.icon_colored, "青少年", "XXXX");
        articleCardList.add(articleCard1);
        ArticleCard articleCard2 = new ArticleCard("文章展示", R.mipmap.icon_colored, "情感", "XXXXX");
        articleCardList.add(articleCard2);
    }
}