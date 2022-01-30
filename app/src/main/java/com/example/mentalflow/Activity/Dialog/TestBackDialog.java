package com.example.mentalflow.Activity.Dialog;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.example.mentalflow.R;

// 测试返回对话框
public class TestBackDialog extends AlertDialog implements View.OnClickListener {
    Button mBtnCancel, mBtnBack;
    Context mContext;

    public TestBackDialog(Context context,int style) {
        super(context,R.style.DialogTheme);
        mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test_back);

        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        this.setCancelable(false); //在这个时候无法点击返回
        mBtnCancel = (Button) findViewById(R.id.test_dialog_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.test_dialog_back);
        mBtnBack.setOnClickListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_dialog_cancel:
                this.dismiss(); //取消
                break;
            case R.id.test_dialog_back:
                break; //先不设置返回的监听动作，在activity中设置
            default:
                break;
        }
    }
}