package com.example.mentalflow.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalflow.Activity.Activity.DoctorInfoActivity;
import com.example.mentalflow.Activity.Entity.DoctorCard;
import com.example.mentalflow.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DoctorRecyclerAdapter extends RecyclerView.Adapter<DoctorRecyclerAdapter.myViewHodler>{
    private List<DoctorCard> mDoctorCardList;
    private Context context;
    public DoctorRecyclerAdapter(Context context,List<DoctorCard> mDoctorCardList) {
        this.mDoctorCardList = mDoctorCardList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public DoctorRecyclerAdapter.myViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= View.inflate(context,R.layout.item_doc_card,null);
        final myViewHodler holder=new myViewHodler(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DoctorInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(intent);
            }
        });
        return new myViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DoctorRecyclerAdapter.myViewHodler holder, int position) {
        //根据点击位置绑定数据
        DoctorCard doctorCard=mDoctorCardList.get(position);
        holder.docImage.setImageResource(doctorCard.getImageId());
        holder.doc_name.setText(doctorCard.getDocName());
        holder.label1.setText(doctorCard.getType());
        holder.label2.setText(doctorCard.getType());
        holder.doc_info.setText(doctorCard.getIntro());
    }

    @Override
    public int getItemCount() {
        return mDoctorCardList.size();
    }


    static class myViewHodler extends RecyclerView.ViewHolder  {

        private ImageView docImage;
        private TextView doc_name;
        private TextView label1;
        private TextView label2;
        private TextView doc_info;

        public myViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);
            docImage=(ImageView) itemView.findViewById(R.id.dc_image);
            doc_name=(TextView)itemView.findViewById(R.id.dc_name);
            label1=(TextView)itemView.findViewById(R.id.dc_label);
            label2=(TextView)itemView.findViewById(R.id.dc_label);
            doc_info=(TextView)itemView.findViewById(R.id.dc_info);
        }
    }


}
