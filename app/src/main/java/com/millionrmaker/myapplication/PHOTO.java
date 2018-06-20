package com.millionrmaker.myapplication;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "photo")
class PHOTO {

    PHOTO(){}


    @DatabaseField(generatedId = true)
    int Id;

    @DatabaseField
    String title;

    @DatabaseField
    String url;

    @DatabaseField
    String thumbnailUrl;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public PHOTO(String title, String url, String thumbnailUrl) {

        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
