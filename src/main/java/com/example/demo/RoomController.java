package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.core.internal.resources.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/room/info/{id}")
    public String getRoomInfo(@PathVariable("id") String id, Model model) throws NoSuchAlgorithmException {
        Room selectedRoom = roomdb.select(encrypt(id));
        System.out.println(selectedRoom.toString());
        return "/room/info";
    }

    // Assets
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
