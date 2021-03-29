package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connexion extends AppCompatActivity {

    private final String TAG = "Connexion";

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Button button = (Button) findViewById(R.id.button);
        this.email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        this.password = (EditText) findViewById(R.id.editTextTextPassword1);
        configureRetrofit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth auth = new Auth(email.getText().toString().trim(),
                        password.getText().toString().trim());
                connexionviaAPI(auth);
            }
        });
    }
    private void startActivity() {
        Intent intent = new Intent(this, accueil.class);
        startActivity(intent);
    }

    private void startActivity2() {
        Intent intent = new Intent(this, Connexion.class);
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
        UserApi userApi = retrofit.create(UserApi.class);
    }
    private void connexionviaAPI(Auth auth){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        userApi.connexion(auth).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    startActivity();
                } else {
                    startActivity2();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                startActivity2();
            }
        });
    }
}