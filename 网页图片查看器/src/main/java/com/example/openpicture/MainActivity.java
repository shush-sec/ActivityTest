package com.example.openpicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int OK = 1;
    Bitmap bitmap;
    ImageView imageView;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv_pic);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case OK:
                    ImageView iv = (ImageView) findViewById(R.id.iv_pic);
                    iv.setImageBitmap((Bitmap) msg.obj);
                    break;
            }
        }
    };

    public void openPic(View view) {

        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://wx1.sinaimg.cn/mw600/005Bd8p4gy1ffsviozjbbj30qo0xc43p.jpg");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setRequestMethod("GET");
                    InputStream in = urlConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                       }
                    });
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "加载完成！", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
