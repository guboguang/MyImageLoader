package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyDoubleCache implements ImageCache {

    MyImageCache myImageCache = new MyImageCache();
    MyDiskCache myDiskCache = new MyDiskCache();


    //先从内存缓存中获取图片，如果没有，再从SD卡中获取；
    public Bitmap get(String url){

        Bitmap bitmap = myImageCache.get(url);

        if(bitmap ==null){
            bitmap =  myDiskCache.get(url);
        }

        return bitmap;

    }

    public void put(String url,Bitmap bitmap){
        myDiskCache.put(url,bitmap);
        myImageCache.put(url,bitmap);
    }
}
