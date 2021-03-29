package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Menuliste extends BaseAdapter {
    private Context context;
    private List<Menu> menuList;

    public Menuliste(Context context, List menuList) {
        this.context = context;
        this.menuList = menuList;
    }
    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listviewmenu,null);
        }
        Menu menu = menuList.get(position);

        TextView textViewMenuName = (TextView) convertView.findViewById(R.id.menu_name);
        TextView textViewMenuPrice = (TextView) convertView.findViewById(R.id.menu_price);
        TextView textViewMenuDescription = (TextView) convertView.findViewById(R.id.menu_description);

        textViewMenuName.setText(menu.getName());
        textViewMenuPrice.setText(String.valueOf(menu.getPrice()));
        textViewMenuDescription.setText(menu.getDescription());

        return convertView;
    }
}
