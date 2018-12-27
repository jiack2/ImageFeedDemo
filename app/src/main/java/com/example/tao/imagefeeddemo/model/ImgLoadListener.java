package com.example.tao.imagefeeddemo.model;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:05
 */

public interface ImgLoadListener {
    void onLoadSuccess(List<ImgInfo> rssInfoList);
    void onLoadFailed();
    void onNetWorkFailed();
    void onBeginLoad();
}
