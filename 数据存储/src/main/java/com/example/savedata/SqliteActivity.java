package com.example.savedata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SqliteActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
    }
    //创建数据库
    public void onClickCreateDB(View view) {

        dbHelper.getWritableDatabase();
    }
    //插入数据
    public void onClickInsertData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //插入一条数据
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.95);
        db.insert("Book",null,values);

        Toast.makeText(this, "插入数据成功！", Toast.LENGTH_SHORT).show();
    }
}
