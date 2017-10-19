package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyImageCache implements ImageCache {

    //图片LRU缓存
    LruCache<String,Bitmap> mImageCache;

    public MyImageCache(){
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大的内存；
        final  int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/4;
        mImageCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

    public Bitmap get(String url){

        return mImageCache.get(url);
    }
}
