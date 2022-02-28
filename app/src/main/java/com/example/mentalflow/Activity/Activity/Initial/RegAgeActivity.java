package com.example.mentalflow.Activity.Activity.Initial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mentalflow.Activity.Activity.BaseActivity;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.UserInfo;
import com.example.mentalflow.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegAgeActivity extends BaseActivity {

    private EditText mAge;
    private Button mNextButton;
    private ImageButton mBackButton;
    private final Context context  = RegAgeActivity.this;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_age; //跳转年龄页
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.reg_back);
        mNextButton=findViewById(R.id.reg_next);
        mAge=findViewById(R.id.reg_age);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.what == 0){ // 注册成功

                // 写入文件:UserInfo
                UserInfo userInfo = (UserInfo) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putInt("id",userInfo.getId());
                editor.putString("phone",userInfo.getPhone());
                editor.putString("password",userInfo.getPassword());
                editor.putString("name",userInfo.getName());
                editor.putString("gender", userInfo.getGender());
                editor.putInt("age", userInfo.getAge());
                editor.putString("intro",userInfo.getIntro());
                editor.apply();

                //跳转自测量表页
                navigateTo(Test0PreActivity.class);
            }
        }
    };

    @Override
    protected void initData() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(RegGenderActivity.class);
            }
        });

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String password = intent.getStringExtra("password");
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String age = mAge.getText().toString().trim();
                if(mAge == null || mAge.length() == 0) {
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else if(!match(age)){
                    Toast.makeText(context, "请输入正确的年龄", Toast.LENGTH_SHORT).show();
                } else {
                    // 将注册信息添加进数据库
                    UserInfo userInfo = new UserInfo();
                    userInfo.setPhone(phone);
                    userInfo.setPassword(password);
                    userInfo.setName(name);
                    userInfo.setGender(gender);
                    userInfo.setAge(Integer.parseInt(age));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DBOperator dbOperator = new DBOperator(); //调用数据库
                            int new_id = dbOperator.reg_insert_userInfo(userInfo);
                            // 获取数据库自增生成的用户id
                            userInfo.setId(new_id);
                            Message msg = Message.obtain();
                            msg.what = 0;
                            msg.obj = userInfo;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            }
        });
    }

    private boolean match(String age) {
        String regEx = "[0-9]|[1-9][0-9]|1[0-4][0-9]"; //年龄范围在0~149
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(age);
        return matcher.matches();
    }
}