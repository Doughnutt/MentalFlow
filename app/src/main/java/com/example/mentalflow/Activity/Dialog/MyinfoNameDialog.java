package com.example.mentalflow.Activity.Dialog;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mentalflow.R;

// 修改昵称对话框
public class MyinfoNameDialog extends AlertDialog implements View.OnClickListener {
    Button mBtnCancel, mBtnUpdate;
    Context mContext;
//    EditText newName;

    public MyinfoNameDialog(Context context,int style) {
        super(context,R.style.DialogTheme);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_myinfo_name);

        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        this.setCancelable(false); //在这个时候无法点击返回
        mBtnCancel = (Button) findViewById(R.id.myinfo_dialog_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnUpdate = (Button) findViewById(R.id.myinfo_dialog_update);
        mBtnUpdate.setOnClickListener(this);
//        newName=(EditText) findViewById(R.id.myinfo_name_content);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myinfo_dialog_cancel:
                this.dismiss(); //取消
                break;
            case R.id.myinfo_dialog_update:
                break; //先不设置返回的监听动作，在activity中设置
            default:
                break;
        }
    }
}