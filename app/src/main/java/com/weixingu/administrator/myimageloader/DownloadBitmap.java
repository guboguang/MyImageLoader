package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/16.
 */

public class DownloadBitmap {

    private final String imageUrl;

    public DownloadBitmap(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public Bitmap downloadImage(){
        Bitmap  bitmap = null;
        try{
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    };
}
