package com.lenicliu.ddd.library.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Long> {

    List<History> findByIsbnOrderByBorrowAtDesc(String isbn);

    List<History> findByIdentityOrderByBorrowAtDesc(String identity);
}