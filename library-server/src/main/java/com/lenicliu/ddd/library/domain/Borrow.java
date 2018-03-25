package com.lenicliu.ddd.library.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"identity", "isbn"})})
@Configurable
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identity, isbn;
    @Column(name = "borrow_at")
    private Date borrowAt;

    @Autowired
    private transient BorrowRepository borrowRepository;
    @Autowired
    private transient BookRepository bookRepository;
    @Autowired
    private transient UserRepository userRepository;
    @Autowired
    private transient HistoryRepository historyRepository;

    public History archive() {
        History history = new History();
        history.setIdentity(getIdentity());
        history.setIsbn(getIsbn());
        history.setBorrowAt(getBorrowAt());
        history.setReturnAt(new Date());
        historyRepository.save(history);
        borrowRepository.delete(this);
        return history;
    }

    public Book getBook() {
        return bookRepository.findById(getIsbn()).get();
    }

    public User getUser() {
        return userRepository.findById(getIdentity()).get();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(Date borrowAt) {
        this.borrowAt = borrowAt;
    }
}