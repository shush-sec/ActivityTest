package com.example.savedata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClickSet(View view) {

        EditText editText = (EditText) findViewById(R.id.etUserName);
        String name = editText.getText().toString();
        //1.获取SharedPreferences.Editor
        SharedPreferences.Editor editor =
                getSharedPreferences("data",MODE_PRIVATE).edit();
        //2.填充输入进去
        editor.putString("name",name);
        //3.apply提交
        editor.apply();
    }

    public void onClickGet(View view) {
        //1.获取SharePreferences
        SharedPreferences sharedPreferences  =
                getSharedPreferences("data",MODE_PRIVATE);
        //2.获取数据
        String name = sharedPreferences.getString("name","");

        EditText editText = (EditText) findViewById(R.id.etUserName);
        editText.setText(name);
    }
}
