package com.maes.lionel.cars;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
//bron: https://www.youtube.com/channel/UCfQkNueQenRQQ1NnCBe6eQQ

@Entity
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}
