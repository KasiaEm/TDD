package com.sda.db;

public class User {
    private String login;
    private String password;

    private int expPoints;

    public User(String login, String username) {
        this.login = login;
        this.password = username;
    }

    public User(String login, String password, int expPoints) {
        this.login = login;
        this.password = password;
        this.expPoints = expPoints;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }
}
