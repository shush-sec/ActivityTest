package com.example.shush.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");

                    Toast.makeText(this, returnData, Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //activity被回收之前一定会调用
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something";
        outState.putString("data_key",tempData);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //取出onSaveInstanceState中保存的数据
        if (savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Toast.makeText(this, tempData, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
        startActivityForResult(intent ,1);


    }
}