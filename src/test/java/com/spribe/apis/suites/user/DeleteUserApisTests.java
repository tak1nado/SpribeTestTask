package com.spribe.apis.suites.user;

import com.google.gson.JsonObject;
import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
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

import java.util.List;

@Epic("Delete users")
@Feature("Delete different type of users by User role")
public class DeleteUserApisTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(DeleteUserApisTests.class);

    @Test
    @Description("Check that User is not able to delete User and check response code.")
    public void deleteUserResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", editor.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that User is not able to delete User and user can be found in all users list.")
    public void deleteUserGetUserValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", editor.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Response getUsersResponse = playerControllerUsersApi.getAllUsersData();
        List<Integer> actualIds = getUsersResponse.jsonPath().getList("players.id");

        Assert.assertNotNull(actualIds, "Array 'players' or field 'id' should not be null.");
        Assert.assertTrue(
                actualIds.contains(user.getId()),
                "Object with id=" + user.getId() + " hasn't been found in the Players list but had not be deleted.");
    }

    @Test
    @Description("Check that User is not able to delete itself and check response code.")
    public void deleteUserItselfResponseValidation() {
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", user.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that User is not able to delete itself and user can be found in all users list.")
    public void deleteUserItselfGetUserValidation() {
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", user.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Response getUsersResponse = playerControllerUsersApi.getAllUsersData();
        List<Integer> actualIds = getUsersResponse.jsonPath().getList("players.id");

        Assert.assertNotNull(actualIds, "Array 'players' or field 'id' should not be null.");
        Assert.assertTrue(
                actualIds.contains(user.getId()),
                "Object with id=" + user.getId() + " hasn't been found in the Players list but had not be deleted.");
    }

    @Test
    @Description("Check that User is not able to delete Admin and check response code.")
    public void deleteAdminResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", editor.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

    @Test
    @Description("Check that User is not able to delete Admin and user can be found in all users list.")
    public void deleteAdminGetUserValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);

        logger.info("Test body: " + user);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("playerId", user.getId());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .delete(String.format("player/delete/%s", editor.getLogin()));

        logger.info("Delete user response: " + response.asString());

        if (response.statusCode() == 204 || response.statusCode() == 200) {
            usersManager.getAllUsers().remove(user);
        }

        Response getUsersResponse = playerControllerUsersApi.getAllUsersData();
        List<Integer> actualIds = getUsersResponse.jsonPath().getList("players.id");

        Assert.assertNotNull(actualIds, "Array 'players' or field 'id' should not be null.");
        Assert.assertTrue(
                actualIds.contains(user.getId()),
                "Object with id=" + user.getId() + " hasn't been found in the Players list but had not be deleted.");
    }

    @Test(enabled = false)
    @Description("Check that Admin is not able to delete Supervisor user.")
    public void deleteSupervisorResponseValidation() {
        User editor = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
//        TODO There is no possibility to create test Supervisor user with current apis
//        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.SUPERVISOR);

        logger.info("Test body: " + editor);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", editor.getAge())
                .queryParam("gender", editor.getGender().getGenderPropertyText())
                .queryParam("login", editor.getLogin())
                .queryParam("password", editor.getPassword())
                .queryParam("role", editor.getRole().getRolePropertyText())
                .queryParam("screenName", editor.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());
//        TODO
//        if (response.statusCode() == 204 || response.statusCode() == 200) {
//            usersManager.getAllUsers().remove(editor);
//        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN,
                "Status code should be 403 (Forbidden).");
    }

}
