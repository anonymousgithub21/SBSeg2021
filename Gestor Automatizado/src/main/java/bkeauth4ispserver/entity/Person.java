package bkeauth4ispserver.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author anonymous
 */
public class Person implements Serializable {

    private String name;

    private String cpf;
    public String username;
    private long rg;
    private String phone;

    public Person(String name, String cpf, String username, long rg, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.username = username;
        this.rg = rg;
        this.phone = phone;
    }

    public Person(){

    };

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
