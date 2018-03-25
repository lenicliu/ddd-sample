package com.lenicliu.ddd.library.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Configurable
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identity, isbn;
    @Column(name = "borrow_at")
    private Date borrowAt;
    @Column(name = "return_at")
    private Date returnAt;

    @Autowired
    private transient HistoryRepository historyRepository;
    @Autowired
    private transient UserRepository userRepository;
    @Autowired
    private transient BookRepository bookRepository;

    public User getUser() {
        return userRepository.findById(getIdentity()).get();
    }

    public Book getBook() {
        return bookRepository.findById(getIsbn()).get();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(Date borrowAt) {
        this.borrowAt = borrowAt;
    }

    public Date getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(Date returnAt) {
        this.returnAt = returnAt;
    }
}
