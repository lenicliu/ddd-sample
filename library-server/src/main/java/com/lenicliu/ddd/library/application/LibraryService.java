package com.lenicliu.ddd.library.application;

import com.lenicliu.ddd.library.domain.Book;

public interface LibraryService {

    Book newBook(String isbn, String name, int stock);

    Book addStock(String isbn, int stock);
}
