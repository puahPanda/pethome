package com.example.pethome;

public class Pet {
    private Integer Age;
    private String Breed;
    private String Gender;
    private String Name;
    private String Owner;
    private boolean Vaccine;

    public Pet() {
    }

    public Pet(Integer age, String breed, String gender, String name, String owner, boolean vaccine) {
        Age = age;
        Breed = breed;
        Gender = gender;
        Name = name;
        Owner = owner;
        Vaccine = vaccine;
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
}
