package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shush on 2017/5/12.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter( Context context,   int textViewResourceId,  List<Fruit> objects) {
        super(context,  textViewResourceId, objects);

        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        //第一次加载
        if(convertView == null){
           view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        //非第一次加载
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        //给控件赋值
        Fruit fruit = getItem(position);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());

        return  view;
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
