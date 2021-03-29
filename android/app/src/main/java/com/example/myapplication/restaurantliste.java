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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class restaurantliste extends AppCompatActivity {

    private List<Restaurant> restaurants;
    private final String TAG = "restaurantliste";
    private restau1 restau1;
    private ListView listView;
    private Button buttonMaj;
    private EditText grade;
    private EditText name;
    private EditText description;
    private EditText localization;
    private EditText phone_number;
    private EditText website;
    private EditText hours;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantliste);
        this.buttonMaj = (Button) findViewById(R.id.button3);
        restaurants = new ArrayList<>();
        this.listView = (ListView) findViewById(R.id.liste);
        this.restau1 = new restau1(getApplicationContext(), restaurants);
        this.listView.setAdapter(restau1);
        this.restaurantId = getIntent().getIntExtra("restId", 0);
        String restaurantname = getIntent().getStringExtra("restName");
        String restaurantdescription = getIntent().getStringExtra("restDescription");
        float restaurantgrade = getIntent().getFloatExtra("restGrade", 0);
        String restauranthours = getIntent().getStringExtra("restHours");
        String restaurantlocalization = getIntent().getStringExtra("restLocalization");
        String restaurantphone = getIntent().getStringExtra("restPhone");
        String restaurantwebsite = getIntent().getStringExtra("restWebsite");

        this.name = (EditText) findViewById(R.id.editTextTextPersonName);
        this.description = (EditText) findViewById(R.id.editTextTextPersonName4);
        this.localization = (EditText) findViewById(R.id.editTextTextPersonName5);
        this.phone_number = (EditText) findViewById(R.id.editTextTextPersonName6);
        this.website = (EditText) findViewById(R.id.editTextTextPersonName7);
        this.hours = (EditText) findViewById(R.id.editTextTextPersonName8);
        this.grade = (EditText) findViewById(R.id.editTextNumber2);
        this.configureRetrofit();
        getRestaurantsViaAPI(restaurantId);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        buttonMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Restaurant restaurant = new Restaurant(name.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Float.valueOf(grade.getText().toString().trim()),
                        localization.getText().toString().trim(),
                        phone_number.getText().toString().trim(),
                        website.getText().toString().trim(),
                        hours.getText().toString().trim());
                MajRestau(restaurant, restaurantId);
            }
        });

        ImageButton buttonsupp = (ImageButton) findViewById(R.id.imageButton);
        buttonsupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(restaurantId);
            }
        });
        Button buttonmenus = (Button) findViewById(R.id.button5);
        buttonmenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity2(restaurantId);
            }
        });
    }
    private void startActivity2(Integer restaurantId) {
        Intent intent = new Intent(this, Menus.class);
        intent.putExtra("restId", restaurantId);
        startActivity(intent);
    }
    private void startActivity() {
        Intent intent = new Intent(this, accueil.class);
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
        RestaurantApi RestaurantApi = retrofit.create(RestaurantApi.class);
    }
    private void getRestaurantsViaAPI(int restaurantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.getRestaurant1(restaurantId).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Log.d(TAG, "onResponse: ");

                if(response.body() != null){
                    restaurants.add(response.body());
                    restau1.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse is empty: "+ response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage());

            }
        });
    }
    public void delete(int restaurantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.deleterestau(restaurantId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                }
                startActivity();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
    public void MajRestau(Restaurant restaurant, int restaurantId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.majrestau(restaurantId, restaurant).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                Log.d(TAG, "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
        Intent intent = new Intent(this, accueil.class);
        startActivity(intent);
    }
}