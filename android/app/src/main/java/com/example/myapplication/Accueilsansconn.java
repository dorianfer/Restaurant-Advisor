package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
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


public class Accueilsansconn extends AppCompatActivity {
    private Object Button;
    private final String TAG = "Testt";
    private MyListViewAdapter myListViewAdapter;
    private List<Restaurant> restaurants;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueilsansconn);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.ListView);
        this.myListViewAdapter = new MyListViewAdapter(getApplicationContext(), restaurants);
        this.listView.setAdapter(myListViewAdapter);
        Button buttonconn = (Button) findViewById(R.id.button6);
        buttonconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueilsansconn.this, Connexion.class);
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueilsansconn.this, Inscription.class);
                startActivity(intent);
            }
        });
        this.configureRetrofit();

        getRestaurantsViaAPI();

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
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
    }

    private void getRestaurantsViaAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                Log.d(TAG, "onResponse: ");

                List<Restaurant> restaurantList = response.body();
                if(restaurantList != null){
                    for(Restaurant restaurant: restaurantList) {
                        restaurants.add(restaurant);
                    }
                    myListViewAdapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse is empty: "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }

}