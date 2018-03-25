package com.lenicliu.ddd.library.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

    List<Book> findByNameContaining(String name);
}