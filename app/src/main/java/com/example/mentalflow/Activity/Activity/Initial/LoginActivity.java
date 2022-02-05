package com.example.mentalflow.Activity.Activity.Initial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.R;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity {
    private EditText mPhoneNumber;
    private Button mLoginButton;
    private final Context context = LoginActivity.this;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginButton=findViewById(R.id.login_next);
        mPhoneNumber=findViewById(R.id.login_et);

        Handler mainHandler = new Handler(getMainLooper()); //获取主线程
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.what==0) { //what相当于一个映射
                Intent intent = new Intent(LoginActivity.this,RegPasswordActivity.class);
                String phone=mPhoneNumber.getText().toString().trim();
                intent.putExtra("phone",phone); //传入账号
                Toast.makeText(context, "该手机号已注册账号，请登录", Toast.LENGTH_SHORT).show();
                intent.putExtra("state",0); //0表示是登录
                startActivity(intent);
                overridePendingTransition(0,0);
            } else {
                Intent intent = new Intent(LoginActivity.this,RegPasswordActivity.class);
                String phone=mPhoneNumber.getText().toString().trim();
                intent.putExtra("phone",phone); //传入账号
                Toast.makeText(context, "该手机号未注册账号，请填写信息进行注册", Toast.LENGTH_LONG).show();
                intent.putExtra("state", 1); //1表示是注册
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        }
    };

    @Override
    protected void initData() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPhoneNumber == null||mPhoneNumber.length()==0) { //输入为空
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else { //正则表达式匹配
                    String phone=mPhoneNumber.getText().toString().trim();
                    if(!match(phone)) { //匹配不正确
                        Toast.makeText(context,"请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    } else { //匹配正确
                        Intent intent = new Intent(LoginActivity.this,RegPasswordActivity.class);
                        intent.putExtra("phone",phone); //传入账号

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DBOperator dbOperator = new DBOperator(); //调用数据库
                                boolean state = dbOperator.login_search_phone(phone);
                                Message msg = Message.obtain();
                                if(state) {
                                    msg.what = 0;
                                    msg.obj = true;
                                } else {
                                    msg.what = 1;
                                    msg.obj = false;
                                }
                                handler.sendMessage(msg);
                            }
                        }).start();
                    }
                }

            }
        });
    }
    // 正则表达式：不符合返回false，否则返回true
    private boolean match(String phone) {
        String regEx = "[1][3-9][0-9]{9}|[0]"; //正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}