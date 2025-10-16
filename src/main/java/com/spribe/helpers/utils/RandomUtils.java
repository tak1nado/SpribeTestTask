package com.spribe.helpers.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomUtils {

    public Float getRandomFloat(float leftLimit, float rightLimit) {
        DecimalFormat df = new DecimalFormat("####0.00");
        return Float.parseFloat(df.format(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit)));
    }

    public Double getRandomDouble(double leftLimit, double rightLimit) {
        DecimalFormat df = new DecimalFormat("####0.00");
        return Double.parseDouble(df.format(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit)));
    }

    public int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getRandomNumberInRangeExcludingNumbers(int min, int max, List<Integer> excludingNumbers) {
        int[] range = IntStream.rangeClosed(min, max).toArray();
        List<Integer> rangeExcluding = Arrays.stream(range).boxed().collect(Collectors.toList());

        rangeExcluding.removeAll(excludingNumbers);
        int newRandomInt = new Random().nextInt(rangeExcluding.size());

        return rangeExcluding.get(newRandomInt);
    }

    public String generateRandomLogin() {
        return "login" + UUID.randomUUID();
    }

    public String generateRandomScreenName() {
        return "sn" + UUID.randomUUID();
    }

    public String generateRandomPassword() {
        return "pass" + RandomStringUtils.randomAlphanumeric(9);
    }

    public String generateRandomString() {
        return "text" + RandomStringUtils.randomAlphabetic(9);
    }

    public String generateRandomNumericNumberOfSymbolsInRange(int min, int max) {
        return RandomStringUtils.randomNumeric(getRandomNumberInRange(min, max));
    }

    public String generateRandomAlphabetic(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String generateRandomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String generateRandomAlphabeticNumberOfSymbolsInRange(int min, int max) {
        return RandomStringUtils.randomAlphabetic(getRandomNumberInRange(min, max));
    }

    public String generateRandomAlphanumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public String generateRandomAlphanumericStringNumberOfSymbolsInRange(int min, int max) {
        return RandomStringUtils.randomAlphanumeric(getRandomNumberInRange(min, max));
    }
}
