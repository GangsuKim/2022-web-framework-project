package com.example.db;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        String sql = "INSERT INTO rooms (id, place_name, avg_star, avg_price, avg_parking, review_cnt, recent_review) values (?, ?, 0.0, 0.0, 0.0, 0, null)";
        jdbcTemplate.update(sql, room.getId(), room.getPlaceName());
    }

    public void updateAll(String id) {
        int review_cnt = 0;
        double avg_star = 0.0;
        String countSql = "SELECT count(*) as cnt, avg(star) as avg_star FROM reviews WHERE room_id='" + id + "'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(countSql);

        Map<String, Object> map = list.get(0);

        review_cnt = (int) ((long) map.get("cnt"));
        avg_star = ((BigDecimal) map.get("avg_star")).doubleValue();

        String sql = "SELECT r_value FROM reviews WHERE room_id='" + id + "' ORDER BY r_id DESC LIMIT 1";
        list = jdbcTemplate.queryForList(sql);
        map = list.get(0);

        String r_value = (String) map.get("r_value");

        System.out.println(r_value + review_cnt + avg_star);

        sql = "UPDATE rooms SET avg_star = " + avg_star + ", recent_review = '" + r_value + "', review_cnt = " + review_cnt + " WHERE id = '" + id + "';";
        jdbcTemplate.update(sql);
    }

    // 원룸 정보 조회
    public Room select(String id) {
        String sql = "SELECT * FROM rooms WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new RoomMapper(), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
