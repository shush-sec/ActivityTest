package com.example.goodsprice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by shush on 2017/5/13.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            //1.获取view对象
            convertView = mInflater.inflate(R.layout.price_item,null);
            //2.获取view对象中的控件对象
            TextView  idTV = (TextView) convertView.findViewById(R.id.idTV);
            TextView  nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            TextView  balanceTV = (TextView) convertView.findViewById(R.id.balanceTV);

            //3.设置控件信息
            Map<String ,String> map = (Map<String, String>) getItem(position);
            idTV.setText(map.get("id"));
            nameTV.setText(map.get("name"));
            balanceTV.setText(map.get("price"));
        }

       return null;
    }
}
