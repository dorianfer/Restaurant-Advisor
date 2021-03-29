package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("register")
    Call<User> createUser(@Body User user);

    @POST("auth")
    Call<Auth> connexion(@Body Auth auth);
}

