package com.example.tao.imagefeeddemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.tao.imagefeeddemo.R;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/28 02:15
 */

public class WelcomeActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()) {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                }
            }
        },3000);
    }
}
