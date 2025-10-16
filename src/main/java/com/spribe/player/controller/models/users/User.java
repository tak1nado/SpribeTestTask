package com.spribe.player.controller.models.users;

public class User {

    private final int id;
    private UserData userData;
    private boolean isTest = true;

    public User(int id, int age, String gender, String login, String password, String role, String screenName) {
        this.id= id;
        this.userData = new UserData(age, gender, login, password, role, screenName);
    }

    public User(Integer id, UserData userData) {
        this.id = id;
        this.userData = new UserData(userData);
    }

    public int getId() {
        return this.id;
    }

    public PlayerControllerUserRole getRole() {
        return this.userData.getRole();
    }

    public void setRole(PlayerControllerUserRole userRole) {
        this.userData.setRole(userRole);
    }

    public int getAge() {
        return this.userData.getAge();
    }

    public void setAge(int age) {
        this.userData.setAge(age);
    }

    public Gender getGender() {
        return this.userData.getGender();
    }

    public void setGender(Gender gender) {
        this.userData.setGender(gender);
    }

    public String getLogin() {
        return this.userData.getLogin();
    }

    public String getPassword() {
        return this.userData.getPassword();
    }

    public void setPassword(String password) {
        this.userData.setPassword(password);
    }

    public String getScreenName() {
        return this.userData.getScreenName();
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public void updateUserData(UserData newUserData) {
        if (newUserData != null) {
            this.userData.update(newUserData);
        }
    }

    public UserData getUsedDataCopy() {
        return new UserData.Builder()
                .age(this.getAge())
                .gender(this.getGender())
                .login(this.getLogin())
                .password(this.getPassword())
                .role(this.getRole())
                .screenName(this.getScreenName())
                .build();
    }

    @Override
    public String toString() {
        return "User: " + this.userData;
    }
}
