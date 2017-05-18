package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("", "onServiceDisconnected: ");
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btn_start);
        Button btnEnd = (Button) findViewById(R.id.btn_end);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

        Button btnBindService = (Button) findViewById(R.id.btn_bind_service);
        Button btnUnbingdService = (Button) findViewById(R.id.btn_unbind_service);
        btnBindService.setOnClickListener(this);
        btnUnbingdService.setOnClickListener(this);

        Button btnStartIntentService = (Button) findViewById(R.id.btn_start_intentservice);
        btnStartIntentService.setOnClickListener(this);
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
            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);
                break;
            case R.id.btn_start_intentservice:
                Log.d("MainActivity", "Thread id is "+Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
