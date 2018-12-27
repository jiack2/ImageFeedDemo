package com.example.tao.imagefeeddemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.example.tao.imagefeeddemo.R;
import com.example.tao.imagefeeddemo.view.ClipZoomImageView;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/28 01:39
 */

public class ClipZoomImageActivity extends Activity{
    private ClipZoomImageView clipZoomImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_img);
        String imgUrl = getIntent().getStringExtra("imgUrl");
        clipZoomImageView = (ClipZoomImageView) findViewById(R.id.img_clip);
        if (imgUrl != null && !imgUrl.equals("")) {
            Glide.with(this).load(imgUrl).into(clipZoomImageView);
        }
        clipZoomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_open,R.anim.activity_close);
    }
}
