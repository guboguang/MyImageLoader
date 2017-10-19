package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/10/14.
 */

public class MyImageLoader{

    //图片缓存
    ImageCache mImageCache = new MyImageCache();

    //线程池，线程数量为CPU的数量；
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


   public void setmImageCache(ImageCache mImageCache){
       this.mImageCache = mImageCache;
   }

    public MyImageLoader(){
    }

    public void displayImage(final String url,final ImageView imageView){

        Bitmap bitmap = mImageCache.get(url);

        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }else {
            submitLoadRequest(url,imageView);
        }
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {

        imageView.setTag(url);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = new DownloadBitmap(url).downloadImage();
                if(bitmap ==null){
                    return;
                }

                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }

                mImageCache.put(url,bitmap);
            }
        });
    }

}
