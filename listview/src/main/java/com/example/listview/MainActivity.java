package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Map<String, Object>> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = list.get(position);
                Toast.makeText(MainActivity.this, map.get("title").toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClickBase(View view) {
        ListView listView = (ListView) findViewById(R.id.lv1);
        Map<String, Object> map = new HashMap<>();
        map.put("logo", R.mipmap.ic_launcher);
        map.put("title", "千千静听");
        map.put("version", "v1.2");
        map.put("size", "12MB");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("logo", R.mipmap.ic_launcher);
        map1.put("title", "网易云音乐");
        map1.put("version", "v3.2");
        map1.put("size", "15MB");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("logo", R.mipmap.ic_launcher);
        map2.put("title", "腾讯云音乐");
        map2.put("version", "v13.2");
        map2.put("size", "98MB");


        list.add(map);
        list.add(map1);
        list.add(map2);

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this
                , list
                , R.layout.item
                , new String[]{"logo", "title", "version", "size"}
                , new int[]{R.id.logo, R.id.title, R.id.version, R.id.size});

        listView.setAdapter(adapter);
    }


}


