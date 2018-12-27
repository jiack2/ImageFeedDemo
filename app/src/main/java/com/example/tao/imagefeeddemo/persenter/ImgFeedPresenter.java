package com.example.tao.imagefeeddemo.persenter;

import android.content.Context;
import android.util.Log;

import com.example.tao.imagefeeddemo.model.ImgInfo;
import com.example.tao.imagefeeddemo.model.ImgLoadListener;
import com.example.tao.imagefeeddemo.model.ImgLoadModel;
import com.example.tao.imagefeeddemo.utils.MainContacts;
import com.example.tao.imagefeeddemo.view.ImgFeedView;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:34
 */

public class ImgFeedPresenter implements MvpPresenter<ImgFeedView> {
    private final static String TAG = "ImgFeedPresenter";
    private ImgFeedView view;
    private Context context;

    public ImgFeedPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(ImgFeedView view) {
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    public void startLoadTask() {
        if (view == null) {
            Log.w(TAG, "please attach view first.");
            return;
        }

        new ImgLoadModel(context, MainContacts.URL).excute(new ImgLoadListener() {

            @Override
            public void onBeginLoad() {
                view.showLoading();
            }

            @Override
            public void onLoadSuccess(List<ImgInfo> imgInfos) {
//                Log.d(TAG, "onLoadSuccess: ");
                view.hideLoading();
                view.showResult(imgInfos);
            }

            @Override
            public void onLoadFailed() {
                view.hideLoading();
                view.showError("更新失败");
            }

            @Override
            public void onNetWorkFailed() {
                view.showError("网络不可用");
                view.hideLoading();
            }
        });
    }
}
