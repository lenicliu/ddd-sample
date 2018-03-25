package com.lenicliu.ddd.library.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@Configurable
public class Library {

    private final String name = "迷你图书馆";

    @Autowired
    private transient BookRepository bookRepository;
    @Autowired
    private transient BorrowRepository borrowRepository;
    @Autowired
    private transient UserRepository userRepository;

    /**
     * 读者注册
     *
     * @param username
     * @param identity
     * @return
     */
    public User register(String username, String identity) {
        Optional<User> found = userRepository.findById(identity);
        if (found.isPresent()) {
            throw new RuntimeException(String.format("%s has been registered.", identity));
        }
        User user = new User();
        user.setIdentity(identity);
        user.setUsername(username);
        userRepository.save(user);
        return user;
    }

    /**
     * 新书上架
     *
     * @param isbn
     * @param name
     * @param stock
     * @return
     */
    public Book newBook(String isbn, String name, int stock) {
        Optional<Book> found = bookRepository.findById(isbn);
        if (found.isPresent()) {
            throw new RuntimeException(String.format("%s(%s) has been added.", isbn, name));
        }
        Book book = new Book();
        book.setIsbn(isbn);
        book.setName(name);
        book.setStock(stock);
        bookRepository.save(book);
        return book;
    }

    /**
     * 增加库存
     *
     * @param isbn
     * @param stock
     * @return
     */
    public Book addStock(String isbn, int stock) {
        Optional<Book> optional = bookRepository.findById(isbn);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.increase(stock);
            return book;
        } else {
            throw new RuntimeException(String.format("book not found. %s", isbn));
        }
    }

    /**
     * 借书
     *
     * @param identity
     * @param isbn
     * @return
     */
    public Borrow borrowBook(String identity, String isbn) {
        Optional<Book> optional = bookRepository.findById(isbn);
        if (!optional.isPresent()) {
            throw new RuntimeException(String.format("book %s not found.", isbn));
        }

        Borrow found = borrowRepository.findByIdentityAndIsbn(identity, isbn);
        if (found != null) {
            throw new RuntimeException(String.format("%s has borrowed %s", identity, isbn));
        }

        Book book = optional.get();
        book.decrease(1);

        Borrow borrow = new Borrow();
        borrow.setIdentity(identity);
        borrow.setIsbn(isbn);
        borrow.setBorrowAt(new Date());
        borrowRepository.save(borrow);
        return borrow;
    }

    /**
     * 还书
     *
     * @param identity
     * @param isbn
     * @return
     */
    public History returnBook(String identity, String isbn) {
        Borrow borrow = borrowRepository.findByIdentityAndIsbn(identity, isbn);
        if (borrow == null) {
            throw new RuntimeException(String.format("borrow ticket not found, %s, %s", identity, isbn));
        }

        Optional<Book> optional = bookRepository.findById(isbn);
        if (!optional.isPresent()) {
            throw new RuntimeException(String.format("book %s not found.", isbn));
        }
        Book book = optional.get();
        book.increase(1);

        return borrow.archive();
    }

    public String getName() {
        return name;
    }
}