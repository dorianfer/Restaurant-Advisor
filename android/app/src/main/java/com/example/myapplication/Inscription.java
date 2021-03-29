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

public class Inscription extends AppCompatActivity {

    private final String TAG = "Inscription";
    private List<User> users;
    private EditText firstname;
    private EditText name;
    private EditText login;
    private EditText email;
    private EditText age;
    private EditText password;
    private EditText confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.firstname = (EditText) findViewById(R.id.editTextTextPersonName1);
        this.name = (EditText) findViewById(R.id.editTextTextPersonName2);
        this.login = (EditText) findViewById(R.id.editTextTextPersonName3);
        this.email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        this.age = (EditText) findViewById(R.id.editTextNumber);
        this.password = (EditText) findViewById(R.id.editTextTextPassword1);
        this.confirmpassword = (EditText) findViewById(R.id.editTextTextPassword2);
        users = new ArrayList<>();

        this.configureRetrofit();
        Button buttoninsc = (Button) findViewById(R.id.button);
        buttoninsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw = password.getText().toString();
                String cpsw = confirmpassword.getText().toString();
                if (psw.equals(cpsw)) {
                    User user = new User(login.getText().toString().trim(),
                            email.getText().toString().trim(),
                            firstname.getText().toString().trim(),
                            name.getText().toString().trim(),
                            Integer.valueOf(age.getText().toString().trim()),
                            password.getText().toString().trim());
                    createUser(user);
                } else {
                    startActivity2();
                }
            }
        });
    }

    private void startActivity() {
        Intent intent = new Intent(this, accueil.class);
        startActivity(intent);
    }

    private void startActivity2() {
        Intent intent = new Intent(this, Inscription.class);
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
    private void createUser(User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        userApi.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                startActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                startActivity2();
            }
        });
    }
}