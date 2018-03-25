package com.lenicliu.ddd.library;

import com.lenicliu.ddd.library.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    private Library library;

    /**
     * <pre>
     *     curl http://localhost:8080/books
     *     curl http://localhost:8080/books/978-7-115-39249-7
     *     curl http://localhost:8080/histories?identity=6xxxxx1987xxxx1234
     *     curl http://localhost:8080/histories?isbn=978-7-115-39249-7
     *     curl -X POST -d 'identity=6xxxxx1987xxxx1234&isbn=978-7-115-39249-7' http://localhost:8080/borrow
     *     curl -X POST -d 'identity=6xxxxx1987xxxx1234&isbn=978-7-115-39249-7' http://localhost:8080/return
     *
     *     curl -X POST -d 'identity=6xxxxx1990xxxx4321&username=90`s' http://localhost:8080/register
     *     curl -X POST -d 'identity=6xxxxx1987xxxx1234&isbn=978-7-115-39249-7' http://localhost:8080/borrow
     *     curl -X POST -d 'identity=6xxxxx1987xxxx1234&isbn=978-7-115-39249-7' http://localhost:8080/return
     *     curl -X POST -d 'identity=6xxxxx1990xxxx4321&isbn=978-7-115-39249-7' http://localhost:8080/borrow
     *     curl -X POST -d 'identity=6xxxxx1990xxxx4321&isbn=978-7-115-39249-7' http://localhost:8080/return
     * </pre>
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        library.register("lenicliu", "6xxxxx1987xxxx1234");
        library.newBook("978-7-115-39249-7", "Computing Ads", 2);
    }
}