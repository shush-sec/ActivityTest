package com.example.goodsprice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper = new DBHelper(this, "Goods", null, 1);;
    private List<Map<String, String>> goodsList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.accountLV);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                refreshUI();

            }
        });

        //初始化商品列表
        getGoodsList();

    }

    //获取商品价格列表 并显示到ListView中
    private void getGoodsList() {
        goodsList = dbHelper.selectGoods();

        if (goodsList.size() == 0){
            listView.setAdapter(null);
        }else   {
            //填充数据到ListView
            MyAdapter myAdapter = new MyAdapter(MainActivity.this
                    , R.layout.price_item
                    , goodsList);

            listView.setAdapter(myAdapter);
        }


    }

    //添加商品价格
    public void add(View view) {
        EditText editText = (EditText) findViewById(R.id.nameET);
        EditText editText1 = (EditText) findViewById(R.id.balanceET);
        String goodsName = editText.getText().toString();
        String price = editText1.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("name", goodsName);
        map.put("price", price);
        //执行添加
        dbHelper.addGoods(map);

        //刷新界面
        refreshUI();
    }
    //刷新界面
    public void refreshUI() {
        clearGoodsList();
        getGoodsList();
    }

    //清空原来的goodsList
    private void clearGoodsList() {
        goodsList.clear();
        listView.setAdapter(null);
    }



}
