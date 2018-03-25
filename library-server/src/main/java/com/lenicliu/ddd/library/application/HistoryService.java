package com.lenicliu.ddd.library.application;

import com.lenicliu.ddd.library.domain.History;

import java.util.List;

public interface HistoryService {
    List<History> listByIsbn(String isbn);

    List<History> listByUser(String identity);
}