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
import com.example.mentalflow.Activity.Activity.HomeActivity;
import com.example.mentalflow.Activity.Activity.MainActivity;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Entity.UserInfo;
import com.example.mentalflow.Activity.FileOperator;
import com.example.mentalflow.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegPasswordActivity extends BaseActivity {

    private EditText mPassword;
    private Button mNextButton;
    private ImageButton mBackButton;
    private final Context context = RegPasswordActivity.this;

    @Override
    protected int initLayout() {
        return R.layout.activity_reg_password;
    }

    @Override
    protected void initView() {
        mBackButton=findViewById(R.id.check_back);
        mNextButton=findViewById(R.id.check_next);
        mPassword=findViewById(R.id.check_et);

        Handler mainHandler = new Handler(getMainLooper()); //获取主线程
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.what == 0) { // 登录不成功
                Toast.makeText(context, "账号密码不匹配，请重新输入", Toast.LENGTH_SHORT).show();
            } else if(msg.what == 1){ // 登录成功，跳转主页
                Intent intent = new Intent(RegPasswordActivity.this, HomeActivity.class);
                UserInfo userInfo = (UserInfo) msg.obj;
                // 写入文件:UserInfo
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putInt("id",userInfo.getId());
                editor.putString("phone",userInfo.getPhone());
//                editor.putString("password",userInfo.getPassword());
                editor.putString("name",userInfo.getName());
                editor.putString("gender", userInfo.getGender());
                editor.putInt("age", userInfo.getAge());
                editor.putString("intro",userInfo.getIntro());
                editor.apply();
                Toast.makeText(context,"你好，"+userInfo.getName(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        }
    };

    @Override
    protected void initData() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPassword == null||mPassword.length()==0) { //输入为空
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    String password = mPassword.getText().toString().trim();

                    Intent intent = getIntent();
                    int state = intent.getIntExtra("state",1); //获取状态：此处默认值为1，以防用户从后返回
                    String phone = intent.getStringExtra("phone"); //获取账号

                    if(state==0) { //登录状态
                        if (!match(password)) { //匹配不正确
                            Toast.makeText(context, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        } else { //查询数据库
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    Object res = dbOperator.login_search_psw(phone,password);
                                    Message msg = Message.obtain();
                                    if(res instanceof Boolean) { //如果结果是false，表示匹配不成功
                                        msg.what = 0;
                                    } else { //匹配成功
                                        msg.what = 1;
                                        msg.obj = res;
                                    }
                                    handler.sendMessage(msg);
                                }
                            }).start();
                        }
                    } else { //注册状态
                        if (!match(password)) { //匹配不正确
                            Toast.makeText(context, "请输入8~16位密码，必须同时包含字母与数字", Toast.LENGTH_LONG).show();
                        } else { // 匹配成功，传递数据并跳转
                            intent = new Intent(RegPasswordActivity.this,RegNameActivity.class);
                            intent.putExtra("phone",phone);
                            intent.putExtra("password",password);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                        }
                    }
                }
            }
        });
    }
    private boolean match(String password) {
        String regEx = "[0]|(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}"; //密码由8~16位字母与数字组合而成
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}