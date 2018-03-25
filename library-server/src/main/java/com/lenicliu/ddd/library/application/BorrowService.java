package com.lenicliu.ddd.library.application;

import com.lenicliu.ddd.library.domain.Borrow;

import java.util.List;

public interface BorrowService {

    List<Borrow> listByUser(String identity);
}