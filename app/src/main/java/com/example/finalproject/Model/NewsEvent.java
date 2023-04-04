package com.example.finalproject.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class NewsEvent implements Parcelable {
    protected int id;
    protected String image;
    protected String image_source;
    protected String author_name;
    protected String source;
    protected Category category;
    protected String title;
    protected String description;
    protected int comment_enabled;
    protected int comments_count;
    protected int shares_count;
    protected String created_at;
    protected String url;
    protected String click_type;


    protected NewsEvent(Parcel in) {
        id = in.readInt();
        image = in.readString();
        image_source = in.readString();
        author_name = in.readString();
        source = in.readString();
        title = in.readString();
        description = in.readString();
        comment_enabled = in.readInt();
        comments_count = in.readInt();
        shares_count = in.readInt();
        created_at = in.readString();
        url = in.readString();
        click_type = in.readString();
    }

    public static final Creator<NewsEvent> CREATOR = new Creator<NewsEvent>() {
        @Override
        public NewsEvent createFromParcel(Parcel in) {
            return new NewsEvent(in);
        }

        @Override
        public NewsEvent[] newArray(int size) {
            return new NewsEvent[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int isComment_enabled() {
        return comment_enabled;
    }

    public void setComment_enabled(int comment_enabled) {
        this.comment_enabled = comment_enabled;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getShares_count() {
        return shares_count;
    }

    public void setShares_count(int shares_count) {
        this.shares_count = shares_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClick_type() {
        return click_type;
    }

    public void setClick_type(String click_type) {
        this.click_type = click_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(image);
        parcel.writeString(image_source);
        parcel.writeString(author_name);
        parcel.writeString(source);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeInt(comment_enabled);
        parcel.writeInt(comments_count);
        parcel.writeInt(shares_count);
        parcel.writeString(created_at);
        parcel.writeString(url);
        parcel.writeString(click_type);
    }
}
