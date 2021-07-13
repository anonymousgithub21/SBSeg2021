package com.tcc2.bke_auth4isp.entity;

public class Client extends Person {

    String url_photo;

    public Client(String name, String cpf, String username, long rg, String phone, String url_photo) {
        super(name, cpf, username, rg, phone);
        this.url_photo = url_photo;
    }

    public Client(){}

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

}
