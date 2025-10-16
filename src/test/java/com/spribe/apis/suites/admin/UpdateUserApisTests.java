package com.spribe.apis.suites.admin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import com.spribe.player.controller.models.users.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Update users")
@Feature("Update different type of users by Admin role")
public class UpdateUserApisTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(UpdateUserApisTests.class);

    @Test
    @Description("Check that Admin is able to update editable fields(ell except role) User and check response body.")
    public void updateUserResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull(),
                "Field 'id' Should not be empty.");
        Assert.assertEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("gender"), newUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().has("password"),
                "Field 'password' should not be displayed.");
        Assert.assertEquals(response.jsonPath().getString("role"), user.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

    @Test
    @Description("Check that Admin is able to update editable fields(ell except role) User and check get user data.")
    public void updateUserGetUserValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());

        Assert.assertEquals(getUserDataResponse.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("id").isJsonNull(),
                "Field 'id' Should not be empty.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("gender"), newUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("role"), user.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

    @Test
    @Description("Check that Admin is not able to update Admin and check response code.")
    public void updateAdminResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that Admin is not able to update Admin and check get user data.")
    public void updateAdminGetUserValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData initialUserData = user.getUsedDataCopy();
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());

        Assert.assertEquals(getUserDataResponse.jsonPath().getInt("id"), user.getId(),
                "Field 'id' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getInt("age"), initialUserData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("gender"), initialUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("login"), initialUserData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), initialUserData.getPassword(),
                "Field 'password' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("role"), initialUserData.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("screenName"), initialUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

    @Test
    @Description("Check that Admin is able to update editable fields(ell except role) itself and check response body.")
    public void updateAdminItselfResponseValidation() {
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", user.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull(),
                "Field 'id' Should not be empty.");
        Assert.assertEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("gender"), newUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().has("password"),
                "Field 'password' should not be displayed.");
        Assert.assertEquals(response.jsonPath().getString("role"), user.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertEquals(response.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

    @Test
    @Description("Check that Admin is able to update editable fields(ell except role) itself and check get user data.")
    public void updateAdminItselfGetUserValidation() {
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", user.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());

        Assert.assertEquals(getUserDataResponse.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("id").isJsonNull(),
                "Field 'id' Should not be empty.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("gender"), newUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("role"), user.getRole().getRolePropertyText(),
                "Field 'role' doesn't match.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

    @Test(enabled = false)
    @Description("Check that Admin is not able to update Supervisor and check response code.")
    public void updateSupervisorResponseValidation() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.ADMIN);
        //TODO there is no ability to create test Supervisor instance
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.SUPERVISOR);
        UserData newUserData = usersManager.generateRandomValidUserDataWithoutRole();

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Update user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

}
