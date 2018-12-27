package com.example.tao.imagefeeddemo.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/28 01:02
 */

public class ScreenUtil {

    public static int getScreenWidth(Activity mActivity) {
        int screenW = 0;
        if (mActivity == null) return screenW;
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenW = metric.widthPixels;
        return screenW;
    }
}
