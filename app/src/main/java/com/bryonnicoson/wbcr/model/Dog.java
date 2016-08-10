package com.bryonnicoson.wbcr.model;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryon on 8/10/16.
 */
public class Dog implements Serializable {

    public String id;
    public String shelterId;
    public String shelterPetId;
    public String name;
    public String animal;
    public String breed;
    public String mix;
    public String age;
    public String sex;
    public String size;
    public boolean hasShots;
    public boolean altered;
    public String description;
    public String lastUpdate;
    public String status;
    public List<String> images = new ArrayList<String>();

    public Dog(String id, String shelterId, String shelterPetId, String name, String animal,
               String breed, String mix, String age, String sex, String size, boolean hasShots,
               boolean altered, String description, String lastUpdate, String status,
               List<String> images) {
        this.id = id;
        this.shelterId = shelterId;
        this.shelterPetId = shelterPetId;
        this.name = name;
        this.animal = animal;
        this.breed = breed;
        this.mix = mix;
        this.age = age;
        this.sex = sex;
        this.size = size;
        this.hasShots = hasShots;
        this.altered = altered;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.images = images;
    }

    public ArrayList<Dog> dogMaker (JsonResponse jsonResponse) {
        ArrayList<Dog> dogs = new ArrayList<Dog>();

        List<Pet> petList = jsonResponse.petfinder.pets.pet;

        for (int i = 0; i < petList.size(); i++) {
            id = petList.get(i).id;
            shelterId = petList.get(i).shelterId;
            shelterPetId = petList.get(i).shelterPetId;
            name = petList.get(i).name;
            animal = petList.get(i).animal;

        }
        Dog dog = new Dog(id, shelterId, shelterPetId, name, animal, breed, mix, age, sex, size,
                hasShots, altered, description, lastUpdate, status, images);
        return dogs;
    }


}
