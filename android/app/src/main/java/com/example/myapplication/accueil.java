package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class accueil extends AppCompatActivity {

    private final String TAG = "accueil";
    private MyListViewAdapter myListViewAdapter;
    private List<Restaurant> restaurants;
    private ListView listView;
    private RestaurantApi restaurantApi;
    private Retrofit retrofit;
    private Button button;
    private EditText grade;
    private EditText name;
    private EditText description;
    private EditText localization;
    private EditText phone_number;
    private EditText website;
    private EditText hours;
    ImageButton bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.ListView);
        this.button = (Button) findViewById(R.id.button3);
        this.grade = (EditText) findViewById(R.id.editTextNumber2);
        this.name = (EditText) findViewById(R.id.editTextTextPersonName);
        this.description = (EditText) findViewById(R.id.editTextTextPersonName4);
        this.localization = (EditText) findViewById(R.id.editTextTextPersonName5);
        this.phone_number = (EditText) findViewById(R.id.editTextTextPersonName6);
        this.website = (EditText) findViewById(R.id.editTextTextPersonName7);
        this.hours = (EditText) findViewById(R.id.editTextTextPersonName8);
        bt=(ImageButton) findViewById(R.id.imageButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareSub = "Share this app";
                String shareBody = "Come and check out this app ! It's the best for knowing near by restaurant, their menus and their schedules !\n";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });

        this.myListViewAdapter = new MyListViewAdapter(getApplicationContext(), restaurants);
        this.listView.setAdapter(myListViewAdapter);
        this.configureRetrofit();

        getRestaurantsViaAPI();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Restaurant restaurant = new Restaurant(name.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Float.valueOf(grade.getText().toString().trim()),
                        localization.getText().toString().trim(),
                        phone_number.getText().toString().trim(),
                        website.getText().toString().trim(),
                        hours.getText().toString().trim());
                createRestaurant(restaurant);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = restaurants.get(position);

                startActivity(restaurant.getId(),restaurant.getName(),restaurant.getDescription(),restaurant.getGrade(),restaurant.getHours(),restaurant.getLocalization(),restaurant.getPhone(),restaurant.getWebsite());
            }
        });

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
    private void startActivity(Integer restaurantId, String name, String description, Float grade, String hours, String localization, String phone, String website) {
        Intent intent = new Intent(this, restaurantliste.class);
        intent.putExtra("restId", restaurantId);
        intent.putExtra("restName", name);
        intent.putExtra("restDescription", description);
        intent.putExtra("restGrade", grade);
        intent.putExtra("restHours", hours);
        intent.putExtra("restLocalization", localization);
        intent.putExtra("restPhone", phone);
        intent.putExtra("restWebsite", website);
        startActivity(intent);
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
    private void startActivity2() {
        Intent intent = new Intent(this, accueil.class);
        startActivity(intent);
    }
    private void createRestaurant(Restaurant restaurant){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);

        restaurantApi.createRest(restaurant).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        startActivity2();
    }
}