package com.company.MattLometU1M5Summative.dao;

import com.company.MattLometU1M5Summative.dto.Author;
import com.company.MattLometU1M5Summative.dto.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoImpl implements PublisherDao{
    private static final String INSERT_PUB_SQL =
            "insert into publisher (name, street, city, state, postal_code, phone, email) values ( ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PUB_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUB_SQL =
            "select * from publisher";

    private static final String DELETE_PUB_SQL =
            "delete from publisher where publisher_id = ?";

    private static final String UPDATE_PUb_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ?, where publisher_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher getPublisher(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_PUB_SQL, this::mapRowToPublisher, id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SELECT_ALL_PUB_SQL, this::mapRowToPublisher);

    }

    @Override
    @Transactional
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(INSERT_PUB_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        publisher.setPublisher_id(id);

        return publisher;
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUb_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getState(),
                publisher.getPostal_code(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisher_id());

    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUB_SQL,id);
    }


    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setPublisher_id(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostal_code(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));

        return publisher;
    }
}
