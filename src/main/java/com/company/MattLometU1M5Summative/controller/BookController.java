package com.company.MattLometU1M5Summative.controller;

import com.company.MattLometU1M5Summative.dao.BookDaoImpl;
import com.company.MattLometU1M5Summative.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    // thought we needed these but i believe after reading the instructions we do not
    // they do work tho

    @Autowired
    BookDaoImpl bookDao;

    @GetMapping("/id/{id}")
    public Book getBook(@PathVariable int id){
        return bookDao.getBook(id);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book){
        return bookDao.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable int id){
        bookDao.deleteBook(id);
    }

    @GetMapping("/author/{id}")
    public void getBookByAuthorId(@PathVariable int id){
        bookDao.getBookByAuthorId(id);
    }
}
