package com.spribe.player.controller.models.users;

public enum PlayerControllerUserRole {
    SUPERVISOR("supervisor"),
    ADMIN("admin"),
    USER("user");

    private final String propertyRoleText;

    PlayerControllerUserRole(String propertyRoleText){
        this.propertyRoleText = propertyRoleText;
    }

    public String getRolePropertyText() {
        return propertyRoleText;
    }
}
