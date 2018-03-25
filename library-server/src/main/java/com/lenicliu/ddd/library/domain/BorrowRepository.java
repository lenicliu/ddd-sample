package com.lenicliu.ddd.library.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    Borrow findByIdentityAndIsbn(String identity, String isbn);

    List<Borrow> findByIdentity(String identity);
}
