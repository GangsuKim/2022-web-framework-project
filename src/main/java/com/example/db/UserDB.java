package com.example.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.User;

@Component
public class UserDB {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 사용자 생성
    public void create(User usr) {
        String sql = "INSERT INTO user (id, passwd, username, email, name, admin) values (?, ?, ?, ?, ?, 0)";
        jdbcTemplate.update(sql, usr.getId(), usr.getPasswd(), usr.getUsername(), usr.getEmail(), usr.getName());
    }

    // 사용자 조회
    public User select(String id) {
        String sql = "SELECT * FROM user WHERE id=?"; 
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
