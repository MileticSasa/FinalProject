package com.example.finalproject.Model;

import java.util.ArrayList;

public class Comment {

    public int negative_votes;
    public int positive_votes;
    public int news;
    public String created_at;
    public String name;
    public int parent_comment;
    public int id;
    public String content;
    public ArrayList<Comment> children;

    public boolean voted;
}
