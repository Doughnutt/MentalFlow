package com.example.mentalflow.Activity.Fragment.MainFragment.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import com.example.mentalflow.Activity.Adapter.ArticleAdapter;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.ArticleCard;

import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class HomeArticleFragment extends BaseFragment {
    private int title;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private List<ArticleCard> articleCardList = new ArrayList<>();
    private static final int GET_TEXT=1;
    private static final int INIT_TEXT=0;
    private int pages=0;
    private boolean endLoading=true;
    private ArticleAdapter articleAdapter;

    public static HomeArticleFragment newInstance(int title) {
        HomeArticleFragment fragment = new HomeArticleFragment();
        fragment.title=title;
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home_article;
    }

    @Override
    protected void initView() {
        recyclerView=mRootView.findViewById(R.id.home_article_rv);
        refreshLayout=mRootView.findViewById(R.id.home_article_refresh);
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        首次自动刷新
        refreshLayout.autoRefresh();
//      只有第一次需要添加测试内容
        if (articleCardList.size() == 0) {
            setArticleCard(title,0,0);
            articleAdapter= new ArticleAdapter(getActivity(), articleCardList);
        }
        recyclerView.setAdapter(articleAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() { //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                setArticleCard(title,0,0);
                articleAdapter = new ArticleAdapter(getActivity(), articleCardList);
                recyclerView.setAdapter(articleAdapter);
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { //上拉加载更多
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
//                if(!endLoading){
//                    pages++;
//                    setArticleCard(title,pages,1);
//                    articleAdapter = new ArticleAdapter(getActivity(), articleCardList);
//                    recyclerView.setAdapter(articleAdapter);
//                }
//                else  {
                    refreshLayout.finishLoadMoreWithNoMoreData();
//                    refreshlayout.finishLoadMore(false);//传入false表示加载失败
//                }

            }
        });

    }
//    设置推荐分类下的文章
    private void Recommend(){

    }
//    设置除推荐外各分类下的文章
    private void setArticleCard(int type,int pages,int flag){
        DBOperator dbOperator = new DBOperator(); //调用数据库
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ArticleCard> list;
                if (type==0)  list=dbOperator.test();
                else list=dbOperator.GetArticleByType(type);
                System.out.println("LIST.SIZE()"+list.size());
                if(!list.isEmpty()||list.size()!=0) {
//                   已有数据<4，结束下次刷新
                    if(list.size()!=4) endLoading=true;
//                    加载,添加数据；刷新，覆盖数据；
                    if (flag==1) articleCardList.addAll(list);
                    else articleCardList=list;
                    Message msg = new Message();
                    msg.what = GET_TEXT;
                    handler.sendMessage(msg);
                }
                else{
                    Message msg = new Message();
                    msg.what = INIT_TEXT;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    private Handler handler = new Handler(Looper.myLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GET_TEXT:
                    Log.d("HomeArticleFragment", "已得到文章内容");
                    break;
                case INIT_TEXT:
                    Log.d("HomeArticleFragment", "未得到文章内容");
                    break;
                default:
                    break;
            }
        }
    };
}