package com.spribe.apis.suites.admin;

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

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Create users")
@Feature("Create different type of users by Admin role")
public class CreateUserApisTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(CreateUserApisTests.class);

    @Test
    @Description("Check that Admin is able to create new User and check response body.")
    public void createNewValidUserResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull(),
                "Field 'id' should not be empty.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertEquals(response.jsonPath().getInt("age"), userData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("gender").isJsonNull(),
                "Field 'gender' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("gender"), userData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("login").isJsonNull(),
                "Field 'login' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("login"), userData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("password"), userData.getPassword(),
                "Field 'password' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("role").isJsonNull(),
                "Field 'role' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("role"), userData.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("screenName").isJsonNull(),
                "Field 'screenName' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("screenName"), userData.getScreenName(),
                "Field 'screenName' doesn't match.");

        //Or it could be verified with Hamcrest also it can be covered with soft asserts at least

        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue())
                .body("age", equalTo(userData.getAge()))
                .body("gender", equalTo(userData.getGender().getGenderPropertyText()))
                .body("login", equalTo(userData.getLogin()))
                .body("password", equalTo(userData.getPassword()))
                .body("role", equalTo(userData.getRole().getRolePropertyText()))
                .body("screenName", equalTo(userData.getScreenName()));
    }

    @Test
    @Description("Check that Admin is able to create new User and user can be found in all users list.")
    public void createNewValidUserGetUserValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
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

        Assert.assertTrue(!response.asString().isEmpty() &&
                        JsonParser.parseString(response.asString()).getAsJsonObject().has("id"),
                "User 'id' should not be null or empty.");
        int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
        if (response.statusCode() == 200) {
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Response getUsersResponse = playerControllerUsersApi.getAllUsersData();
        List<Integer> actualIds = getUsersResponse.jsonPath().getList("players.id");

        Assert.assertNotNull(actualIds, "Array 'players' or field 'id' should not be null.");
        Assert.assertFalse(actualIds.isEmpty(), "Array 'players' should not be empty.");
        Assert.assertTrue(
                actualIds.contains(id),
                "Object with id=" + id + " hasn't been found in the Players list."
        );
    }

    @Test(enabled = false)
    //TODO It is not clear from requirements if Admin user can create new Admin user
    @Description("Check that Admin is not able to create new Admin user and check response body.")
    public void createNewValidAdminResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
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

    @Test(enabled = false)
    //TODO It is not clear from requirements if Admin user can create new Admin user
    @Description("Check that Admin is able to create new Admin user and user can be found in all users list.")
    public void createNewValidAdminGetUserValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
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

        Assert.assertTrue(!response.asString().isEmpty() &&
                        JsonParser.parseString(response.asString()).getAsJsonObject().has("id"),
                "User 'id' should not be null or empty.");
        int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
        if (response.statusCode() == 200) {
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Response getUsersResponse = playerControllerUsersApi.getAllUsersData();
        List<Integer> actualIds = getUsersResponse.jsonPath().getList("players.id");

        Assert.assertNotNull(actualIds, "Array 'players' or field 'id' should not be null.");
        Assert.assertFalse(actualIds.isEmpty(), "Array 'players' should not be empty.");
        Assert.assertTrue(
                actualIds.contains(id),
                "Object with id=" + id + " hasn't been found in the Players list."
        );
    }

    @Test
    @Description("Check that Admin is not able to create new Supervisor user.")
    public void createNewValidSupervisorResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
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
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

}
