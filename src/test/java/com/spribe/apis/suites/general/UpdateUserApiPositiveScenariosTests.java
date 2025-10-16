package com.spribe.apis.suites.general;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.Gender;
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
@Feature("Update user API positive scenarios")
public class UpdateUserApiPositiveScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(UpdateUserApiPositiveScenariosTests.class);

    @Test
    @Description("Check that user can be updated with age 17")
    public void updateUserWithAge17() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(17);

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with age 59")
    public void updateUserWithAge59() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(59);

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with age in range 18-58")
    public void updateUserWithAgeInRange18To59() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(randomUtils.getRandomNumberInRange(18, 58));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with password length 7")
    public void updateUserWithPasswordLength7() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setPassword(randomUtils.generateRandomAlphanumeric(7));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with password length 15")
    public void updateUserWithPasswordLength15() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setPassword(randomUtils.generateRandomAlphanumeric(15));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with password length in range 8-14 symbols")
    public void updateUserWithPasswordLengthInRange8To14() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setPassword(randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(8, 14));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());
        jsonBody.addProperty("login", newUserData.getLogin());
        jsonBody.addProperty("password", newUserData.getPassword());
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());
        jsonBody.addProperty("screenName", newUserData.getScreenName());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with only age field")
    public void updateUserOnlyWithAgeField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(randomUtils.getRandomNumberInRange(17, 59));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with only gender field")
    public void updateUserOnlyWithGenderField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setGender(Gender.getRandomExcept(user.getGender()));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("gender", newUserData.getGender().getGenderPropertyText());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("gender").isJsonNull(),
                "Field 'gender' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("gender"), newUserData.getGender().getGenderPropertyText(),
                "Field 'gender' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with only login field")
    public void updateUserOnlyWithLoginField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setLogin(randomUtils.generateRandomLogin());

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("login", newUserData.getLogin());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("login").isJsonNull(),
                "Field 'login' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with only password field")
    public void updateUserOnlyWithPasswordField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setPassword(randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(7, 15));

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("password", newUserData.getPassword());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' doesn't match.");
    }

    //TODO Clarify requirement: Role can't be updated. But there are no explanations how exactly system should behave
    @Test
    @Description("Check that user role field can't be updated")
    public void updateUserRoleField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setRole(PlayerControllerUserRole.ADMIN);

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("role").isJsonNull(),
                "Field 'role' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("role"), PlayerControllerUserRole.USER.getRolePropertyText(),
                "Field 'role' doesn't match.");
    }

    @Test
    @Description("Check that user can be updated with only screenName field")
    public void updateUserOnlyWithScreenNameField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setScreenName(randomUtils.generateRandomScreenName());

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("role", newUserData.getRole().getRolePropertyText());

        Response response = spribeApi.playerControllerRequest()
                .contentType(ContentType.JSON)
                .body(jsonBody.toString())
                .patch(String.format("player/update/%s/%s", editor.getLogin(), user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK,
                "Status code should be 200 (OK).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("screenName").isJsonNull(),
                "Field 'screenName' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' doesn't match.");
    }

}
