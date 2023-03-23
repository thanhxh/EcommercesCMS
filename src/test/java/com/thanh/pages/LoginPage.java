package com.thanh.pages;

import com.thanh.constants.ConstantGlobal;
import com.thanh.helpers.PropertiesHelpers;
import com.thanh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {
    static {
        PropertiesHelpers.loadAllFiles();
    }
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By alert = By.xpath("//div[@role='alert']");
    //Invalid login credentials

    public CommonPage logInValid(){
        WebUI.openURL(ConstantGlobal.URL);
        enterEmail();
        enterPassword();
        WebUI.clickElement(buttonLogin);
        WebUI.sleep(1);
        return new CommonPage();
    }
    public void enterEmail (){
        Assert.assertTrue(WebUI.getWebElement(inputEmail).isDisplayed(),"Không tìm thấy element Email");
        WebUI.setText(inputEmail,ConstantGlobal.EMAIL);
    }
    public void enterPassword (){
        Assert.assertTrue(WebUI.getWebElement(inputPassword).isDisplayed(),"Không tìm thấy element Password");
        WebUI.setText(inputPassword,ConstantGlobal.PASSWORD);
    }
    public void logInWithEmailInvalid(){
        WebUI.openURL(ConstantGlobal.URL);
        WebUI.setText(inputEmail,"tienthanhxh2000@gmail.com");
        enterPassword();
        WebUI.clickElement(buttonLogin);
        WebUI.verifyEquals(WebUI.getElementText(alert),"Invalid login credentials");
    }
    public void logInWithPasswordInvalid(){
        WebUI.openURL(ConstantGlobal.URL);
        enterEmail();
        WebUI.setText(inputPassword,"123");
        WebUI.clickElement(buttonLogin);
        WebUI.verifyEquals(WebUI.getElementText(alert),"Invalid login credentials");
    }
}
