package com.example.handlertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn0,btn1,btn2,btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this,TimerActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this,Main3Activity.class));
                break;

            case R.id.btn3:
                startActivity(new Intent(MainActivity.this,TimerproActivity.class));
                break;

            default:
                break;
        }
    }
}
