package com.lenicliu.ddd.library.application.impl;

import com.lenicliu.ddd.library.application.UserService;
import com.lenicliu.ddd.library.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private Library library;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String username, String identity) {
        return library.register(username, identity);
    }

    @Override
    public Borrow borrowBook(String identity, String isbn) {
        Optional<User> o = userRepository.findById(identity);
        if (o.isPresent()) {
            return o.get().borrowBook(isbn);
        } else {
            throw new RuntimeException(String.format("user not found. %s", identity));
        }
    }

    @Override
    public History returnBook(String identity, String isbn) {
        Optional<User> o = userRepository.findById(identity);
        if (o.isPresent()) {
            return o.get().returnBook(isbn);
        } else {
            throw new RuntimeException(String.format("user not found. %s", identity));
        }
    }
}
