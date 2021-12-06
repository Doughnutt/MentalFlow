package com.example.mentalflow.Activity.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalflow.Activity.Entity.ArticleCard;
import com.example.mentalflow.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.myViewHodler>{
    private List<ArticleCard> mArticleCardList;
    private Context context;

    public ArticleRecyclerAdapter(Context context,List<ArticleCard> mArticleCardList) {
        this.mArticleCardList = mArticleCardList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public myViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.item_article,null);
        return new ArticleRecyclerAdapter.myViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArticleRecyclerAdapter.myViewHodler holder, int position) {
        //根据点击位置绑定数据
        ArticleCard articleCard=mArticleCardList.get(position);
        holder.acImage.setImageResource(articleCard.getImageId());
        holder.ac_name.setText(articleCard.getArticleName());
        holder.label.setText(articleCard.getLabel());
        holder.ac_info.setText(articleCard.getIntro());
    }

    @Override
    public int getItemCount() {
        return mArticleCardList.size();
    }

    public class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView acImage;
        private TextView ac_name;
        private TextView label;
        private TextView ac_info;

        public myViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);
            acImage=(ImageView) itemView.findViewById(R.id.article_pic);
            ac_name=(TextView)itemView.findViewById(R.id.article_title);
            label=(TextView)itemView.findViewById(R.id.article_label);
            ac_info=(TextView)itemView.findViewById(R.id.article_info);
        }
    }
}
