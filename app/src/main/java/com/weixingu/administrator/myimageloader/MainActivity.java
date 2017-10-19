package com.weixingu.administrator.myimageloader;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    public Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    private String url;
    private MyImageLoader myImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.image_picture);
        //http://img0.imgtn.bdimg.com/it/u=2907681597,3594392251&fm=27&gp=0.jpg
        url = "http://img0.imgtn.bdimg.com/it/u=2907681597,3594392251&fm=27&gp=0.jpg";

        myImageLoader = new MyImageLoader();

        myImageLoader.setmImageCache(new MyDoubleCache());
        myImageLoader.displayImage(url,imageView);

        /*new Thread(new Runnable() {
            @Override
            public void run() {

                Message obtain = Message.obtain();
                obtain.what = 1;
                handler.sendMessage(obtain);

            }
        }).start();*/





    }
}
