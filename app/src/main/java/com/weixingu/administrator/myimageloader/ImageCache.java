package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/10/18.
 */

public interface ImageCache {
    Bitmap get(String url);
    void put(String url, Bitmap bitmap);
}
