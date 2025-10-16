package com.spribe.apis.suites.general;

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
@Feature("Update user API negative scenarios")
public class UpdateUserApiNegativeScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(UpdateUserApiNegativeScenariosTests.class);

    @Test
    @Description("Check that user can't be updated with age less than 16")
    public void updateUserWithAgeLessThan16() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(randomUtils.getRandomNumberInRange(1, 15));

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with age greater than 60")
    public void updateUserWithAgeGreaterThan60() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(randomUtils.getRandomNumberInRange(61, 120));

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with age 16")
    public void updateUserWithAge16() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(16);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with age 60")
    public void updateUserWithAge60() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setAge(60);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("age").isJsonNull(),
                "Field 'age' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getInt("age"), newUserData.getAge(),
                "Field 'age' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with already existed login value")
    public void updateUserWithAlreadyExistedLoginValue() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User existedUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setLogin(existedUser.getLogin());

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("login").isJsonNull(),
                "Field 'login' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getString("login"), newUserData.getLogin(),
                "Field 'login' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with already existed screenName value")
    public void updateUserWithAlreadyExistedScreenNameValue() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User existedUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        newUserData.setLogin(existedUser.getScreenName());

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("screenName").isJsonNull(),
                "Field 'screenName' should not be null.");
        Assert.assertNotEquals(response.jsonPath().getString("screenName"), newUserData.getScreenName(),
                "Field 'screenName' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with gender differs from 'male' or 'female'")
    public void updateUserWithInvalidGender() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidGender = randomUtils.generateRandomString();

        logger.info("Test body: " + newUserData);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("age", newUserData.getAge());
        jsonBody.addProperty("gender", invalidGender);
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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Assert.assertFalse(JsonParser.parseString(response.asString()).getAsJsonObject().get("gender").isJsonNull(),
                "Field 'gender' should not be null.");
        Assert.assertEquals(response.jsonPath().getString("gender"), invalidGender,
                "Field 'gender' should not match.");
    }

    @Test
    @Description("Check that user can't be update with password shorter than 6 symbols")
    public void updateUserWithPasswordShorterThan6Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(1, 5);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with password longer than 16 symbols")
    public void updateUserWithPasswordLongerThan16Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(17, 25);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with password length 6 symbols")
    public void updateUserWithPasswordLength6Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomAlphanumericString(6);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    @Test
    @Description("Check that user can't be created with password longer than 16 symbols")
    public void createNewUserWithPasswordLength16Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomAlphanumericString(16);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");

        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    @Test
    @Description("Check that user can't be updated with password that contains only numbers")
    public void updateUserWithPasswordThatContainsOnlyNumbers() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomNumericNumberOfSymbolsInRange(7, 15);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    @Test
    @Description("Check that user can't be created with password that contains only latin letters")
    public void createNewUserWithPasswordThatContainsOnlyLetters() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();
        String invalidPassword = randomUtils.generateRandomAlphabeticNumberOfSymbolsInRange(7, 15);
        newUserData.setPassword(invalidPassword);

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

        //TODO There are no clear requirements how system should behave if request contains invalid data
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
//                "Status code should be 400 (Bad Request).");
        Response getUserDataResponse = playerControllerUsersApi.getUserDataById(user.getId());
        Assert.assertFalse(JsonParser.parseString(getUserDataResponse.asString()).getAsJsonObject().get("password").isJsonNull(),
                "Field 'password' should not be null.");
        Assert.assertNotEquals(getUserDataResponse.jsonPath().getString("password"), newUserData.getPassword(),
                "Field 'password' should not match.");
    }

    // TODO Clarify requirement: Check that user can't be updated with password with specific symbols

    @Test
    @Description("Check that user can't be updated without editor parameter")
    public void updateUserWithoutEditorParameter() {
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();

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
                .patch(String.format("player/update//%s", user.getId()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        //TODO There are no clear requirements how system should behave if request contains invalid data
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be updated without id parameter")
    public void updateUserWithoutIdParameter() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User user = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);
        UserData newUserData = user.getUsedDataCopy();

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
                .patch(String.format("player/update/%s/", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200) {
            user.updateUserData(newUserData);
        }

        //TODO There are no clear requirements how system should behave if request contains invalid data
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }
}
