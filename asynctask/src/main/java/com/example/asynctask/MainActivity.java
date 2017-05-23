package com.example.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    class MyAsyncTask extends AsyncTask<String,Integer,String>{

        //在execute方法内调用
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //异步调用
        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
