package com.example.handlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Main3Activity extends AppCompatActivity implements View.OnClickListener {


    static class MyHandler extends Handler{
        WeakReference<Main3Activity> weakReference;

        private MyHandler(Main3Activity activity){
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null){

                if (msg.what == 1){
                    Bitmap map = (Bitmap) msg.obj;
                    weakReference.get().img.setImageBitmap(map);
                }
//                msg.what == 0
                else {
                    Toast.makeText(weakReference.get().getApplicationContext(),"网络连接错误",Toast.LENGTH_SHORT);
                }
            }
        }
    }


    private Button btn;
    private ImageView img;
    private MyHandler handler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        img = findViewById(R.id.img);
        handler = new MyHandler(this);

    }


    private void demo(){
        try {
            URL url = new URL("https://img2.mukewang.com/5adfee7f0001cbb906000338-240-135.jpg");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = conn.getInputStream();
                Bitmap map = BitmapFactory.decodeStream(in);

                Message msg = Message.obtain();
                msg.obj = map;
                msg.what = 1;
                handler.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn:
                new Thread(new GetPicThread(handler,"https://zhengxin-pub.bj.bcebos.com/logopic/8b832625aca0500a96f68d4775ee7473_fullsize.jpg?x-bce-process=image/resize,m_lfit,w_200")).start();
                break;
            default:
                break;
        }

    }

}
