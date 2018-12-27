package com.example.tao.imagefeeddemo.view;

import com.example.tao.imagefeeddemo.model.ImgInfo;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:21
 */

public interface ImgFeedView extends MvpView{

    /**
     * show loading view
     */
    void showLoading();

    /**
     * hide loading view when finish load or exception
     */
    void hideLoading();

    /**
     * show error message
     * @param msg
     */
    void showError(String msg);

    /**
     * show list item
     * @param imgInfos
     */
    void showResult(List<ImgInfo> imgInfos);

}
