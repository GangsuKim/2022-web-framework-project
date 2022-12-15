package com.example.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.Room;

public class RoomMapper implements RowMapper<Room>{
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();

        room.setId(rs.getString("id"));
        room.setPlaceName(rs.getString("place_name"));
        room.setAvg_star(rs.getDouble("avg_star"));
        room.setAvg_price(rs.getDouble("avg_price"));
        room.setAvg_parking(rs.getDouble("avg_parking"));
        room.setReview_cnt(rs.getInt("review_cnt"));
        room.setRecent_review(rs.getString("recent_review"));

        return room;
    }
}
