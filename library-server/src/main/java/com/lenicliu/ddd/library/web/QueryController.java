package com.lenicliu.ddd.library.web;

import com.lenicliu.ddd.library.application.BookService;
import com.lenicliu.ddd.library.application.BorrowService;
import com.lenicliu.ddd.library.application.HistoryService;
import com.lenicliu.ddd.library.domain.Book;
import com.lenicliu.ddd.library.domain.Borrow;
import com.lenicliu.ddd.library.domain.History;
import com.lenicliu.ddd.library.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QueryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private Library library;

    @GetMapping("/library")
    public Library library() {
        return library;
    }

    @GetMapping("/books")
    public List<Book> books(@RequestParam(value = "name", required = false) String name) {
        return bookService.findByName(name);
    }

    @GetMapping("/books/{isbn}")
    public Book booksByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    @GetMapping("/histories")
    public List<History> histories(
        @RequestParam(value = "isbn", required = false) String isbn,
        @RequestParam(value = "identity", required = false) String identity) {
        if (isbn != null) {
            return historyService.listByIsbn(isbn);
        } else if (identity != null) {
            return historyService.listByUser(identity);
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/borrows/{identity}")
    public List<Borrow> borrows(@PathVariable("identity") String identity) {
        return borrowService.listByUser(identity);
    }
}