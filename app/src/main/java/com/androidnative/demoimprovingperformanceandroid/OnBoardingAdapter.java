package com.androidnative.demoimprovingperformanceandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<OnBoardingModel> mList;
    long buildTime = 0;
    public OnBoardingAdapter(Context mContext, ArrayList<OnBoardingModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.item_person, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OnBoardingModel model = mList.get(position);
        holder.mImage.setImageResource(model.getImage());
        holder.mTextName.setText(model.getText()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTextName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.img_header);
            mTextName = itemView.findViewById(R.id.tv_title);
        }
    }

    void setNewList(ArrayList<OnBoardingModel> models){
        this.mList = models;
        notifyDataSetChanged();
    }
}