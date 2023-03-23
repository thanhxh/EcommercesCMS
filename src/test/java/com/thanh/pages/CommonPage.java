package com.thanh.pages;

import com.thanh.constants.ConstantGlobal;
import com.thanh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CommonPage {
    public LoginPage loginPage;
    public CategoryPage categoryPage;
    public ProductPage productPage;
    public ForgotPasswordPage forgotPasswordPage;

    public By menuProduct = By.xpath("//span[normalize-space()='Products']");
    public By menuAllProduct = By.xpath("//span[normalize-space()='All products']");
    public By menuCategory = By.xpath("//span[normalize-space()='Category']");
    public By buttonStaff = By.xpath("//span[@class='d-flex align-items-center']");
    public By buttonLogOut = By.xpath("//span[normalize-space()='Logout']");
    public By buttonClearCache = By.xpath("//span[normalize-space()='Clear Cache']");
    public By headerCategory = By.xpath("//h1[normalize-space()='All categories']");
    public By headerProduct = By.xpath("//h1[normalize-space()='All products']");
    //Search
    public By inputSearch = By.xpath("//input[@id='search']");
    public By searchDropdownInput = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    //Upload File Image
    public By buttonUploadNew = By.xpath("//a[normalize-space()='Upload New']");
    public By buttonBrowse = By.xpath("//button[normalize-space()='Browse']");
    public By buttonAddFiles = By.xpath("//button[normalize-space()='Add Files']");
    public By buttonSelectFile = By.xpath("//a[normalize-space()='Select File']");
    public By inputSearchFile = By.xpath("//input[@placeholder='Search your files']");
    public By itemFile = By.xpath("(//div[@class='card-body']//h6//span)[1]");
    //Forgot Password
    public By buttonForgot = By.xpath("//a[normalize-space()='Forgot password ?']");
    public LoginPage logOut() {
        WebUI.verifyEquals(WebUI.getElementText(buttonClearCache),"Clear Cache");
        WebUI.clickElement(buttonStaff);
        WebUI.clickElement(buttonLogOut);
        return new LoginPage();
    }
    public ForgotPasswordPage forgotPassword(){
        WebUI.openURL(ConstantGlobal.URL);
        WebUI.clickElement(buttonForgot);
        return new ForgotPasswordPage();
    }

    public CategoryPage openCategory() {
        WebUI.verifyEquals(WebUI.getElementText(buttonClearCache),"Clear Cache");
        WebUI.clickElement(menuProduct);
        WebUI.clickElement(menuCategory);
        boolean checkPageCategory = WebUI.checkElementExist(headerCategory);
        Assert.assertTrue(checkPageCategory,"Fail. Not open CategoryPage " + headerCategory);
        String checkText = WebUI.getElementText(headerCategory);
        WebUI.sleep(0.5);
        Assert.assertTrue(checkText.contains(getCategoryPage().pageText), "Fail. Text of Header Category page not match.");
        WebUI.sleep(0.5);
        return new CategoryPage();
    }
    public ProductPage openProduct() {
        WebUI.verifyEquals(WebUI.getElementText(buttonClearCache),"Clear Cache");
        WebUI.clickElement(menuProduct);
        WebUI.clickElement(menuAllProduct);
        boolean checkPageCategory = WebUI.checkElementExist(headerProduct);
        Assert.assertTrue(checkPageCategory,"Fail. Not open CategoryPage " + headerProduct);
        String checkText = WebUI.getElementText(headerProduct);
        Assert.assertTrue(checkText.contains(getProductPage().pageText), "Fail. Text of Header Category page not match.");
        return new ProductPage();
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new CategoryPage();
        }
        return categoryPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }
    public ForgotPasswordPage getForgotPasswordPage() {
        if (forgotPasswordPage == null) {
            forgotPasswordPage = new ForgotPasswordPage();
        }
        return forgotPasswordPage;
    }
}
