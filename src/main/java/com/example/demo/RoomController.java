package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.db.ReviewDB;
import com.example.db.RoomDB;
import com.example.resources.UserInfo;

import jakarta.annotation.Resource;

@Controller
public class RoomController {

    @Autowired
    private RoomDB roomdb;
    @Autowired
    private ReviewDB reviewdb;

    @Resource
    private UserInfo userInfo;

    @PostMapping(value = "/room/register")
    public String register(@ModelAttribute Room room, Model model) throws NoSuchAlgorithmException{
        room.setId(encrypt(room.getId())); // SHA256으로 변환하여 저장
        if(userInfo.isLogined()) {
            roomdb.create(room);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/room/create")
    public String create(@RequestParam(value = "id", required = true, defaultValue = "-1") String id, Model model) throws NoSuchAlgorithmException{
        if(!userInfo.isLogined()) {
            return "redirect:/?message=login";
        }

        if(roomdb.select(encrypt(id)) == null) {
            model.addAttribute("address", id);
            return "/room/create";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/room/review")
    public String review(
        @RequestParam(value = "id", required = true, defaultValue = "-1") String id,
        @RequestParam(value = "name", required = true, defaultValue = "-1") String name,
        Model model) {
        model.addAttribute("place_name", name);
        model.addAttribute("address", id);
        return "/room/review";
    }

    @PostMapping(value = "/room/review/register")
    public String review_register(
        @ModelAttribute Review review, 
        @RequestParam(value = "name", required = true, defaultValue = "-1") String name,
        Model model) throws NoSuchAlgorithmException {
        if(userInfo.isLogined()) {
            review.setWriter(userInfo.getUserName());
        } else {
            review.setWriter("익명");
        }

        if(roomdb.select(encrypt(review.getRoom_id())) == null) {
            Room r = new Room();
            r.setId(encrypt(review.getRoom_id()));
            r.setPlaceName(name);
            roomdb.create(r);
        }

        review.setRoom_id(encrypt(review.getRoom_id()));
        reviewdb.create(review);

        roomdb.updateAll(review.getRoom_id());

        return "redirect:/";
    }

    /**
     * SHA256 암호화 Script From : https://bamdule.tistory.com/233
     * @param text 암호화할 Text 문장
     * @return sha256 hash text
     * @throws NoSuchAlgorithmException
     */
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
