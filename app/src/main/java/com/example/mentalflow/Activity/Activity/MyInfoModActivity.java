package com.example.mentalflow.Activity.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mentalflow.Activity.Activity.Initial.Test0PreActivity;
import com.example.mentalflow.Activity.DBOperator;
import com.example.mentalflow.Activity.Dialog.MyinfoAgeDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoGenderDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoIntroDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoNameDialog;
import com.example.mentalflow.Activity.Dialog.MyinfoPswDialog;
import com.example.mentalflow.Activity.Entity.UserInfo;
import com.example.mentalflow.R;

// 修改用户信息
public class MyInfoModActivity extends BaseActivity{
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    private TextView mText5;
    private FrameLayout mBt1;
    private FrameLayout mBt2;
    private FrameLayout mBt3;
    private FrameLayout mBt4;
    private FrameLayout mBt5;
    private ImageButton back;

    @Override
    protected int initLayout() {
        return R.layout.activity_mod_myinfo;
    }

    @Override
    protected void initView() {
        mText1=findViewById(R.id.myinfo_mod_name);
        mText2=findViewById(R.id.myinfo_mod_psw);
        mText3=findViewById(R.id.myinfo_mod_age);
        mText4=findViewById(R.id.myinfo_mod_gender);
        mText5=findViewById(R.id.myinfo_mod_intro);
        mBt1=findViewById(R.id.myinfo_mod_name_bt);
        mBt2=findViewById(R.id.myinfo_mod_psw_bt);
        mBt3=findViewById(R.id.myinfo_mod_age_bt);
        mBt4=findViewById(R.id.myinfo_mod_gender_bt);
        mBt5=findViewById(R.id.myinfo_mod_intro_bt);
        back=findViewById(R.id.myinfo_mod_back);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 0){ // 修改昵称

                // 写入文件:UserInfo
                String name= (String) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putString("name",name);
                editor.apply();

                // 刷新该页面
                initData();
            } else if(msg.what == 1){ //修改密码

                // 写入文件:UserInfo
                String psw= (String) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putString("password",psw);
                editor.apply();

                // 刷新该页面
                initData();
            } else if(msg.what == 2){ //修改年龄

                // 写入文件:UserInfo
                int age= (int) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putInt("age",age);
                editor.apply();

                // 刷新该页面
                initData();
            } else if(msg.what == 3) { //修改性别

                // 写入文件:UserInfo
                String gender= (String) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putString("gender",gender);
                editor.apply();

                // 刷新该页面
                initData();
            } else if(msg.what == 4){ //修改简介

                // 写入文件:UserInfo
                String intro= (String) msg.obj;
                SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
                editor.putString("intro",intro);
                editor.apply();

                // 刷新该页面
                initData();
            }
        }
    };

    @Override
    protected void initData() {
        SharedPreferences pref = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        int id = pref.getInt("id",0);
        String name = pref.getString("name","");
        mText1.setText(name);
        String psw = pref.getString("password","");
        mText2.setText(psw);
        int age = pref.getInt("age",0);
        mText3.setText(Integer.toString(age));
        String gender = pref.getString("gender","");
        mText4.setText(gender);
        String intro = pref.getString("intro","");
        if(intro.length()!=0){
            mText5.setText(intro);
        } else {
            mText5.setText(R.string.myinfo_mod_intro1);
        }


        //返回键
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //弹出对话框进行修改
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyinfoNameDialog dialog = new MyinfoNameDialog(mContext, R.style.DialogTheme);
                dialog.show();
                EditText newName = (EditText)  dialog.findViewById(R.id.myinfo_name_content);
                Button button = (Button) dialog.findViewById(R.id.myinfo_dialog_update);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newname=newName.getText().toString();
                        if(newname.length()==0) {
                            Toast.makeText(mContext, "输入框不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            //调用线程，执行数据库更新操作
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    dbOperator.myinfo_update_name(id, newname); //执行修改操作
                                    Message msg = Message.obtain();
                                    msg.what = 0;
                                    msg.obj = newname;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyinfoPswDialog dialog = new MyinfoPswDialog(mContext, R.style.DialogTheme);
                dialog.show();
                EditText newPsw = (EditText)  dialog.findViewById(R.id.myinfo_psw_content);
                Button button = (Button) dialog.findViewById(R.id.myinfo_dialog_update);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newpsw=newPsw.getText().toString();
                        if(newpsw.length()==0) {
                            Toast.makeText(mContext, "输入框不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            //调用线程，执行数据库更新操作
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    dbOperator.myinfo_update_psw(id, newpsw); //执行修改操作
                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    msg.obj = newpsw;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        mBt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyinfoAgeDialog dialog = new MyinfoAgeDialog(mContext, R.style.DialogTheme);
                dialog.show();
                EditText newAge = (EditText)  dialog.findViewById(R.id.myinfo_age_content);
                Button button = (Button) dialog.findViewById(R.id.myinfo_dialog_update);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(newAge.length()==0) {
                            Toast.makeText(mContext, "输入框不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            Integer newage = Integer.parseInt(newAge.getText().toString());
                            //调用线程，执行数据库更新操作
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    dbOperator.myinfo_update_age(id, newage); //执行修改操作
                                    Message msg = Message.obtain();
                                    msg.what = 2;
                                    msg.obj = newage;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        mBt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyinfoGenderDialog dialog = new MyinfoGenderDialog(mContext, R.style.DialogTheme);
                dialog.show();
                ImageButton maleButton = (ImageButton)  dialog.findViewById(R.id.myinfo_male);
                ImageButton femaleButton = (ImageButton) dialog.findViewById(R.id.myinfo_female);
                Button button = (Button) dialog.findViewById(R.id.myinfo_dialog_update);
                // 标记性别被选中
                final boolean[] male = {false};
                final boolean[] female = {false};

                // 如果男被选中
                maleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 修改female状态
                        female[0] = false;
                        femaleButton.setActivated(false);
                        // 修改male状态
                        male[0] = true;
                        maleButton.setActivated(true);
                    }
                });

                // 如果女被选中
                femaleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 修改female状态
                        female[0] = true;
                        femaleButton.setActivated(true);
                        // 修改male状态
                        male[0] = false;
                        maleButton.setActivated(false);
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String gender="";
                        if(maleButton.isActivated()) { // 如果选了男
                            gender="男";
                            //调用线程，执行数据库更新操作
                            String finalGender1 = gender;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    dbOperator.myinfo_update_gender(id, finalGender1); //执行修改操作
                                    Message msg = Message.obtain();
                                    msg.what = 3;
                                    msg.obj = finalGender1;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                            dialog.dismiss();
                        } else if(femaleButton.isActivated()) { // 如果选了女
                            gender="女";
                            //调用线程，执行数据库更新操作
                            String finalGender = gender;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    DBOperator dbOperator = new DBOperator(); //调用数据库
                                    dbOperator.myinfo_update_gender(id, finalGender); //执行修改操作
                                    Message msg = Message.obtain();
                                    msg.what = 3;
                                    msg.obj = finalGender;
                                    handler.sendMessage(msg);
                                }
                            }).start();
                            dialog.dismiss();
                        } else { //如果两个都没选
                            Toast.makeText(mContext, "请选择性别", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mBt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyinfoIntroDialog dialog = new MyinfoIntroDialog(mContext, R.style.DialogTheme);
                dialog.show();
                EditText newIntro = (EditText)  dialog.findViewById(R.id.myinfo_intro_content);
                Button button = (Button) dialog.findViewById(R.id.myinfo_dialog_update);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newintro=newIntro.getText().toString();
                        //调用线程，执行数据库更新操作
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                DBOperator dbOperator = new DBOperator(); //调用数据库
                                dbOperator.myinfo_update_intro(id,newintro); //执行修改操作
                                Message msg = Message.obtain();
                                msg.what = 4;
                                msg.obj = newintro;
                                handler.sendMessage(msg);
                            }
                        }).start();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
