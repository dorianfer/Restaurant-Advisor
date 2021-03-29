package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menus extends AppCompatActivity {

    private List<Menu> menus;
    private final String TAG = "Menus";
    private Menuliste menuliste;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        menus = new ArrayList<>();
        int restaurantId = getIntent().getIntExtra("restId", 0);
        this.menuliste = new Menuliste(getApplicationContext(), menus);
        this.listView = (ListView) findViewById(R.id.liste);
        this.listView.setAdapter(menuliste);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Menu menu = menus.get(position);

                startActivity(menu.getId(),menu.getName(),menu.getDescription(),menu.getPrice(),menu.getRestaurant_id());
            }
        });
        this.configureRetrofit();
        getMenuViaAPI(restaurantId);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private void startActivity(Integer restaurantId, String name, String description, Float price, int restaurant_id) {
        Intent intent = new Intent(this, MenuChoisi.class);
        intent.putExtra("restId", restaurantId);
        intent.putExtra("restName", name);
        intent.putExtra("restDescription", description);
        intent.putExtra("restPrice", price);
        intent.putExtra("restRestaurant_id", restaurant_id);
        startActivity(intent);
    }
    public void configureRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi MenuApi = retrofit.create(MenuApi.class);
    }
    private void getMenuViaAPI(int restaurant_Id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
        menuApi.getMenu(restaurant_Id).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                Log.d(TAG, "onResponse: ");

                List<Menu> menuList = response.body();
                if(menuList != null){
                    for(Menu menu: menuList) {
                        menus.add(menu);
                    }
                    menuliste.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse is empty: "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }
}