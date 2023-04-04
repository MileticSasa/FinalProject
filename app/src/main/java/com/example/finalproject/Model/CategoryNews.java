package com.example.finalproject.Model;

import java.util.ArrayList;

public class CategoryNews { //ovo je objekat sa homepage pod nazivom category
    private int id;
    private String title;
    private String color;
    private ArrayList<NewsEvent> news;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<NewsEvent> getNews() {
        return news;
    }

    public void setNews(ArrayList<NewsEvent> news) {
        this.news = news;
    }
}
