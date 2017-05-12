package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAutoCompleteTextView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_auto_complete_text_view);

        loadMyAutoCompleteTextView();
    }

//    public void loadAutoCompleteTextView() {
//
//        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTv);
//
//        String[] citys = {"beijing", "shanghai", "tianjing", "beiping"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this
//                , android.R.layout.simple_list_item_1
//                , android.R.id.text1
//                , citys);
//
//        autoCompleteTextView.setAdapter(arrayAdapter);
//
//    }

    public void loadMyAutoCompleteTextView() {
        AutoCompleteTextView autoCompleteTextView =
                (AutoCompleteTextView) findViewById(R.id.autoCompleteTv);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("text_name", "beijing");
        map1.put("imageView_logo",String.valueOf(R.mipmap.ic_launcher) );

        Map<String, Object> map2 = new HashMap<>();
        map2.put("text_name", "beiping");
        map2.put("imageView_logo", String.valueOf(R.mipmap.ic_launcher_round));


        Map<String, Object> map3 = new HashMap<>();
        map3.put("text_name", "guangzhou");
        map3.put("imageView_logo", String.valueOf(R.mipmap.ic_launcher));

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);

        SimpleAdapter adapter = new SimpleAdapter(this
                , list
                , R.layout.my_autocompletetheme
                , new String[]{"imageView_logo", "text_name"}
                , new int[]{R.id.imageView_logo, R.id.text_name});
        autoCompleteTextView.setAdapter(adapter);

    }
}
