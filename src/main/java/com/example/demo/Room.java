package com.example.demo;

public class Room {
    private String id, placeName, recent_review;
    private double avg_star, avg_price, avg_parking;
    private int review_cnt;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getRecent_review() {
        return recent_review;
    }
    public void setRecent_review(String recent_review) {
        this.recent_review = recent_review;
    }
    public double getAvg_star() {
        return avg_star;
    }
    public void setAvg_star(double avg_star) {
        this.avg_star = avg_star;
    }
    public double getAvg_price() {
        return avg_price;
    }
    public void setAvg_price(double avg_price) {
        this.avg_price = avg_price;
    }
    public double getAvg_parking() {
        return avg_parking;
    }
    public void setAvg_parking(double avg_parking) {
        this.avg_parking = avg_parking;
    }
    public int getReview_cnt() {
        return review_cnt;
    }
    public void setReview_cnt(int review_cnt) {
        this.review_cnt = review_cnt;
    }
    
    @Override
    public String toString() {
        return "Room [id=" + id + ", placeName=" + placeName + ", recent_review=" + recent_review + ", avg_star="
                + avg_star + ", avg_price=" + avg_price + ", avg_parking=" + avg_parking + ", review_cnt=" + review_cnt
                + "]";
    }
}
