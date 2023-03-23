package com.thanh.testcases;

import com.thanh.common.BaseTest;
import com.thanh.pages.CommonPage;
import com.thanh.pages.ForgotPasswordPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ForgotPassTest extends BaseTest {
    public CommonPage commonPage;
    public ForgotPasswordPage forgotPasswordPage;
    @BeforeMethod
    public void setUp(){
        commonPage = new CommonPage();
        forgotPasswordPage = commonPage.forgotPassword();
    }
    @Test(priority = 1)
    public void testForgotPassValid(){
        forgotPasswordPage.forgotPassValid();
    }
    @Test (priority = 2)
    public void testForgotPassInValidWithEmail(){
        forgotPasswordPage.forgotPassInValidWithEmail();
    }
    @Test (priority = 3)
    public void testForgotPassInValidWithNumber(){
        forgotPasswordPage.forgotPassInValidWithNumber();
    }
}
