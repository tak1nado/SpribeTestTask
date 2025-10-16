package com.spribe.apis.runners;

import com.spribe.player.controller.PlayerController;
import com.spribe.player.controller.managers.UsersManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@ContextConfiguration(locations = {"classpath:spring-application-context.xml"})
public class APITestsRunner extends AbstractTestNGSpringContextTests {
    protected final Logger logger = LoggerFactory.getLogger(APITestsRunner.class);
    @Autowired private PlayerController playerController;
    @Autowired private UsersManager usersManager;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodLog(Method method) {
        logger.info("START TEST METHOD: " + method.getName());
    }

    @BeforeTest(alwaysRun = true)
    public void initSuite() {
        RestAssured.replaceFiltersWith(new AllureRestAssured());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodLog(Method method) {
        logger.info("FINISH TEST METHOD: " + method.getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void preconditions() {
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodCleanUp() {
        usersManager.unpickThreadUsers();
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUp() {
        usersManager.deleteAllCreatedUsers();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownClass() {
        createAllureReportEnvironmentVariables();
    }

    private void createAllureReportEnvironmentVariables() {
        try (FileOutputStream fos = new FileOutputStream("target/allure-results/environment.properties")) {
            Properties properties = new Properties();
            properties.setProperty("Environment: ", playerController.getBaseUrl());
            ofNullable(System.getProperty("os.name")).ifPresent(p -> properties.setProperty("OS name: ", p));
            properties.store(fos, EMPTY);
        } catch (IOException e) {
            logger.warn("IO problem when writing allure properties file: " + e.getMessage());
        }
    }
}
