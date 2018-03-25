package com.lenicliu.ddd.library.application;

import com.lenicliu.ddd.library.domain.Borrow;
import com.lenicliu.ddd.library.domain.History;
import com.lenicliu.ddd.library.domain.User;

public interface UserService {
    User register(String username, String identity);

    Borrow borrowBook(String identity, String isbn);

    History returnBook(String identity, String isbn);
}
