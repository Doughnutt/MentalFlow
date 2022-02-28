package com.example.mentalflow.Activity.Fragment.MainFragment.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mentalflow.Activity.Adapter.ArticleAdapter;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.ArticleCard;

import com.example.mentalflow.Activity.Fragment.BaseFragment;
import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;


public class HomeArticleFragment extends BaseFragment {
    private String title;
    private RecyclerView recyclerView;
    private List<ArticleCard> articleCardList = new ArrayList<>();
    private static final int GET_TEXT=1;
    private static final int INIT_TEXT=0;

    public static HomeArticleFragment newInstance(String title) {
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
    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //只有第一次需要添加测试内容
        if (articleCardList.size() == 0) initArticleView();
        ArticleAdapter articleAdapter = new ArticleAdapter(getActivity(), articleCardList);
        recyclerView.setAdapter(articleAdapter);
    }
    private void initArticleView() {
       //根据标题添加对应类目的文章
        System.out.println("目前标题为"+title);
        if(title.equals("心理")) setArticleCard(1);
        else if (title.equals("科普")) setArticleCard(2);
        else if (title.equals("婚恋")) setArticleCard(3);
        else if (title.equals("家庭")) setArticleCard(4);
        else if (title.equals("教育")) setArticleCard(5);
        else if (title.equals("人际")) setArticleCard(6);
        else if (title.equals("睡眠")) setArticleCard(7);
        else if (title.equals("性别")) setArticleCard(8);
        else if (title.equals("性格")) setArticleCard(9);
        else if (title.equals("职场")) setArticleCard(10);
        else Recommend();

    }
//    设置推荐分类下的文章
    private void Recommend(){
        for (int i = 0; i < 8; i++) {
             ArticleCard ac = new ArticleCard("今日文章"+i, R.mipmap.icon_colored, "青少年", "XXXX");
             articleCardList.add(ac);
        }
    }
//    设置除推荐外各分类下的文章
    private void setArticleCard(int type){
        DBOperator dbOperator = new DBOperator(); //调用数据库
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ArticleCard> list=dbOperator.GetArticleByType(type);
                if(!list.isEmpty()||list.size()!=0){
                for (int i = 0; i < list.size() ;i++) {
//                   ArticleCard articleCard1 = new ArticleCard("今日文章"+i, R.mipmap.icon_colored, "青少年", "XXXX");
//                   articleCardList.add(articleCard1);
                    articleCardList.add(list.get(i));
                }
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
                    System.out.println("已得到文章内容");
//                  刷新页面以展示绑定数据
//                    mRootView.invalidate();
                    break;
                case INIT_TEXT:
                    System.out.println("未得到文章内容，error");
                    break;
                default:
                    break;
            }
        }
    };
}