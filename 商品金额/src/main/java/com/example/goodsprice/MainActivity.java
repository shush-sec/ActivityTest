package com.example.goodsprice;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private List<Map<String, String>> goodsList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建Goods商品数据库
        dbHelper = new DBHelper(this, "Goods", null, 1);
        //获取商品列表
        getGoodsList();
        //监听删除按钮
        listView = (ListView) findViewById(R.id.accountLV);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View item_view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.price_item,null);


                Map<String,String> map = goodsList.get(position);
                String goodsId = (String)map.get("id");

                if (view.getId() == R.id.deleteIV){
                    Toast.makeText(MainActivity.this, "删除"+goodsId, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(MainActivity.this, goodsId, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //获取商品价格列表 并显示到ListView中
    private void getGoodsList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //创建游标对象
        Cursor cursor = db.query("prices", new String[]
                {"id", "name", "price"}, null, null, null, null, null);
        cursor.moveToFirst();
        do{
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("price", price);

            goodsList.add(map);
        }while (cursor.moveToNext());



        //填充数据到ListView
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this
                , goodsList
                , R.layout.price_item
                , new String[]{"id" ,"name","price"}
                , new int[]{R.id.idTV,R.id.nameTV,R.id.balanceTV});

        listView = (ListView) findViewById(R.id.accountLV);
        listView.setAdapter(simpleAdapter);
    }

    //添加商品价格
    public void add(View view) {
        EditText editText = (EditText) findViewById(R.id.nameET);
        EditText editText1 = (EditText) findViewById(R.id.balanceET);
        String goodsName = editText.getText().toString();
        String price = editText1.getText().toString();
        //创建ContentValues对象
        ContentValues values = new ContentValues();
        values.put("name", goodsName);
        values.put("price", price);
        //打开数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("prices", null, values);

        clearGoodsList();
        getGoodsList();

    }
    //清空原来的goodsList
    private void clearGoodsList(){
        goodsList.clear();
        listView.setAdapter(null);
    }

    //删除商品价格
    public void onDelClick(View view) {

    }
}
