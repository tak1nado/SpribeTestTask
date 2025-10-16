package com.spribe.apis.suites.user;

import com.google.gson.JsonParser;
import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import com.spribe.player.controller.models.users.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Create users")
@Feature("Create different type of users by User role")
public class CreateUserApisTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(CreateUserApisTests.class);

    @Test
    @Description("Check that User is not able to create new User and check response body.")
    public void createNewValidUserResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that User is not able to create new Admin and check response body.")
    public void createNewValidAdminResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.ADMIN);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that User is not able to create new Supervisor user.")
    public void createNewValidSupervisorResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.SUPERVISOR);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }
        //TODO Should be clarified which status code is expected 400 or 403
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

}
