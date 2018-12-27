package com.example.tao.imagefeeddemo.persenter;

import com.example.tao.imagefeeddemo.view.MvpView;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:34
 */

public interface MvpPresenter<V extends MvpView> {

    /**
     * Bind presenter with MvpView
     * @param view
     */
    public void attachView(V view);

    /**
     * @param retainInstance
     * unBind
     */
    public void detachView(boolean retainInstance);
}
