package com.example.pethome;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    public String name, image, email, token, id;

    private String Name;
    private List<String> Likes;
    private String Address;
    private Integer Phone;

    private String UserType;

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getLikes() {
        return Likes;
    }

    public void addLikes(String input) {
        Likes.add(input);
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public User(String name, List<String> likes, String address, Integer phone, String userType) {
        Name = name;
        Likes = likes;
        Address = address;
        Phone = phone;
        UserType = userType;
    }

    public boolean likedBefore(String input){
        if(Likes.contains(input)){
            return true;
        }
        return false;
    }
}
