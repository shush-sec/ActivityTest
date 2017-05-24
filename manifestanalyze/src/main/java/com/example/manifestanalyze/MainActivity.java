package com.example.manifestanalyze;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> infos = analyze();
        System.out.println(infos.toString());
    }

    private List<String> analyze() {
        List<String> messages = new ArrayList<>(); //保存解析的信息
        try {

            InputStream in = getAssets().open("AndroidManifest.xml");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(in, "utf-8");
            int eventType = pullParser.getEventType();


            String mainActivity = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = pullParser.getName();
                String message = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("manifest".equals(nodeName))
                            message = "包名：" + pullParser.getAttributeValue(null, "package")+"\n";
                        else if ("uses-permission".equals(nodeName))
                            message = "权限有：" + pullParser.getAttributeValue(null, "android:name"+"\n");
                        else if ("activity".equals(nodeName))
                            message = "activity有：" + pullParser.getAttributeValue(null, "android:name"+"\n");
                        else if ("service".equals(nodeName))
                            message = "service有：" + pullParser.getAttributeValue(null, "android:name"+"\n");


                    default:
                        break;
                }
                

                //!!!!必须手动触发下一个事件
                eventType = pullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void analyze(View view) {
        analyze();
    }
}
