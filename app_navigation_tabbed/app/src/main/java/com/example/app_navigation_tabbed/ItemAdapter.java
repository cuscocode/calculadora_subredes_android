package com.example.app_navigation_tabbed;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    List<Item>items;
    Context context;
    LayoutInflater inflater;


    public ItemAdapter (List<Item> items,Context context){
        this.items=items;
        this.context=context;
        inflater=(LayoutInflater.from(context.getApplicationContext()));

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_row,parent,false);
            ImageView icon=(ImageView) convertView.findViewById(R.id.icon_item);
          //  TextView nombre=(TextView)convertView.findViewById(R.id.tv_nombre_item);
            icon.setImageResource(items.get(position).getIcon());
          //  nombre.setText(items.get(position).getNombre());

        }
        return convertView;
    }
}
