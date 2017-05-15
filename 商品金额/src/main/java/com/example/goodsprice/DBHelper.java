package com.example.goodsprice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by shush on 2017/5/13.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;
    private    SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table prices(" +
                "ID integer primary key autoincrement" +
                ",name nvarchar(20)" +
                ",price real)";

        db.execSQL(sql);

        Toast.makeText(mContext, "创建prices表成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addGoods(Map<String,String> map){
        //创建ContentValues对象
        ContentValues values = new ContentValues();
        values.put("name", map.get("name"));
        values.put("price", map.get("price"));
        //打开数据库
        db = getWritableDatabase();
        db.insert("prices", null, values);
    }

    public void delGoods(String id){
        db = getWritableDatabase();
        db.delete("prices","id=?",new String[]{id});
    }

    public void upGoods(String id,int price){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",price);
        db.update("prices",values,"id=?",new String[]{id});
    }

    public List<Map<String,String>> selectGoods(){
        List<Map<String,String>> listGoods = new ArrayList<>();

        db = getWritableDatabase();
        //创建游标对象
        Cursor cursor = db.query("prices", new String[]
                {"id", "name", "price"}, null, null, null, null, "id");

        if (cursor.getCount() == 0){
            return listGoods;
        }

        cursor.moveToFirst();
        do {
            String id = cursor.getString(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));

            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("price", price);

            listGoods.add(map);
        } while (cursor.moveToNext());
        cursor.close();
        return listGoods;
    }
}
