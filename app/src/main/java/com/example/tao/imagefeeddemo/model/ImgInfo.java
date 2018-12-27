package com.example.tao.imagefeeddemo.model;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:03
 */

public class ImgInfo implements Serializable {
    private String title;
    private String imgUrl;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
