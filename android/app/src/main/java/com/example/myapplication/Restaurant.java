package com.example.myapplication;

public class Restaurant {


    //@SerializedName("name")
    //@Expose
    private String name;

    //@SerializedName("id")
    //@Expose
    private int id;

    //@SerializedName("description")
    //@Expose
    private String description;

    //@SerializedName("grade")
    //@Expose
    private float grade;

    //@SerializedName("localization")
    //@Expose
    private String localization;

    //@SerializedName("phone_number")
    //@Expose
    private String phone_number;

    //@SerializedName("website")
    //@Expose
    private String website;

    //@SerializedName("hours")
    //@Expose
    private String hours;

    public Restaurant(String name, String description, float grade, String localization, String phone_number, String website, String hours) {
        this.name = name;
        this.description = description;
        this.grade = grade;
        this.localization = localization;
        this.phone_number = phone_number;
        this.website = website;
        this.hours = hours;
    }

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
    public float getGrade(){
        return grade;
    }
    public void setGrade(float grade){
        this.grade = grade;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getLocalization(){
        return localization;
    }
    public void setLocalization(String localization){
        this.localization = localization;
    }

    public String getPhone(){
        return phone_number;
    }
    public void setPhone(String phone_number){
        this.phone_number = phone_number;
    }

    public String getHours(){
        return hours;
    }
    public void setHours(String hours){
        this.hours = hours;
    }

    public String getWebsite(){
        return website;
    }
    public void setWebsite(String website){
        this.website = website;
    }

}
