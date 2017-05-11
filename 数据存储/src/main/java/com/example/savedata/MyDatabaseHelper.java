package com.example.savedata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shush on 2017/5/11.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK = "create table Book(" +
                "id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer" +
                "name text)";
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "Create Book succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
