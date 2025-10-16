package com.spribe.apis.suites.general;

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
@Feature("Create user API positive scenarios")
public class CreateUserApiPositiveScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(CreateUserApiPositiveScenariosTests.class);

    @Test
    @Description("Check that user can be created with age 17")
    public void createNewUserWithAge17() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(17);

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
    }

    @Test
    @Description("Check that user can be created with age 59")
    public void createNewUserWithAge59() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(59);

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
    }

    @Test
    @Description("Check that user can be created with age in range 18-58")
    public void createNewUserWithAgeInRange18To59() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(randomUtils.getRandomNumberInRange(18, 58));

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
    }

    @Test
    @Description("Check that user can be created with password length 7")
    public void createNewUserWithPasswordLength7() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setPassword(randomUtils.generateRandomAlphanumeric(7));

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
    }

    @Test
    @Description("Check that user can be created with password length 15")
    public void createNewUserWithPasswordLength15() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setPassword(randomUtils.generateRandomAlphanumeric(15));

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
    }

    @Test
    @Description("Check that user can be created with password length in range 8-14 symbols")
    public void createNewUserWithPasswordLengthInRange8To14() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setPassword(randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(8, 14));

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
    }

}
