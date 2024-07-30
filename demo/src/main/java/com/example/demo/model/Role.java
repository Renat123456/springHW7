package com.example.demo.model;

public enum Role {
    ADMIN("admin"), USER("user"), REST("rest");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}