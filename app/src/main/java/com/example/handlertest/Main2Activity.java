package com.example.handlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 利用Handler，从主线程向子线程传递消息
//利用Handler，子线程向主线程传递消息

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private final String Tag = "Main2Activity";

//    主线程的Handler
    private Handler Main_Handler;
//    子线程的Handler
    private Handler Thread_Handler;

    private Button btn0;
    private Button btn1;





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);



        Main_Handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 100){
                    Log.d(Tag,"线程号：" +  Thread.currentThread().getId());
                    Log.d(Tag,"线程名：" +Thread.currentThread().getName());
                    Log.d(Tag,"子线程向主线程传递的消息，消息的值是：" + msg.what);
                }
            }
        };

//      新建子线程，在子线程中配置Handler
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Thread_Handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 200){
                            Log.d(Tag,"线程号：" +  Thread.currentThread().getId());
                            Log.d(Tag,"线程名：" +Thread.currentThread().getName());
                            Log.d(Tag,"主线程向子线程传递的消息，消息的值是：" + msg.what);
                        }
                    }
                };

                Looper.loop();
            }
        }).start();
    }


//    子线程向主线程传递消息
    private void click(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Main_Handler.sendEmptyMessage(100);
            }
        }).start();
    }


//  主线程向子线程传递消息
    private void click1(){
        Thread_Handler.sendEmptyMessage(200);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                click();
                break;

            case R.id.btn1:
                click1();
                break;

            default:
                break;
        }

    }
}
