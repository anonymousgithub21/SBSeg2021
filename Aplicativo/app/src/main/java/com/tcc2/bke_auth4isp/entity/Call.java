package com.tcc2.bke_auth4isp.entity;

import java.io.Serializable;

public class Call implements Serializable  {

    String date;
    long calls_id;
    String name_technician;
    String url_photo;
    String username_technician;
    String username_client;

    public Call(String date, long calls_id, String name_technician, String url_photo, String username_technician, String username_client) {
        this.date = date;
        this.calls_id = calls_id;
        this.name_technician = name_technician;
        this.username_technician = username_technician;
        this.username_client = username_client;
    }

    public Call (){

    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCalls_id() {
        return calls_id;
    }

    public void setCalls_id(long calls_id) {
        this.calls_id = calls_id;
    }

    public String getName_technician() {
        return name_technician;
    }

    public void setName_technician(String name_technician) {
        this.name_technician = name_technician;
    }

    public String getUsername_technician() {
        return username_technician;
    }

    public void setUsername_technician(String username_technician) {
        this.username_technician = username_technician;
    }

    public String getUsername_client() {
        return username_client;
    }

    public void setUsername_client(String username_client) {
        this.username_client = username_client;
    }
}
