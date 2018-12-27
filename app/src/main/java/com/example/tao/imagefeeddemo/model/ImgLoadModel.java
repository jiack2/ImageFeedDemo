package com.example.tao.imagefeeddemo.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.tao.imagefeeddemo.utils.NetworkUtils;
import com.example.tao.imagefeeddemo.utils.XMLRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:jiack
 * @date:2018/12/27 23:06
 */

public class ImgLoadModel implements ImgLoad{
    private final static String TAG = "ImgLoadModel";
    private final static String TITLE = "title";
    private final static String ENCLOSURE = "enclosure";
    private final static String ITEM = "item";
    private final static String DESCRIPTION = "description";

    private List<ImgInfo> imgInfos;
    private RequestQueue mQueue;
    private Context context;
    private String url;

    public ImgLoadModel(Context context, String url) {
        this.context = context;
        this.url = url;
        imgInfos = new LinkedList<>();
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void excute(final ImgLoadListener imgLoadListener) {
        if(!NetworkUtils.isNetworkAvailable(context)){
            imgLoadListener.onNetWorkFailed();
            return;
        }
        imgLoadListener.onBeginLoad();
        XMLRequest xmlRequest = new XMLRequest(url,
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        parserXML(response);
                        imgLoadListener.onLoadSuccess(imgInfos);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                imgLoadListener.onLoadFailed();
            }
        });
        mQueue.add(xmlRequest);
    }

    /**
     * 解析xml文件(由于时间关系，以最快方式获取img信息，数据解析不是特别严谨规范)
     * @param response
     */
    private void parserXML(XmlPullParser response){
        try {
            imgInfos.clear();
            ImgInfo imgInfo = null;
            String title = null;
            String description = null;
            String imgUrl = null;
            int eventType = response.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT && imgInfos.size() <= 15) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String nodeName = response.getName();
//                        Log.d(TAG, "parserXML: "+nodeName);
                        switch (nodeName){
                            case ITEM:
                                imgInfo = new ImgInfo();
                                break;
                            case TITLE:
                                title= response.nextText();
                                break;
                            case DESCRIPTION:
                                description = response.nextText();
                                break;
                            case ENCLOSURE:
                                imgUrl = response.getAttributeValue(0);
                                if (imgUrl != null && !imgUrl.equals("")) {
                                    imgInfo.setTitle(title);
                                    imgInfo.setImgUrl(imgUrl);
//                                    imgInfo.setImgUrl("http://qnimg.9igcw.com/o_1c1u268tp6u61rdgkrn1rujsij9.jpg");
                                    imgInfo.setDesc(description);
                                    imgInfos.add(imgInfo);
                                }
//                                Log.d(TAG, "imgUrl: "+imgUrl);
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        break;
                }
                eventType = response.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
