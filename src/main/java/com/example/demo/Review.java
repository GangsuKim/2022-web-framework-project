package com.example.demo;

public class Review {
    private String room_id, r_value, writer;
    private int r_id, star;
    
    public String getRoom_id() {
        return room_id;
    }
    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
    public String getR_value() {
        return r_value;
    }
    public void setR_value(String r_value) {
        this.r_value = r_value;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public int getR_id() {
        return r_id;
    }
    public void setR_id(int r_id) {
        this.r_id = r_id;
    }
    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
    
    @Override
    public String toString() {
        return "Review [room_id=" + room_id + ", r_value=" + r_value + ", writer=" + writer + ", r_id=" + r_id
                + ", star=" + star + "]";
    }
}
