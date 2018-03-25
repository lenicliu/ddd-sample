package com.lenicliu.ddd.library.application.impl;

import com.lenicliu.ddd.library.application.BorrowService;
import com.lenicliu.ddd.library.domain.Borrow;
import com.lenicliu.ddd.library.domain.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Override
    public List<Borrow> listByUser(String identity) {
        return borrowRepository.findByIdentity(identity);
    }
}