package com.lenicliu.ddd.library.application.impl;

import com.lenicliu.ddd.library.application.BookService;
import com.lenicliu.ddd.library.domain.Book;
import com.lenicliu.ddd.library.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findById(isbn).get();
    }

    @Override
    public List<Book> findByName(String name) {
        name = name == null ? "" : name.trim();
        return bookRepository.findByNameContaining(name);
    }
}