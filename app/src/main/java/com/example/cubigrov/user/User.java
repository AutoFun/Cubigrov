package com.example.cubigrov.user;

import java.io.Serializable;
/**
 * by hagnji{
 *  User object
 *  getter and setter methods
 *  toString method
 * }
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String gender) {
        super();
        this.username = username;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + "]";
    }

}
