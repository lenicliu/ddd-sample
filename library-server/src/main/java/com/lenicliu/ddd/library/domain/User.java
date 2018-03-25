package com.lenicliu.ddd.library.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Configurable
public class User {
    @Id
    private String identity;
    private String username;

    @Autowired
    private transient Library library;

    public Borrow borrowBook(String isbn) {
        return library.borrowBook(getIdentity(), isbn);
    }

    public History returnBook(String isbn) {
        return library.returnBook(getIdentity(), isbn);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}