package com.lenicliu.ddd.library.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@Configurable
public class Book {
    @Id
    private String isbn;
    private String name;
    private Integer stock;
    @Version
    private int version;

    @Autowired
    private transient BookRepository bookRepository;

    public void decrease(int stock) {
        if (getStock() - stock < 0) {
            throw new RuntimeException(String.format("book %s has only %d stock", getIsbn(), getStock()));
        }
        setStock(getStock() - stock);
        bookRepository.save(this);
    }

    public void increase(int stock) {
        setStock(getStock() + stock);
        bookRepository.save(this);
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}