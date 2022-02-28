package com.example.mentalflow.Activity.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.DoctorCard;
import com.example.mentalflow.R;

public class DoctorInfoActivity extends BaseActivity {
    private TextView tv_name,tv_exp,tv_bg,tv_type,tv_info;
    private ImageView iv_portrait;
    private int doc_id,portrait;
    private String name,exp,background,type,info;

    @Override
    protected int initLayout() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        doc_id = intent.getIntExtra("doc_id",-1);
        tv_name=findViewById(R.id.doc_name);
        tv_exp=findViewById(R.id.doc_exp);
        tv_bg=findViewById(R.id.doc_bg);
        tv_type=findViewById(R.id.doc_type);
        tv_info=findViewById(R.id.doc_info);
        tv_exp=findViewById(R.id.doc_exp);
        iv_portrait=findViewById(R.id.doc_round_image);
    }

    @Override
    protected void initData() {
        DBOperator dbOperator = new DBOperator(); //调用数据库
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("该医生id为"+doc_id);
                DoctorCard doctorCard = dbOperator.getDoctorInfo(doc_id);
                if(doctorCard==null){
                    Message msg = new Message();
                    msg.what=0;
                    handler.sendMessage(msg);
                }
                else{
                    name = doctorCard.getDocName();
                    exp = "从业时间：" + doctorCard.getExp();
                    background = "专业背景"+doctorCard.getBackground();
                    type = "擅长领域"+doctorCard.getType();
                    info=doctorCard.getIntro();
                    portrait=doctorCard.getImageId();
                    Message msg = new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                }

            }
        }).start();
    }
    //        必须是myLooper，获得当前进程，getMainLooper获得主线程不对
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                iv_portrait.setImageResource(portrait);
                tv_name.setText(name);
                tv_type.setText(type);
                tv_exp.setText(exp);
                tv_bg.setText(background);
                tv_info.setText(info);
            }
            else{
                System.out.println("出错，查不到该医生信息");
            }
        }
    };
}