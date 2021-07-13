package com.tcc2.bke_auth4isp.entity;

public class Technician extends Person {

    String certified;
    float rating;

    int countFeedbacks;
    String url_photo;

    public Technician(String name, String cpf, String username, long rg, String phone, String url_photo, float rating, int countFeedbacks, String certified) {
        super(name, cpf, username, rg, phone);
        this.url_photo = url_photo;
        this.rating = rating;
        this.certified = certified;
        this.countFeedbacks = countFeedbacks;
    }

    public Technician (){}

    public int getCountFeedbacks() {
        return countFeedbacks;
    }

    public void setCountFeedbacks(int countFeedbacks) {
        this.countFeedbacks = countFeedbacks;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public String getCertified() {
        return certified;
    }

    public void setCertified(String certified) {
        this.certified = certified;
    }

}
