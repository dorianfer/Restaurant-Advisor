package com.example.myapplication;

public class Menu {
    public Menu(String name, String description, float price, int restaurant_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant_id = restaurant_id;
    }
    private String name;
    private int id;
    private int restaurant_id;
    private String description;
    private float price;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public float getPrice(){
        return price;
    }
    public void setPrice(float price){
        this.price = price;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public int getRestaurant_id(){
        return restaurant_id;
    }
    public void setRestaurant_id(int restaurant_id){
        this.restaurant_id = restaurant_id;
    }
}
