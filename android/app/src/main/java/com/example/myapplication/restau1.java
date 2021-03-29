package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class restau1 extends BaseAdapter {
    private ViewGroup root;
    private Context context;
    private List<Restaurant> restaurantList;

    public restau1(Context context, List restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }
    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_restaulistview,null);
        }
        Restaurant restaurant = restaurantList.get(position);

        TextView textViewRestaurantName = (TextView) convertView.findViewById(R.id.restaurant_name);
        TextView textViewRestaurantGrade = (TextView) convertView.findViewById(R.id.restaurant_grade);
        TextView textViewRestaurantDescription = (TextView) convertView.findViewById(R.id.restaurant_description);
        TextView textViewRestaurantLocalization = (TextView) convertView.findViewById(R.id.restaurant_localization);
        TextView textViewRestaurantWebsite = (TextView) convertView.findViewById(R.id.restaurant_website);
        TextView textViewRestaurantHours = (TextView) convertView.findViewById(R.id.restaurant_hours);
        TextView textViewRestaurantPhone = (TextView) convertView.findViewById(R.id.restaurant_phone);

        textViewRestaurantName.setText(restaurant.getName());
        textViewRestaurantGrade.setText(String.valueOf(restaurant.getGrade()));
        textViewRestaurantDescription.setText(restaurant.getDescription());
        textViewRestaurantLocalization.setText(restaurant.getLocalization());
        textViewRestaurantWebsite.setText(restaurant.getWebsite());
        textViewRestaurantHours.setText(restaurant.getHours());
        textViewRestaurantPhone.setText(restaurant.getPhone());

        return convertView;
    }
}
