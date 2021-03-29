package com.example.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantApi {
    @GET("restaurants")
    Call<List<Restaurant>> getRestaurants();

    @POST("restaurants")
    Call<Restaurant> createRest(@Body Restaurant restaurant);

    @GET("restaurants/{id}")
    Call<Restaurant> getRestaurant1(@Path("id") int id);

    @DELETE("restaurant/{id}")
    Call<Void> deleterestau(@Path("id") int id);

    @PUT("restaurant/{id}")
    Call<Restaurant> majrestau(@Path("id") int id, @Body Restaurant restaurant);
}
