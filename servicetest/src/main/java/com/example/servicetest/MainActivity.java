package com.example.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.btn_start);
        Button button2 = (Button) findViewById(R.id.btn_end);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent start = new Intent(this,MyService.class);
                startService(start);
                break;
            case  R.id.btn_end:
                Intent end = new Intent(this,MyService.class);
                stopService(end);
                break;
            default:
                break;
        }
    }
}
