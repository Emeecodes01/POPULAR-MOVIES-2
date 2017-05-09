package com.emma.mymovies.Models;


import com.google.gson.annotations.SerializedName;

public class VideoModel {

    @SerializedName("id")
    private String id;
    @SerializedName("iso_639_1")
    private String enType;
    @SerializedName("iso_3166_1")
    private String en;
    @SerializedName("key")
    private String Key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName( "size")
    private int size;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnType() {
        return enType;
    }

    public void setEnType(String enType) {
        this.enType = enType;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VideoModel(String id, String enType, String en, String key, String name, String site, int size, String type) {
        this.id = id;
        this.enType = enType;
        this.en = en;
        Key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }
}
