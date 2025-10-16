package com.spribe.apis.suites.general;

import com.google.gson.JsonParser;
import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Get user")
@Feature("Get user API positive scenarios")
public class GetUserApiPositiveScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(GetUserApiPositiveScenariosTests.class);

    @Test
    @Description("Check that get user api returns valid data")
    public void getUserApiDataValidation() {
        User searchUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        logger.info("Search for user: " + searchUser);
        Response response = playerControllerUsersApi.getUserDataById(searchUser.getId());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("id"),
                "Field 'id' should not be empty.");
        Assert.assertEquals(response.jsonPath().getInt("id"), searchUser.getId(),
                "Field 'age' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("age"),
                "Field 'age' should not be null.");
        Assert.assertEquals(response.jsonPath().getInt("age"), searchUser.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("gender"),
                "Field 'gender' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("gender"), searchUser.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("login"),
                "Field 'login' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("login"), searchUser.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("password"),
                "Field 'password' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("password"), searchUser.getPassword(),
                "Field 'password' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("role"),
                "Field 'role' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("role"), searchUser.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertTrue(JsonParser.parseString(response.asString()).getAsJsonObject().has("screenName"),
                "Field 'screenName' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("screenName"), searchUser.getScreenName(),
                "Field 'screenName' doesn't match.");
    }
}
