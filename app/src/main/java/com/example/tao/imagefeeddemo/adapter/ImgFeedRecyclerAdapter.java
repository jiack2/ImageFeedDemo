package com.example.tao.imagefeeddemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.tao.imagefeeddemo.R;
import com.example.tao.imagefeeddemo.model.ImgInfo;
import com.example.tao.imagefeeddemo.ui.ClipZoomImageActivity;
import com.example.tao.imagefeeddemo.utils.GlideRoundTransform;
import com.example.tao.imagefeeddemo.utils.ScreenUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/28 00:15
 */

public class ImgFeedRecyclerAdapter extends RecyclerView.Adapter{
    private List<ImgInfo> imgInfos;
    private Context context;
    private int screenWidth;

    public ImgFeedRecyclerAdapter(Context context) {
        this.context = context;
        imgInfos = new LinkedList<>();
        screenWidth = ScreenUtil.getScreenWidth((Activity) context);
    }

    public void setImgInfos(List<ImgInfo> imgInfos) {
        this.imgInfos.clear();
        this.imgInfos.addAll(imgInfos);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_item, viewGroup, false);
        ImgViewholder viewholder = new ImgViewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImgInfo imgInfo = imgInfos.get(position);
        final ImgViewholder viewholder = (ImgViewholder) holder;
        if (imgInfo.getTitle() != null) {
            viewholder.titleText.setText(imgInfo.getTitle());
        }
        String imgUrl = imgInfo.getImgUrl();
        Glide.with(context).load(imgUrl).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                int width = resource.getWidth();
//                int height = resource.getHeight();
//                int imgViewWidth = screenWidth / 2;
//                int imgViewHeight = (int) (imgViewWidth * (float)height / width);
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewholder.imageView.getLayoutParams();
//                layoutParams.width = imgViewWidth;
//                layoutParams.height = imgViewHeight;
//                viewholder.imageView.setLayoutParams(layoutParams);
                return false;
            }
        }).override(200,200).transform(new GlideRoundTransform(context, 10)).placeholder(R.color.light_gray).into(viewholder.imageView);
    }

    @Override
    public int getItemCount() {
        return imgInfos.size();
    }

    private class ImgViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public TextView titleView;
        public ImageView imageView;
        public TextView titleText;
        public ImgViewholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            titleText = (TextView) itemView.findViewById(R.id.text_title);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("imgUrl", imgInfos.get(getAdapterPosition()).getImgUrl());
            intent.setClass(context, ClipZoomImageActivity.class);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.activity_open,R.anim.activity_close);
        }
    }
}
