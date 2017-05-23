package com.example.mobilexiongji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject person = new JSONObject();
        JSONArray phone = new JSONArray();
        phone.put("1398222222").put("138000000");
        try {
            person.put("phone",phone);
            person.put("name","hello name");
            person.put("age",100);

            JSONObject address = new JSONObject();
            address.put("country","china");
            address.put("province","beijing");
            person.put("address",address);
            person.put("married",false);

            System.out.println(person.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
