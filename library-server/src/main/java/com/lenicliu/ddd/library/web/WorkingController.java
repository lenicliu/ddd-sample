package com.lenicliu.ddd.library.web;

import com.lenicliu.ddd.library.application.LibraryService;
import com.lenicliu.ddd.library.application.UserService;
import com.lenicliu.ddd.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkingController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(String username, String identity) {
        return userService.register(username, identity);
    }

    @PostMapping("/borrow")
    public Borrow borrowBook(String identity, String isbn) {
        return userService.borrowBook(identity, isbn);
    }

    @PostMapping("/return")
    public History returnBook(String identity, String isbn) {
        return userService.returnBook(identity, isbn);
    }

    @PostMapping("/books")
    public Book book(String isbn, String name, int stock) {
        return libraryService.newBook(isbn, name, stock);
    }

    @PostMapping("/books/{isbn}/stock")
    public Book book(String isbn, int stock) {
        return libraryService.addStock(isbn, stock);
    }
}