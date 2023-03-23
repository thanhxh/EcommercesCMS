package com.thanh.testcases;

import com.thanh.common.BaseTest;
import com.thanh.helpers.ExcelHelpers;
import com.thanh.helpers.PropertiesHelpers;
import com.thanh.pages.CategoryPage;
import com.thanh.pages.CommonPage;
import com.thanh.pages.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("Regression Test")
@Epic("Regression Test v1.0.0")
@Feature("Category Test")

public class CategoryTest extends BaseTest {
    public LoginPage loginPage;
    public CommonPage commonPage;
    public CategoryPage categoryPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        commonPage = loginPage.logInValid();
        categoryPage = commonPage.openCategory();
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Categories"),"Name");
    }

    @Test
    public void testCategories() {
        String name = ExcelHelpers.getCellData("name",1);
        categoryPage.addNewCategory(name);
        categoryPage.searchCategories(name);
    }

}
