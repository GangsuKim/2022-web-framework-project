package com.example.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.User;

public class UserMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User usr = new User();

        usr.setId(rs.getString("id"));
        usr.setPasswd(rs.getString("passwd"));
        usr.setUsername(rs.getString("username"));
        usr.setName(rs.getString("name"));
        usr.setEmail(rs.getString("email"));
        usr.setAdmin(rs.getBoolean("admin"));

        return usr;
    }
}
