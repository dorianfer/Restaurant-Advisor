package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class MenuChoisi extends AppCompatActivity {

    private List<Menu> menus;
    private final String TAG = "MenuChoisi";
    private Menu1 menu1;
    private ListView listView;
    private EditText price;
    private EditText name;
    private EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choisi);
        menus = new ArrayList<>();
        this.listView = (ListView) findViewById(R.id.liste);
        this.menu1 = new Menu1(getApplicationContext(), menus);
        this.listView.setAdapter(menu1);
        int menuId = getIntent().getIntExtra("restId", 0);
        int restaurantId = getIntent().getIntExtra("restRestaurant_id", 0);
        this.configureRetrofit();
        ImageButton buttonsupp = (ImageButton) findViewById(R.id.imageButton);
        buttonsupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(menuId);
            }
        });
        this.name = (EditText) findViewById(R.id.editTextTextPersonName);
        this.description = (EditText) findViewById(R.id.editTextTextPersonName4);
        this.price = (EditText) findViewById(R.id.editTextTextPersonName5);
        Button buttonMaj = (Button) findViewById(R.id.button3);
        buttonMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Menu menu = new Menu(name.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Float.valueOf(price.getText().toString().trim()),
                        restaurantId);
                MajMenu(menu, menuId);
            }
        });
        getMenuViaAPI(menuId);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public void configureRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
    }
    private void getMenuViaAPI(int restaurantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
        menuApi.getMenu1(restaurantId).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                Log.d(TAG, "onResponse: ");

                if(response.body() != null){
                    menus.add(response.body());
                    menu1.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse is empty: "+ response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

            }
        });
    }
    public void delete(int menuId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
        menuApi.deletemenu(menuId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                Intent intent = new Intent(MenuChoisi.this, accueil.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
    public void MajMenu(Menu menu, int restaurantId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
        menuApi.majmenu(restaurantId, menu).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {

                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        Intent intent = new Intent(this, accueil.class);
        startActivity(intent);
    }
}