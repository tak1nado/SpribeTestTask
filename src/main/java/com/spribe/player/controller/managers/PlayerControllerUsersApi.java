package com.spribe.player.controller.managers;

import com.google.gson.JsonObject;
import com.spribe.player.controller.models.users.User;
import com.spribe.player.controller.models.users.UserData;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerControllerUsersApi {

    @Autowired private SpribeApi spribeApi;
    @Autowired private UsersManager usersManager;
    protected final Logger logger = LoggerFactory.getLogger(PlayerControllerUsersApi.class);

    @Step("Create new user {1} by editor {0}")
    public Response createNewUser(User editor, UserData newUser) {
        logger.info("Create new user request: by " + editor + ", new user: " + newUser);

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .queryParam("age", newUser.getAge())
                .queryParam("gender", newUser.getGender().getGenderPropertyText())
                .queryParam("login", newUser.getLogin())
                .queryParam("password", newUser.getPassword())
                .queryParam("role", newUser.getRole().getRolePropertyText())
                .queryParam("screenName", newUser.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create new user response: " + response.asString());

        return response;
    }

    @Step("Get user data/response by id {0}")
    public Response getUserDataById(int id) {
        logger.info("Get user request by: " + id);
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", id);

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .post("/player/get");

        logger.info("Get user response: " + response.asString());

        return response;
    }

    @Step("Get all users data/response")
    public Response getAllUsersData() {
        logger.info("Get all users request.");

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .get("/player/get/all");

        logger.info("Get all users response: " + response.asString());

        return response;
    }

    @Step("Delete user by id {1} by editor {0}")
    public Response deleteUserById(User editor, int id) {
        logger.info("Delete user request by: " + id);
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", id);

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", editor.getLogin()));

        logger.info("Delete user response: " + response.asString());

        return response;
    }

    @Step("Update user data {2} by id {1} by editor {0}")
    public Response updateUserById(User editor, int id, UserData userData) {
        logger.info(String.format("Update user request by id: %s, by editor: %s, user data: %s", id, editor.getLogin(), userData));
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", userData.getAge());
        jsonBody.addProperty("gender", userData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", userData.getLogin());
        jsonBody.addProperty("password", userData.getPassword());
//        jsonBody.addProperty("role", userData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", userData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), id));

        logger.info("Update user response: " + response.asString());

        return response;
    }

}
