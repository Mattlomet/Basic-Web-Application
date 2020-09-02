package com.company.MattLometU1M5Summative.dao;

import com.company.MattLometU1M5Summative.dto.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthor(int id);
    List<Author> getAllAuthors();
    Author addAuthor(Author author);
    void updateAuthor(Author author);
    void deleteAuthor(int id);
}
