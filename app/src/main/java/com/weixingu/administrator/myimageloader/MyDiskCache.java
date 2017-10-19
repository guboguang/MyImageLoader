package com.weixingu.administrator.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/18.
 */

public class MyDiskCache implements ImageCache {

    //SD卡路径
    static String imagePath = getSDPath() + "/" + "ImageFile" + "/";

    //从缓存中获取图片
    public Bitmap get(String url) {
        url = url.substring(url.length()-37,url.length());
        Log.i("get","=====fileName==="+url);
        url=url.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……& ;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        Log.i("get","=====fileName==="+url);
        return BitmapFactory.decodeFile(imagePath + url);
    }

    //将图片放在内存中
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;

        try {

            File file = new File(imagePath);

            if (!file.exists()) {
                file.mkdirs();
            }
            //通过UUID生成字符串文件名
            //String fileName = UUID.randomUUID().toString();
            if(url.length()>40) {
                url = url.substring(url.length() - 37, url.length());
                Log.i("put", "=====url===" + url);
            }

            url=url.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……& ;*（）——+|{}【】‘；：”“’。，、？|-]", "");

            File file2 = new File(imagePath +"/"+ url);
            if (!file2.exists()) {
                try {
                    boolean newFile = file2.createNewFile();
                    if (newFile) {
                        fileOutputStream = new FileOutputStream(file2);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (fileOutputStream != null) {

                        try {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static String getSDPath () {
            File sdDir = null;
            boolean sdCardExist = Environment.getExternalStorageState()
                    .equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
            if (sdCardExist) {
                sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            }
            return sdDir.toString();
        }
    }
