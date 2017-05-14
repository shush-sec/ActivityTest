package com.example.goodsprice;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by shush on 2017/5/13.
 */

public class MyAdapter extends ArrayAdapter<Map<String,String>> {

    private int resourceId;
    private DBHelper dbHelper = new DBHelper(getContext(), "Goods", null, 1);;
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Map<String, String>> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            //1.获取view对象
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            //2.获取view对象中的控件对象
            final TextView  idTV = (TextView) convertView.findViewById(R.id.idTV);
            final TextView  nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            final TextView  balanceTV = (TextView) convertView.findViewById(R.id.balanceTV);

            //3.设置控件信息
            Map<String ,String> map =  getItem(position);
            idTV.setText(map.get("id"));
            nameTV.setText(map.get("name"));
            balanceTV.setText(map.get("price"));

            //4.设置按钮监听事件
            //4.1 删除按钮监听事件
            ImageView imageViewID = (ImageView) convertView.findViewById(R.id.deleteIV);
            imageViewID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.delGoods(idTV.getText().toString());
                }
            });
            //4.2增加金额监听事件
            ImageView imageViewUp = (ImageView) convertView.findViewById(R.id.upIV);
            imageViewUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int price = Integer.valueOf( balanceTV.getText().toString())+1;
                    dbHelper.upGoods(idTV.getText().toString(),price);

                }
            });
            //4.3减少金额监听事件
            ImageView imageViewDown = (ImageView) convertView.findViewById(R.id.downIV);
            imageViewDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int price = Integer.valueOf( balanceTV.getText().toString())-1;
                    dbHelper.upGoods(idTV.getText().toString(),price);
                }
            });
        }

       return convertView;
    }
}
