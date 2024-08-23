package com.kuruc.domain;

public class User {

    private long id;
    private String guId;
    private String name;

    public User() {
    }

    public User(long id, String guId, String name) {
        this.id = id;
        this.guId = guId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + guId + " " + name;
    }
}