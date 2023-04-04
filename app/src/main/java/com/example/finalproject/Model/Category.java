package com.example.finalproject.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Category {
    private int id;
    private String type;
    private String name;
    private String color;
    private String main_image;
    private String description;
    private ArrayList<Category> subcategories;
    public boolean isChecked = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategory(ArrayList<Category> subcategories) {
        this.subcategories = subcategories;
    }
}
