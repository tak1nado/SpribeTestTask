package com.spribe.player.controller.models.users;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String propertyGenderText;

    Gender(String propertyGenderText){
        this.propertyGenderText = propertyGenderText;
    }

    public String getGenderPropertyText() {
        return propertyGenderText;
    }

    public static Gender getRandom() {
        int rnd = new Random().nextInt(values().length);
        return values()[rnd];
    }

    public static Gender getRandomExcept(Gender gender) {
        List<Gender> allGenders = Arrays.asList(values());
        List<Gender> availableGenders = allGenders.stream()
                .filter(g -> g != gender)
                .collect(Collectors.toList());

        if (!availableGenders.isEmpty()) {
            int rnd = new Random().nextInt(availableGenders.size());
            return availableGenders.get(rnd);
        }
        return gender;
    }
}
