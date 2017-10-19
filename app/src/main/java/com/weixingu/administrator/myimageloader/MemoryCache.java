package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MemoryCache implements ImageCache {

    private LruCache<String,Bitmap> mMemeryCache;

    public MemoryCache(){
        //计算可使用的最大内存；
        final  int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        //取四分之一作为可用缓存；
        final int cacheSize = maxMemory/4;
        mMemeryCache =  new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key,Bitmap bitmap){
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {

    }
}
