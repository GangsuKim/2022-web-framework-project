package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.db.RoomDB;
import com.example.resources.UserInfo;

import jakarta.annotation.Resource;

@RestController
public class MetaRestController {
    @Autowired
    private RoomDB roomdb;

    @Resource
    private UserInfo userInfo;

    /**
     * 한글 주소명을 받아 JSON 객체의 DB 정보 반환
     * @param id 한글 형식의 주소명
     * @return JSON 형식의 반환값 
     * @throws NoSuchAlgorithmException SHA256 처리
     */

     @GetMapping(value = "/room/info/{id}")
     public Object getRoomInfo(@PathVariable("id") String id) throws NoSuchAlgorithmException {
         return roomdb.select(encrypt(id));
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
