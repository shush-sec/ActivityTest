package com.example.callPhone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn_callphone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {

                    testCallPhone();
                }

            }
        });

        Button btnSendSMS = (Button) findViewById(R.id.btn_sendsms);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSendSMS();
            }
        });
    }

    public void testSendSMS() {
        EditText editText = (EditText) findViewById(R.id.tv_num);
        String num = editText.getText().toString();

        EditText et = (EditText) findViewById(R.id.tv_content);
        String content = et.getText().toString();

        //获取sms管理器
        SmsManager smsManager = SmsManager.getDefault();
        //拆分短信
        ArrayList<String> parts = smsManager.divideMessage(content);
        //发送短信
        smsManager.sendMultipartTextMessage(num, null, parts, null, null);
    }

    //打电话
    public void testCallPhone() {
        EditText editText = (EditText) findViewById(R.id.tv_num);
        String num = editText.getText().toString();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + num));
        startActivity(intent);
    }
}
