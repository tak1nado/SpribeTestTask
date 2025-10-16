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
@Feature("Create user API negative scenarios")
public class CreateUserApiNegativeScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(CreateUserApiNegativeScenariosTests.class);

    @Test
    @Description("Check that user can't be created with age less than 16")
    public void createNewUserWithAgeLessThan16() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(randomUtils.getRandomNumberInRange(1, 15));

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with age greater than 60")
    public void createNewUserWithAgeGreaterThan60() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(randomUtils.getRandomNumberInRange(61, 120));

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with age 16")
    public void createNewUserWithAge16() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(16);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with age 60")
    public void createNewUserWithAge60() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setAge(60);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with already existed login value")
    public void createNewUserWithAlreadyExistedLoginValue() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User existedUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setLogin(existedUser.getLogin());

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with already existed screenName value")
    public void createNewUserWithAlreadyExistedScreenNameValue() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        User existedUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.ADMIN);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        userData.setScreenName(existedUser.getScreenName());

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with gender differs from 'male' or 'female'")
    public void createNewUserWithInvalidGender() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidGender = randomUtils.generateRandomString();

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", invalidGender)
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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password shorter than 6 symbols")
    public void createNewUserWithPasswordShorterThan6Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(1, 5);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password longer than 16 symbols")
    public void createNewUserWithPasswordLongerThan16Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomAlphanumericStringNumberOfSymbolsInRange(17, 25);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password length 6 symbols")
    public void createNewUserWithPasswordLength6Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomAlphanumericString(6);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password longer than 16 symbols")
    public void createNewUserWithPasswordLength16Symbols() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomAlphanumericString(16);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password that contains only numbers")
    public void createNewUserWithPasswordThatContainsOnlyNumbers() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomNumericNumberOfSymbolsInRange(7, 15);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created with password that contains only latin letters")
    public void createNewUserWithPasswordThatContainsOnlyLetters() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);
        String invalidPassword = randomUtils.generateRandomAlphabeticNumberOfSymbolsInRange(7, 15);
        userData.setPassword(invalidPassword);

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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    // TODO Clarify requirement: Check that user can't be created with specific symbols

    @Test
    @Description("Check that user can't be created without age field")
    public void createNewUserWithoutAgeField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created without gender field")
    public void createNewUserWithoutGenderField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
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

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created without login field")
    public void createNewUserWithoutLoginField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    //TODO Clarify requirement: password field is set as not required in Swagger
    @Test
    @Description("Check that user can't be created without password field")
    public void createNewUserWithoutPasswordField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created without role field")
    public void createNewUserWithoutRoleField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("screenName", userData.getScreenName())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created without screenName field")
    public void createNewUserWithoutScreenNameField() {
        User editor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .get(String.format("player/create/%s", editor.getLogin()));

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

    @Test
    @Description("Check that user can't be created without editor parameter")
    public void createNewUserWithoutEditorParameter() {
        UserData userData = usersManager.generateRandomValidUserDataWithRole(PlayerControllerUserRole.USER);

        logger.info("Test body: " + userData);

        Response response = spribeApi.playerControllerRequest()
                .queryParam("age", userData.getAge())
                .queryParam("gender", userData.getGender().getGenderPropertyText())
                .queryParam("login", userData.getLogin())
                .queryParam("password", userData.getPassword())
                .queryParam("role", userData.getRole().getRolePropertyText())
                .queryParam("screenName", userData.getScreenName())
                .get("player/create/");

        logger.info("Create user response: " + response.asString());

        if (response.statusCode() == 200 && !JsonParser.parseString(response.asString()).getAsJsonObject().get("id").isJsonNull()) {
            int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
            usersManager.pick(usersManager.createInstance(id, userData));
        }

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST,
                "Status code should be 400 (Bad Request).");
    }

}
