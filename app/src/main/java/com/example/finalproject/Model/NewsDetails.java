package com.example.finalproject.Model;

import android.os.Parcel;

import java.util.ArrayList;

public class NewsDetails extends NewsEvent{

    private ArrayList<Tag> tags;
    private ArrayList<NewsEvent> related_news;
    private ArrayList<NewsEvent> category_news;
    private ArrayList<NewsEvent> comments_top_n;

    protected NewsDetails(Parcel in) {
        super(in);
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<NewsEvent> getRelated_news() {
        return related_news;
    }

    public void setRelated_news(ArrayList<NewsEvent> related_news) {
        this.related_news = related_news;
    }

    public ArrayList<NewsEvent> getCategory_news() {
        return category_news;
    }

    public void setCategory_news(ArrayList<NewsEvent> category_news) {
        this.category_news = category_news;
    }

    public ArrayList<NewsEvent> getComments_top_n() {
        return comments_top_n;
    }

    public void setComments_top_n(ArrayList<NewsEvent> comments_top_n) {
        this.comments_top_n = comments_top_n;
    }
}
