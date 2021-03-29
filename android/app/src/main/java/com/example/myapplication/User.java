package com.example.myapplication;

public class User {
    public User(String login, String email, String firstname, String name, int age, String password) {
        this.login = login;
        this.email = email;
        this.firstname = firstname;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    private String login;
    private String email;
    private String firstname;
    private String name;
    private int age;
    private String password;

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int name){
        this.age = age;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
