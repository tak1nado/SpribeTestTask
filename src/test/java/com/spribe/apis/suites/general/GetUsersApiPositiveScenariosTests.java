package com.spribe.apis.suites.general;

import com.spribe.apis.suites.PlayerControllerTests;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("Get users")
@Feature("Get users API positive scenarios")
public class GetUsersApiPositiveScenariosTests extends PlayerControllerTests {
    protected final Logger logger = LoggerFactory.getLogger(GetUsersApiPositiveScenariosTests.class);

    @Test
    @Description("Check that get users api returns valid data")
    public void getAllUsersApiDataValidation() {
        User searchUser = usersApiActions.userWithRoleIsCreated(PlayerControllerUserRole.USER);

        Response response = playerControllerUsersApi.getAllUsersData();
        logger.info("Search for user: " + searchUser);

        Map<String, Object> foundUser = response.jsonPath().get("players.find { it.id == " + searchUser.getId() + " }");

        logger.info("Found user: " + foundUser);
        Assert.assertNotNull(foundUser, "User wasn't found by id.");
        Assert.assertTrue(foundUser.containsKey("age"),
                "Field 'age' should be displayed.");
        Assert.assertEquals(foundUser.get("age"), searchUser.getAge(),
                "Object field 'age' doesn't match");
        Assert.assertTrue(foundUser.containsKey("gender"),
                "Field 'gender' should be displayed.");
        Assert.assertEquals(foundUser.get("gender"), searchUser.getGender().getGenderPropertyText(),
                "Object field 'gender' doesn't match");
        Assert.assertTrue(foundUser.containsKey("role"),
                "Field 'role' should be displayed.");
        Assert.assertEquals(foundUser.get("role"), searchUser.getRole().getRolePropertyText(),
                "Object field 'role' doesn't match");
        Assert.assertTrue(foundUser.containsKey("screenName"),
                "Field 'screenName' should be displayed.");
        Assert.assertEquals(foundUser.get("screenName"), searchUser.getScreenName(),
                "Object field 'screenName' doesn't match");
        Assert.assertFalse(foundUser.containsKey("screenName"),
                "Field 'password' should not be displayed.");
    }

}
