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

import com.example.mentalflow.R;

import java.util.ArrayList;
import java.util.List;


public class HomeArticleFragment extends Fragment {
    private String title;
    private RecyclerView recyclerView;
    private List<ArticleCard> articleCardList = new ArrayList<>();
    private ArticleAdapter articleAdapter;
    private ArrayList<Integer> idList=new ArrayList<>();//保存文章id
    private static final int GET_TEXT=1;
    private static final int INIT_TEXT=0;
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
    private Handler handler = new Handler(Looper.myLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GET_TEXT:
                    System.out.println("已得到文章内容");
                    break;
                case INIT_TEXT:
                    System.out.println("未得到文章内容，初始化");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home_article, container, false);
        recyclerView=v.findViewById(R.id.home_article_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //只有第一次需要添加测试内容
       if(articleCardList.size()==0) initArticleView();
        articleAdapter =new ArticleAdapter(getActivity(),articleCardList);
        recyclerView.setAdapter(articleAdapter);
        return v;
    }

    private void initArticleView() {
       //根据标题添加对应类目的文章
        if(title.equals("心理")) setArticleCard(1);
        else if (title.equals("科普")) setArticleCard(2);
        else if (title.equals("婚恋")) setArticleCard(3);
        else if (title.equals("家庭")) setArticleCard(4);
        else if (title.equals("教育")) setArticleCard(5);
        else Recommend();

    }
//    设置推荐分类下的文章
    private void Recommend(){
        for (int i = 0; i < 8; i++) {
            ArticleCard ac=new ArticleCard();
            ac.setTitle("今日文章"+i);
            ac.setImageId(R.mipmap.icon_colored);
            ac.setLabel("青少年");
            ac.setContent("XXXXXXXXXXXX");
            ac.setId(0);
//                        ArticleCard articleCard1 = new ArticleCard("今日文章"+i, R.mipmap.icon_colored, "青少年", "XXXX");
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
}