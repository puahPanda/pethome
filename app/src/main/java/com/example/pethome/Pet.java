package com.example.pethome;

public class Pet {

    private Integer Age;
    private String Breed;

    private String Type;
    private String Gender;
    private String Name;
    private String Owner;
    private boolean Vaccine;
    private String ImageUrl;


    public Pet() {

    }

    public Pet (String name, String imageUrl, String breed, String gender, Integer age, boolean vaccine){
        Vaccine = vaccine;
        Age = age;
        Name = name;
        ImageUrl = imageUrl;
        Breed = breed;
        Gender = gender;
    }
    public Pet(String type, Integer age, String breed, String gender, String name, String owner, boolean vaccine, String imageUrl) {
        Type = type;
        Age = age;
        Breed = breed;
        Gender = gender;
        Name = name;
        Owner = owner;
        Vaccine = vaccine;
        ImageUrl = imageUrl;

    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public boolean isVaccine() {
        return Vaccine;
    }

    public void setVaccine(boolean vaccine) {
        Vaccine = vaccine;
    }

    public String getImageUrl(){
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl){ ImageUrl = imageUrl; }
}
