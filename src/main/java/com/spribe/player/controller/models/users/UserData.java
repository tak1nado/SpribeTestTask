package com.spribe.player.controller.models.users;

import com.spribe.helpers.exceptions.NotFoundException;

import java.util.stream.Stream;

public class UserData {

    private Integer age;
    private Gender gender;
    private String login;
    private String password;
    private PlayerControllerUserRole role;
    private String screenName;

    public UserData(int age, String gender, String login, String password, String role, String screenName) {
        this.age = age;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.login = login;
        this.password = password;
        this.role = parseUserRoles(role);
        this.screenName = screenName;
    }

    public UserData(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }

    public UserData(UserData userData) {
        this.age = userData.age;
        this.gender = userData.gender;
        this.login = userData.login;
        this.password = userData.password;
        this.role = userData.role;
        this.screenName = userData.screenName;
    }

    public PlayerControllerUserRole getRole() {
        return this.role;
    }

    public void setRole(PlayerControllerUserRole userRole) {
        this.role = userRole;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void update(UserData newUserData) {
        if (newUserData.age != null) {
            this.age = newUserData.age;
        }
        if (newUserData.gender != null) {
            this.gender = newUserData.gender;
        }
        if (newUserData.password != null) {
            this.password = newUserData.password;
        }
        if (newUserData.login != null) {
            this.login = newUserData.login;
        }
        //TODO rewrite behavior or usage of role field update. It should not be updatable from system requirements
//        if (newUserData.role != null) {
//            this.role = newUserData.role;
//        }
        if (newUserData.screenName != null) {
            this.screenName = newUserData.screenName;
        }
    }

    @Override
    public String toString() {
        return "User data: Login: " + this.login + ", Age: " + this.age + ", Gender: " + this.gender +
                ", Password: " + this.password + ", Role: " + this.role + ", Screen name: " + this.screenName;
    }

    protected PlayerControllerUserRole parseUserRoles(String userRoleName) {
        return Stream.of(PlayerControllerUserRole.values())
                .filter(playerControllerUserRole -> playerControllerUserRole.getRolePropertyText().equalsIgnoreCase(userRoleName))
                .findAny().orElseThrow(() -> new NotFoundException(String.format("User with role %s is not found in framework", userRoleName)));
    }

    public static class Builder {
        private int age;
        private Gender gender;
        private String login;
        private String password;
        private PlayerControllerUserRole role;
        private String screenName;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(PlayerControllerUserRole role) {
            this.role = role;
            return this;
        }

        public Builder role(String role) {
            this.role = PlayerControllerUserRole.valueOf(role.toUpperCase());
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public UserData build() {
            return new UserData(this);
        }
    }
}
