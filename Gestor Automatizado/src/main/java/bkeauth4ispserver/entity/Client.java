package bkeauth4ispserver.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author anonymous
 */
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
