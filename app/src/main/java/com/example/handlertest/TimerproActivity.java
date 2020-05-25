package com.example.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimerproActivity extends AppCompatActivity {

    private final String Tag = "TimerActivity";

    private TextView title, timer, txt;
    private ImageView btn;

    private Handler mHandler;

    private boolean flag = false;

    private Runnable runnable;

    private int cal;
    private String res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timerpro);

        title = findViewById(R.id.title);
        timer = findViewById(R.id.timer);
        txt = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);

        mHandler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                int buff = cal;
                int min = buff / 60;
                int sec = buff % 60;

                res = ((min <= 9) ? ("0" + min) : ("" + min)) + ":" + ((sec <= 9) ? ("0" + sec) : ("" + sec));
                timer.setText(res);
                title.setText("计时中");
                btn.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                txt.setText("");

                cal++;
                mHandler.postDelayed(this, 1000);
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                if (flag) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cal = 0;
                            mHandler.post(runnable);
                        }
                    }).start();
                } else {
                    mHandler.removeCallbacks(runnable);
                    title.setText("计时器");
                    btn.setImageDrawable(getResources().getDrawable(R.drawable.start));
                    txt.setText(res);
                }
            }
        });
    }
}
