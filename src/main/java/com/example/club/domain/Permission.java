package com.example.club.domain;

public class Permission extends Account{
    private int id;
    private String permission;

    public int getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setId(int id) {
        this.id = id;
    }

}
