package com.example.contactstest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    List<String> contactList = new ArrayList<>();
    Map<String,String> contactMap = new HashMap<>() ;  //这个map用于传递contactId到DetailActivity
    String contactName = null;
    String contactId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver()
                .query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null, null);

        //获取联系人名列表
        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactName = cursor
                        .getString(cursor.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
               contactId= cursor
                        .getString(cursor.getColumnIndex
                                (ContactsContract.Contacts._ID));
                contactList.add(contactName);
                contactMap.put(contactName,contactId);
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , contactList);

        ListView listView = (ListView) findViewById(R.id.contacts_view);
        listView.setAdapter(adapter);
             //监听listView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailActvity.class);
                String contactID =  contactMap.get( contactList.get(position))  ;
                intent.putExtra("id", contactID);
                startActivity(intent);
            }
        });
    }

}
