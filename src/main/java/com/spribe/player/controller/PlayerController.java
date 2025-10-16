package com.spribe.player.controller;

public class PlayerController {
    private final String baseUrl;

    public PlayerController(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
