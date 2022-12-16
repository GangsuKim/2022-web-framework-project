package com.example.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.Review;
import com.example.resources.UserInfo;

import jakarta.annotation.Resource;

@Component
public class ReviewDB {
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserInfo userInfo;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 리뷰 정보 생성
    public void create(Review review) {
        String sql = "INSERT INTO reviews VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, review.getR_id(), review.getRoom_id(), review.getR_value(), review.getStar(), review.getWriter());
    }
}
