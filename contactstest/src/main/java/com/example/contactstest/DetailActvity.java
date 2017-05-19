package com.example.contactstest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> numberList = new ArrayList<>();
        List<String> emailsList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actvity);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(name);

        //获取某联系人电话号码列表
        String number ;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                null, null);

        if (phones.getCount() > 0) {
             while (phones.moveToNext()){
                number = phones
                        .getString(phones.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.NUMBER));
                numberList.add(number);
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , numberList);

        ListView listView = (ListView) findViewById(R.id.list_number);
        listView.setAdapter(adapter);

        //获取某人的email
        String email;
        Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                null, null);
        if (emails.getCount() > 0) {
            while (emails.moveToNext()){
                email = emails
                        .getString(emails.getColumnIndex
                                (ContactsContract.CommonDataKinds.Email.DATA));
                emailsList.add(email);
            }
        }

        ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , emailsList);

        ListView listViewEmail = (ListView) findViewById(R.id.list_email);
        listViewEmail.setAdapter(emailAdapter);
    }
}
