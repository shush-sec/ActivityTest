package com.example.goodsprice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shush on 2017/5/13.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;
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
}
