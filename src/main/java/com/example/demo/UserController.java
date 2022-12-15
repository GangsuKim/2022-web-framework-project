package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.db.UserDB;
import com.example.resources.UserInfo;

import jakarta.annotation.Resource;

@ComponentScan(basePackages = "com.example.*")
@Controller
public class UserController {

    // UserDB
    @Autowired
    private UserDB userdb;

    // Session 용도
    @Resource
    private UserInfo userInfo;

    // 로그인 페이지
    @GetMapping(value = "/login")
    public String login(Model model) {
        // 이미 로그인 된 경우 Index page로 return
        if (userInfo.isLogined()) {
            return "redirect:/";
        }
        return "/login/index";
    }

    @GetMapping(value = "/logout")
    public String logout(Model model) {
        System.out.println("[LOG] logout: " + userInfo.getUserName());
        userInfo.logout();
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping(value = "/register")
    public String register(Model model) {
        if (userInfo.isLogined()) {
            return "redirect:/";
        }
        return "/register/index";
    }

    // 회원가입 페이지
    @GetMapping(value = "/user")
    public String user(Model model) {
        if (!userInfo.isLogined()) {
            return "redirect:/login";
        }
        model.addAttribute("username", userInfo.getUserName());
        return "/user/user-info";
    }

    // 회원가입 완료 페이지
    @GetMapping(value = "/register/done")
    public String registered(Model model) {
        if (userInfo.isLogined()) {
            return "redirect:/";
        }
        return "/register/done";
    }

    // request 페이지
    // type = {'register' : '회원가입', 'login' : '로그인 '}
    @PostMapping(value = "/login/request.do")
    public String request(@ModelAttribute User usr, Model model,
            @RequestParam(value = "type", required = true) String type) {
        // Request type 분류
        if (type.equals("register")) { // 회원가입
            userdb.create(usr);
            return "redirect:/register/done";
        } else if (type.equals("login")) { // 로그인
            User selectUser = userdb.select(usr.getId());
            if (selectUser != null) {
                if (selectUser.getPasswd().equals(usr.getPasswd())) {
                    // passwd 가 일치
                    System.out.println("[LOG] login: " + selectUser.getUsername());
                    userInfo.login(selectUser.getId(), selectUser.getUsername());
                    return "redirect:/";
                }
            }
            return "redirect:/login?message=error";
        }
        return "redirect:/";
    }

    @GetMapping("session")
    public String session() {
        System.out.println(userInfo.toString());
        return "redirect:/";
    }
}
