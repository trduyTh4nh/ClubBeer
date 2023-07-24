package com.example.project_ver1.model;

import com.example.project_ver1.class_model.User;

import java.sql.SQLException;

public class LoginDetails {
    private String email;
    private String password;
    private String role;
    private DB db;
    public static final LoginDetails INSTANCE = new LoginDetails();
    public LoginDetails(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getID() throws SQLException {
        db = new DB();
        User u = db.getUserByEmail(email);
        return u.getId();
    }
}
