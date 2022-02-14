package com.example.mentalflow.Activity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalflow.Activity.Activity.TestActivity;
import com.example.mentalflow.Activity.Entity.TestCard;
import com.example.mentalflow.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
/*
 *Recycler界面的写法参考
 *https://blog.csdn.net/zhuchenglin830/article/details/82286109
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.myViewHodler>{
    private List<TestCard> mTestCardList;
    private Context mContext;

    public TestRecyclerAdapter(Context context,List<TestCard> mTestCardList) {
        this.mTestCardList = mTestCardList;
        this.mContext = context;
    }
    @NonNull
    @NotNull
    @Override
    public TestRecyclerAdapter.myViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //创建自定义布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new myViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TestRecyclerAdapter.myViewHodler holder, int position) {
        //根据点击位置绑定数据
        TestCard testCard=mTestCardList.get(position);
        holder.mImageView.setImageResource(testCard.getImageId());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = testCard.getId();
                int test_category = testCard.getCategory();
                Intent intent = new Intent(mContext, TestActivity.class);
                intent.putExtra("test_category",test_category);
                intent.putExtra("test_id",id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTestCardList.size();
    }

    public class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public myViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_test_image);
        }
    }
}
