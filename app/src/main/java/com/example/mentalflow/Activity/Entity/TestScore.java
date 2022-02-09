package com.example.mentalflow.Activity.Entity;

import java.util.ArrayList;

// 用于记录测试过程中的选项，并返回计算得到的结果
public class TestScore {
    private int test_id; //记录测试的id
    private int[] option; //记录用户的选项

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int[] getOption() {
        return option;
    }

    public void setOption(int[] option) {
        this.option = option;
    }
}
