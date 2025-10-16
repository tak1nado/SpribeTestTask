package com.spribe.player.controller.managers;

import com.spribe.player.controller.PlayerController;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpribeApi {
    @Autowired private PlayerController playerController;

    public RequestSpecification playerControllerRequest() {
        return RestAssured.given().baseUri(playerController.getBaseUrl());
    }
    
}
