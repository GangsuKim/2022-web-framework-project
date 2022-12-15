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

import com.example.db.RoomDB;
import com.example.resources.UserInfo;

import jakarta.annotation.Resource;

@Controller
public class RoomController {
    @Autowired
    private RoomDB roomdb;

    @Resource
    private UserInfo userInfo;

    @PostMapping(value = "/room/register")
    public void register(@ModelAttribute Room room, Model model) throws NoSuchAlgorithmException{
        room.setId(encrypt(room.getId())); // SHA256으로 변환하여 저장
        if(userInfo.isLogined()) {
            roomdb.create(room);
        }
    }

    @GetMapping(value = "/room/create")
    public String create(@RequestParam(value = "id", required = true, defaultValue = "-1") String id, Model model) throws NoSuchAlgorithmException{
        if(roomdb.select(encrypt(id)) != null) {
            model.addAttribute("addres", id);
            return "/room/create";
        }
        return "/room/create";
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
