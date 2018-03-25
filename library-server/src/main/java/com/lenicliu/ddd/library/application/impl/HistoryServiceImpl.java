package com.lenicliu.ddd.library.application.impl;

import com.lenicliu.ddd.library.application.HistoryService;
import com.lenicliu.ddd.library.domain.History;
import com.lenicliu.ddd.library.domain.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<History> listByIsbn(String isbn) {
        return historyRepository.findByIsbnOrderByBorrowAtDesc(isbn);
    }

    @Override
    public List<History> listByUser(String identity) {
        return historyRepository.findByIdentityOrderByBorrowAtDesc(identity);
    }
}