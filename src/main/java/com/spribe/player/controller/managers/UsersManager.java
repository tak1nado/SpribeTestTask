package com.spribe.player.controller.managers;

import com.spribe.helpers.exceptions.InstanceAlreadyPickedException;
import com.spribe.helpers.exceptions.NotFoundException;
import com.spribe.helpers.utils.RandomUtils;
import com.spribe.player.controller.PlayerController;
import com.spribe.player.controller.models.users.Gender;
import com.spribe.player.controller.models.users.PlayerControllerUserRole;
import com.spribe.player.controller.models.users.User;
import com.spribe.player.controller.models.users.UserData;
import io.qameta.allure.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersManager {
    protected final Logger logger = LoggerFactory.getLogger(UsersManager.class);
    @Autowired private PlayerControllerUsersApi playerControllerUsersApi;
    @Autowired private PlayerController playerController;
    @Autowired private RandomUtils randomUtils;
    private final ArrayList<User> users = new ArrayList<>();
    private final InheritableThreadLocal<ArrayList<User>> tlUsers = new InheritableThreadLocal<>();
    private final ArrayList<User> pickedUsers = new ArrayList<>();

    @Description("Only for default users creation.")
    public synchronized void createInstance(String login, String role) {
        UserData userData = new UserData.Builder().login(login).role(role).build();
        User user = new User(0, userData);
        user.setTest(false);
        this.users.add(user);
    }

    public synchronized User createInstance(int id, UserData userData) {
        User user = new User(id, userData);
        this.users.add(user);
        return user;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public List<User> getTestUsers() {
        return getAllUsers().stream()
                .filter(User::isTest)
                .collect(Collectors.toList());
    }

    public User getTestUserByRole(PlayerControllerUserRole userRole) {
        return this.getTestUsers().stream()
                .filter(user -> user.getRole().equals(userRole)).findAny()
                .orElseThrow(() -> new NotFoundException("No user with role: " + userRole));
    }

    public User getDefaultUserByRole(PlayerControllerUserRole userRole) {
        return this.getAllUsers().stream()
                .filter(user -> !user.isTest())
                .filter(user -> user.getRole().equals(userRole)).findAny()
                .orElseThrow(() -> new NotFoundException("No user with role: " + userRole));
    }

    public UserData generateRandomValidUserDataWithRole(PlayerControllerUserRole userRole) {
        return new UserData.Builder()
                .age(randomUtils.getRandomNumberInRange(17, 59))
                .gender(Gender.getRandom())
                .login(randomUtils.generateRandomLogin())
                .password(randomUtils.generateRandomPassword())
                .role(userRole)
                .screenName(randomUtils.generateRandomScreenName())
                .build();
    }

    public UserData generateRandomValidUserDataWithoutRole() {
        return new UserData.Builder()
                .age(randomUtils.getRandomNumberInRange(17, 59))
                .gender(Gender.getRandom())
                .login(randomUtils.generateRandomLogin())
                .password(randomUtils.generateRandomPassword())
                .screenName(randomUtils.generateRandomScreenName())
                .build();
    }

    public void deleteAllCreatedUsers() {
        logger.info("Users to delete: " + getTestUsers());
        getTestUsers().forEach(this::deleteUser);
        this.users.removeAll(getTestUsers());
    }

    private void deleteUser(User user) {
        try {
            User supervisor = getDefaultUserByRole(PlayerControllerUserRole.SUPERVISOR);
            playerControllerUsersApi.deleteUserById(supervisor, user.getId());
        } catch (UndeclaredThrowableException exception) {
            logger.info("Connection refused: " + exception);
        }
    }

    public synchronized List<User> getNotPickedUsers() {
        return getTestUsers().stream()
                .filter(user -> !pickedUsers.contains(user))
                .collect(Collectors.toList());
    }

    public synchronized User pick(User user) throws InstanceAlreadyPickedException {
        if (!isPicked(user)) {
            this.pickedUsers.add(user);
            if (this.tlUsers.get() == null) {
                this.tlUsers.set(new ArrayList<>());
                this.tlUsers.get().add(user);
            } else if (!tlUsers.get().contains(user)) {
                this.tlUsers.get().add(user);
            }
        }
        return user;
    }

    public boolean isPicked(User user) {
        return this.pickedUsers.contains(user);
    }

    public synchronized void unpickThreadUser(User user) {
        if (this.tlUsers.get() != null) {
            this.pickedUsers.remove(user);
            this.tlUsers.get().remove(user);
        }
    }

    public synchronized void unpickThreadUsers() {
        if (this.tlUsers.get() != null) {
            this.pickedUsers.removeAll(this.tlUsers.get());
            this.tlUsers.get().clear();
        }
    }
}
