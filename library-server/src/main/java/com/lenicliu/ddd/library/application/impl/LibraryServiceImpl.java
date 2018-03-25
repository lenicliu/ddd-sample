package com.lenicliu.ddd.library.application.impl;

import com.lenicliu.ddd.library.application.LibraryService;
import com.lenicliu.ddd.library.domain.Book;
import com.lenicliu.ddd.library.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private Library library;

    @Override
    public Book newBook(String isbn, String name, int stock) {
        return library.newBook(isbn, name, stock);
    }

    @Override
    public Book addStock(String isbn, int stock) {
        return library.addStock(isbn, stock);
    }
}