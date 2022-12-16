package com.example.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.Review;


public class ReviewMapper implements RowMapper<Review>{
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();

        review.setR_id(rs.getInt("r_id"));
        review.setRoom_id(rs.getString("room_id"));
        review.setR_value(rs.getString("r_value"));
        review.setStar(rs.getInt("star"));
        review.setWriter(rs.getString("writer"));

        return review;
    }
}
