package com.lenicliu.ddd.library.application;

import com.lenicliu.ddd.library.domain.Book;

import java.util.List;

public interface BookService {
    Book findByIsbn(String isbn);

    List<Book> findByName(String name);
}