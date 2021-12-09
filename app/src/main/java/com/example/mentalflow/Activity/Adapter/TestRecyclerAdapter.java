package com.example.mentalflow.Activity.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    private Context context;

    public TestRecyclerAdapter(Context context,List<TestCard> mTestCardList) {
        this.mTestCardList = mTestCardList;
        this.context = context;
    }
    @NonNull
    @NotNull
    @Override
    public TestRecyclerAdapter.myViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //创建自定义布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        //View view= View.inflate(context,R.layout.item_test,null);
        return new myViewHodler(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull TestRecyclerAdapter.myViewHodler holder, int position) {
        //根据点击位置绑定数据
        TestCard testCard=mTestCardList.get(position);
        holder.mImageView.setImageResource(testCard.getImageId());;
        holder.mTextView.setText(testCard.getTestName());
    }

    @Override
    public int getItemCount() {
        return mTestCardList.size();
    }

    public class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
//        private Button mButton;

        public myViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_test_iv);
            mTextView = (TextView) itemView.findViewById(R.id.item_test_tv);
//            mButton = (Button) itemView.findViewById(R.id.item_test_btn);
        }
    }
}
