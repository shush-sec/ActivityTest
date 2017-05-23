package com.example.mobilequery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private String mPath;
    private static PhoneInfo phoneInfo;
    private TextView mPhone;
    private TextView mArea;
    private TextView mCtype;
    private TextView mAtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.mobile_et);
        mPhone = (TextView) findViewById(R.id.phone);
        mArea = (TextView) findViewById(R.id.area);
        mCtype = (TextView) findViewById(R.id.ctype);
        mAtt = (TextView) findViewById(R.id.att);

    }


    public void query(View view) {
        String url = "http://api.k780.com:88";
        String phone = mEditText.getText().toString();

        mPath = url+"/?app=phone.get&phone="+phone+"&" +
                "appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";

        new Thread(){
            @Override
            public void run() {

                try {
                    URL url = new URL(mPath);
                    System.out.println(mPath);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setRequestMethod("GET");
                    urlConn.setConnectTimeout(5000);
                    int code = urlConn.getResponseCode();

                    if (code == 200){
                        InputStream in = urlConn.getInputStream();
                        phoneInfo =  xmlPull(in);
                        //将值显示到界面
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mPhone.setText(phoneInfo.phone);
                                mArea.setText(phoneInfo.area);
                                mCtype.setText(phoneInfo.ctype);
                                mAtt.setText(phoneInfo.att);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public static PhoneInfo xmlPull(InputStream in){

        try {
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(in,"utf-8");
            int eventType = pullParser.getEventType();
            phoneInfo = new PhoneInfo();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName =pullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("START_DOCUMENT:"+nodeName);
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("START_TAG:"+nodeName);
                        if ("success".equals(nodeName))
                            phoneInfo.setSuccess(pullParser.nextText());
                        else if("phone".equals(nodeName))
                            phoneInfo.setPhone(pullParser.nextText());
                        else if("area".equals(nodeName))
                            phoneInfo.setArea(pullParser.nextText());
                        else if("ctype".equals(nodeName))
                            phoneInfo.setCtype(pullParser.nextText());
                        else if("att".equals(nodeName))
                            phoneInfo.setAtt(pullParser.nextText());
                        break;
                    case XmlPullParser.END_TAG:
                        System.out.println("END_TAG:"+nodeName);
                        break;
                    default:break;
                }
                //!!!!必须手动触发下一个事件
                eventType = pullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return phoneInfo;
    }

}
