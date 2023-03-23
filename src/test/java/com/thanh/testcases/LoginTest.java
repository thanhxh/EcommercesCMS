package com.thanh.testcases;

import com.thanh.common.BaseTest;
import com.thanh.pages.CommonPage;
import com.thanh.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Regression Test")
@Epic("Regression Test v1.0.0")
@Feature("Login Test")

public class LoginTest extends BaseTest {
    public LoginPage loginPage;
    public CommonPage commonPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        commonPage = new CommonPage();
    }

    @Test(priority = 1)
    public void testLogIn() {
        loginPage.logInValid();
    }

    @Test(priority = 2)
    public void testLogInWithEmailInvalid() {
        loginPage.logInWithEmailInvalid();
    }

    @Test(priority = 3)
    public void testLogInWithPasswordInvalid() {
        loginPage.logInWithPasswordInvalid();
    }

    @Test(priority = 4)
    public void testLogOut() {
        loginPage.logInValid();
        commonPage.logOut();
    }
}

