package com.tcc2.bke_auth4isp.entity;

public class Feedback {

    String name_techinician;
    String username_techinician;
    String username_client;
    String comments;
    float rating;

    public Feedback(String name_techinician, String username_techinician, String username_client, String comments, float rating) {
        this.name_techinician = name_techinician;
        this.username_techinician = username_techinician;
        this.username_client = username_client;
        this.comments = comments;
        this.rating = rating;
    }

    public Feedback(){}

    public String getName_techinician() {
        return name_techinician;
    }

    public void setName_techinician(String name_techinician) {
        this.name_techinician = name_techinician;
    }

    public String getUsername_techinician() {
        return username_techinician;
    }

    public void setUsername_techinician(String username_techinician) {
        this.username_techinician = username_techinician;
    }

    public String getUsername_client() {
        return username_client;
    }

    public void setUsername_client(String username_client) {
        this.username_client = username_client;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
