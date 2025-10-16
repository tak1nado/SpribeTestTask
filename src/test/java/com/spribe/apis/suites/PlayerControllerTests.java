package com.spribe.apis.suites;

import com.spribe.apis.runners.APITestsRunner;
import com.spribe.helpers.utils.RandomUtils;
import com.spribe.player.controller.api.steps.UsersApiActions;
import com.spribe.player.controller.managers.PlayerControllerUsersApi;
import com.spribe.player.controller.managers.SpribeApi;
import com.spribe.player.controller.managers.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerControllerTests extends APITestsRunner {
    @Autowired protected SpribeApi spribeApi;
    @Autowired protected RandomUtils randomUtils;
    @Autowired protected UsersManager usersManager;
    @Autowired protected PlayerControllerUsersApi playerControllerUsersApi;
    @Autowired protected UsersApiActions usersApiActions;
}
