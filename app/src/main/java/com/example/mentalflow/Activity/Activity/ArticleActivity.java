package com.example.mentalflow.Activity.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.ArticleCard;
import com.example.mentalflow.R;
import com.google.android.material.appbar.MaterialToolbar;

public class ArticleActivity extends BaseActivity {
    private MaterialToolbar toolbar;
    private TextView tv_title,tv_label,tv_date,tv_content,tv_ref,tv_tb;
    private int id;
    private String content,title,label,date,ref;
    private static final int GET_TEXT=1;

    //定义Webview
//    private WebView webView;

    @Override
    protected int initLayout() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("article_id",-1);
        System.out.println("当前文章id为"+id);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tv_content=findViewById(R.id.article_content);
        tv_date=findViewById(R.id.article_date);
        tv_label=findViewById(R.id.article_label);
        tv_title=findViewById(R.id.article_title);
        tv_ref=findViewById(R.id.article_ref);
        tv_tb=findViewById(R.id.type_tb);
        toolbar=findViewById(R.id.article_toolbar);
    }

    @Override
    protected void initData() {
        System.out.println("当前文章id为"+id);
      DBOperator dbOperator = new DBOperator(); //调用数据库
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("调用线程为AA传递文章，调用数据库");
                    ArticleCard articleCard = dbOperator.GetArticleByID(id);
                    System.out.println("数据库调用完毕");
                    content = articleCard.getContent();
                    date = "发布于" + articleCard.getDate();
                    label = articleCard.getLabel();
                    title = articleCard.getTitle();
                    ref = "出处：" + articleCard.getRef();
                    Message msg = new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                }
            }).start();
        }
//        必须是myLooper，获得当前进程，getMainLooper获得主线程不对
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                    tv_content.setText(content);
                    tv_date.setText(date);
                    tv_label.setText(label);
                    tv_title.setText(title);
                    tv_ref.setText(ref);
                    tv_tb.setText(label);
            }
        }
    };
}
