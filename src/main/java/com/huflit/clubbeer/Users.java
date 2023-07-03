package com.huflit.clubbeer;

public class Users {
    private int ID;
    private String name;
    private int age;
    private String email;
    private String password;
    private String phone;
    private int role;
    public Users(){

    }

    public Users(int ID, String name, int age, String email, String password, String phone, int role) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
