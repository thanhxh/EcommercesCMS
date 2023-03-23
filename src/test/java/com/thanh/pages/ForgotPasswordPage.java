package com.thanh.pages;

import com.thanh.constants.ConstantGlobal;
import com.thanh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ForgotPasswordPage extends CommonPage {
    private By formAdv = By.xpath("//div[@class='aiz-editor-data']");
    private By buttonX = By.xpath("//i[@class='la la-close fs-20']");
    private By textForgot = By.xpath("//h1[normalize-space()='Forgot password?']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By buttonSendLink = By.xpath("//button[normalize-space()='Send Password Reset Link']");
    private By message = By.xpath("//span[@data-notify='message']");
    //No account exists with this phone number
    public void forgotPassValid(){
        Assert.assertTrue(WebUI.getWebElement(formAdv).isDisplayed(),"Not appear advertising");
        WebUI.clickElement(buttonX);
        WebUI.verifyEquals(WebUI.getElementText(textForgot),"Forgot password?");
        WebUI.setText(inputEmail, ConstantGlobal.EMAIL);
        WebUI.clickElement(buttonSendLink);
    }
    public void forgotPassInValidWithEmail(){
        Assert.assertTrue(WebUI.getWebElement(formAdv).isDisplayed(),"Not appear advertising");
        WebUI.clickElement(buttonX);
        WebUI.verifyEquals(WebUI.getElementText(textForgot),"Forgot password?");
        WebUI.setText(inputEmail, "tienthanhxh123@gmail.com");
        WebUI.clickElement(buttonSendLink);
        WebUI.verifyEquals(WebUI.getElementText(message),"No account exists with this email");
    }
    public void forgotPassInValidWithNumber(){
        Assert.assertTrue(WebUI.getWebElement(formAdv).isDisplayed(),"Not appear advertising");
        WebUI.clickElement(buttonX);
        WebUI.verifyEquals(WebUI.getElementText(textForgot),"Forgot password?");
        WebUI.setText(inputEmail, "tienthanh123@123");
        WebUI.clickElement(buttonSendLink);
        WebUI.verifyEquals(WebUI.getElementText(message),"No account exists with this phone number");
    }
}
