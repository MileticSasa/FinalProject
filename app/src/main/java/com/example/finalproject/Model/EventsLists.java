package com.example.finalproject.Model;

import java.util.ArrayList;

public class EventsLists {
    private ArrayList<NewsEvent> slider;
    private ArrayList<NewsEvent> top;
    private ArrayList<CategoryNews> category;

    private ArrayList<NewsEvent> editors_choice;
    private ArrayList<NewsEvent> most_read;
    private ArrayList<NewsEvent> latest;
    private ArrayList<NewsEvent> most_comented;
    private ArrayList<NewsEvent> videos;

    public ArrayList<NewsEvent> getSlider() {
        return slider;
    }

    public void setSlider(ArrayList<NewsEvent> slider) {
        this.slider = slider;
    }

    public ArrayList<NewsEvent> getTop() {
        return top;
    }

    public void setTop(ArrayList<NewsEvent> top) {
        this.top = top;
    }

    public ArrayList<CategoryNews> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<CategoryNews> category) {
        this.category = category;
    }

    public ArrayList<NewsEvent> getEditors_choice() {
        return editors_choice;
    }

    public void setEditors_choice(ArrayList<NewsEvent> editors_choice) {
        this.editors_choice = editors_choice;
    }

    public ArrayList<NewsEvent> getMost_read() {
        return most_read;
    }

    public void setMost_read(ArrayList<NewsEvent> most_read) {
        this.most_read = most_read;
    }

    public ArrayList<NewsEvent> getLatest() {
        return latest;
    }

    public void setLatest(ArrayList<NewsEvent> latest) {
        this.latest = latest;
    }

    public ArrayList<NewsEvent> getMost_comented() {
        return most_comented;
    }

    public void setMost_comented(ArrayList<NewsEvent> most_comented) {
        this.most_comented = most_comented;
    }

    public ArrayList<NewsEvent> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<NewsEvent> videos) {
        this.videos = videos;
    }
}
