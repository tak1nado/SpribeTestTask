package com.spribe.player.controller.api.steps;

import com.google.gson.JsonParser;
import com.spribe.helpers.exceptions.InstanceAlreadyPickedException;
import com.spribe.player.controller.managers.PlayerControllerUsersApi;
import com.spribe.player.controller.managers.UsersManager;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import com.spribe.player.controller.models.users.UserData;
import io.qameta.allure.Step;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class UsersApiActions {
    @Autowired private UsersManager usersManager;
    @Autowired private PlayerControllerUsersApi playerControllerUsersApi;

    @Step("User with role {0} is created")
    public User userWithRoleIsCreated(PlayerControllerUserRole userRole) {
        try {
            return usersManager.pick(usersManager.getNotPickedUsers().stream()
                    .filter(user -> user.getRole().equals(userRole))
                    .findAny().orElseGet(() -> {
                        User supervisor = usersManager.getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
                        UserData userData = usersManager.generateRandomValidUserDataWithRole(userRole);
                        Response response = playerControllerUsersApi.createNewUser(supervisor, userData);

                        Assert.assertTrue(!response.asString().isEmpty() &&
                                        JsonParser.parseString(response.asString()).getAsJsonObject().has("id"),
                                "User 'id' should not be null or empty.");
                        int id = JsonParser.parseString(response.asString()).getAsJsonObject().get("id").getAsInt();
                        return usersManager.createInstance(id, userData);
                    }));
        } catch (InstanceAlreadyPickedException ex) {
            return userWithRoleIsCreated(userRole);
        }
    }
}
