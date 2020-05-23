package com.example.handlertest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetPicThread implements Runnable{

    private Handler mHandler;
    private String path;



    public GetPicThread(Handler handler, String path){
        this.mHandler = handler;
        this.path = path;
    }


    @Override
    public void run() {
        try {
            Message msg = Message.obtain();
            Bitmap bitmap = GetMethod(path);

            if (bitmap != null){
                msg.what = 1;
                msg.obj = bitmap;
                mHandler.sendMessage(msg);

            }else {
                msg.what = 0;
                mHandler.sendMessage(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Bitmap GetMethod(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(6000);


        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            InputStream in = conn.getInputStream();
            return BitmapFactory.decodeStream(in);
        }
        return null;
    }

}
