package com.example.myapplication;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MenuApi {
    @GET("restaurants/{id}/menus")
    Call<List<Menu>> getMenu(@Path("id") int restaurant_id);

    @GET("menus/{id}")
    Call<Menu> getMenu1(@Path("id") int id);

    @DELETE("menus/{id}")
    Call<Void> deletemenu(@Path("id") int id);

    @PUT("menus/{id}")
    Call<Menu> majmenu(@Path("id") int id, @Body Menu menu);
}
