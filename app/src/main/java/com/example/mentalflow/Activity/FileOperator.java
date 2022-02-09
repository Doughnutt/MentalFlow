package com.example.mentalflow.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Objects;

// 用于对数据进行文件存储和读取
public class FileOperator {

    // 存储数据：int
    public void saveData(Context context, String filename, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    // 获取数据：int
    public int getData(Context context, String filename, String key, int defValue) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getInt(key, defValue);
    }

    // 存储数据：string
    public void saveData(Context context, String filename, String key,String value) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    // 获取数据：string
    public String getData(Context context, String filename, String key, String defValue) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }

    // 存储数据：boolean
    public void saveData(Context context, String filename, String key,boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    // 获取数据：boolean
    public boolean getData(Context context, String filename, String key, boolean defValue) {
        SharedPreferences preferences = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValue);
    }
}
