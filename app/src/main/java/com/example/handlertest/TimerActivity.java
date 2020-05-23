package com.example.handlertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private final String Tag = "TimerActivity";

    private TextView title,timer,txt;
    private ImageView btn;

    private Handler mHandler;

    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        title = findViewById(R.id.title);
        timer = findViewById(R.id.timer);
        txt = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag = !flag;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int i = -1;
                            while(flag){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                i++;
                                Message msg = Message.obtain();
                                msg.arg1 = i;
                                mHandler.sendMessage(msg);
                            }
                        }
                    }).start();

                }
        });

        //        显示操作
        mHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                    int buff = msg.arg1;
                    int min = buff / 60;
                    int sec = buff % 60;
                    String res = ((min<=9) ? ("0"+min): (""+min))  + ":" +((sec<=9) ? ("0"+sec): (""+sec)) ;

                    Log.d(Tag,String.valueOf(flag));

                if(flag){
                    timer.setText(res);
                    title.setText("计时中");
                    btn.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                    txt.setText("");
                }else {
                    title.setText("计时器");
                    btn.setImageDrawable(getResources().getDrawable(R.drawable.start));
                    txt.setText(res);
                }

            }
        };
    }
}
