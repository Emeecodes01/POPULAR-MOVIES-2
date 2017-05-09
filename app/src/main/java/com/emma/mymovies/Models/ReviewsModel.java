package com.emma.mymovies.Models;


import com.google.gson.annotations.SerializedName;

public class ReviewsModel {
    @SerializedName("id")
    private String Id;
    @SerializedName("author")
    private String Author;
    @SerializedName("content")
    private String Content;
    @SerializedName("url")
    private String URL;

    public ReviewsModel(String id, String author, String content, String URL) {
        Id = id;
        Author = author;
        Content = content;
        this.URL = URL;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
