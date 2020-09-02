package com.company.MattLometU1M5Summative.dao;

import com.company.MattLometU1M5Summative.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?,?,?,?,?,?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOK_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ?, where book_id = ?";

    private static final String SELECT_BOOK_BY_AUTH =
            "select * from book where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBook(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOK_SQL, this::mapRowToBook);

    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        book.setBook_id(id);

        return book;
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublish_date(),
                book.getAuthor_id(),
                book.getTitle(),
                book.getPublisher_id(),
                book.getPrice(),
                book.getBook_id());
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL,id);
    }

    @Override
    public Book getBookByAuthorId(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_BOOK_BY_AUTH, this::mapRowToBook, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBook_id(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublish_date(rs.getDate("publish_date").toLocalDate());
        book.setAuthor_id(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisher_id(rs.getInt("publisher_id"));
        book.setPrice(rs.getDouble("price"));
        return book;
    }
}
