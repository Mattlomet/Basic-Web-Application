package com.company.MattLometU1M5Summative.controller;

import com.company.MattLometU1M5Summative.dao.AuthorDaoImpl;
import com.company.MattLometU1M5Summative.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    // thought we needed these but i believe after reading the instructions we do not
    // they do work tho

    @Autowired
    AuthorDaoImpl authorDao;

    @GetMapping("/id/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorDao.getAuthor(id);
    }

    @GetMapping("/all")
    public List<Author> getAllAuthor(){
        return authorDao.getAllAuthors();
    }

    @PostMapping("/create")
    public Author createAuthor(@RequestBody Author author){
        return authorDao.addAuthor(author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorDao.deleteAuthor(id);
    }


}
