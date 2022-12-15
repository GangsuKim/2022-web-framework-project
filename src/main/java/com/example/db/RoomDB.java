package com.example.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.Room;

@Component
public class RoomDB {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 원룸 정보 생성
    public void create(Room room) {
        String sql = "INSERT INTO room (id, place_name, avg_star, avg_price, avg_parking, review_cnt, recent_review) values (?, ?, 0.0, 0.0, 0.0, 0, null)";
        jdbcTemplate.update(sql, room.getId(), room.getPlaceName());
    }

    // 원룸 정보 조회
    public Room select(String id) {
        String sql = "SELECT * FROM room WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new RoomMapper(), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
