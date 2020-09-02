package com.company.MattLometU1M5Summative.dao;

import com.company.MattLometU1M5Summative.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao{
    private static final String INSERT_AUTH_SQL =
            "insert into author (first_name, last_name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_AUTH_SQL =
            "select * from author where author_id = ?";

    private static final String SELECT_ALL_AUTH_SQL =
            "select * from author";

    private static final String DELETE_AUTH_SQL =
            "delete from author where author_id = ?";

    private static final String UPDATE_AUTHOR_SQL =
            "update author set first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ?, where author_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Author getAuthor(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_AUTH_SQL, this::mapRowToAuthor, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SELECT_ALL_AUTH_SQL, this::mapRowToAuthor);
    }

    @Override
    @Transactional
    public Author addAuthor(Author author) {
        jdbcTemplate.update(INSERT_AUTH_SQL,
                author.getFirst_name(),
                author.getLast_name(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostal_code(),
                author.getPhone(),
                author.getEmail());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        author.setAuthor_id(id);

        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                author.getFirst_name(),
                author.getLast_name(),
                author.getStreet(),
                author.getState(),
                author.getPostal_code(),
                author.getPhone(),
                author.getEmail(),
                author.getAuthor_id());
    }

    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTH_SQL,id);
    }

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException{
        Author author = new Author();
        author.setAuthor_id(rs.getInt("author_id"));
        author.setFirst_name(rs.getString("first_name"));
        author.setLast_name(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostal_code(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));

        return author;
    }
}
