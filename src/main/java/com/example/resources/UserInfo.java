package com.example.resources;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInfo {
    private static final long serialVersionUUD = 1L;
    private String id, userName;
    private boolean logined = false;

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
    }

    public static long getSerialversionuud() {
        return serialVersionUUD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void login(String id, String userName) {
        this.id = id;
        this.userName = userName;
        this.logined = true;
    }

    public void logout() {
        this.id = null;
        this.userName = null;
        this.logined = false;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", userName=" + userName + ", logined=" + logined + "]";
    }
}
