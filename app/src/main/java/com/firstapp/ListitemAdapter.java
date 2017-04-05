package com.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stgqq on 2017/3/29.
 */

public class ListitemAdapter extends ArrayAdapter<Listitem> {
    private int resourceId;
    public ListitemAdapter(Context context, int textViewResourceId, List<Listitem> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Listitem listitem = getItem(position);//获取当前项的Event实例
        View view ;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.listitemName = (TextView) view.findViewById(R.id.listitem_name);
            viewHolder.listitemImage = (ImageView) view.findViewById(R.id.listitem_image);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.listitemName.setText(listitem.getName());
        viewHolder.listitemImage.setImageResource(listitem.getImageId());
        return view;
    }
    class ViewHolder{
        TextView listitemName;
        ImageView listitemImage;
    }
}
