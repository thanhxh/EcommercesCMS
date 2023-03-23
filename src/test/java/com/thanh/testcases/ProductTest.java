package com.thanh.testcases;

import com.thanh.common.BaseTest;
import com.thanh.helpers.ExcelHelpers;
import com.thanh.helpers.PropertiesHelpers;
import com.thanh.pages.CommonPage;
import com.thanh.pages.LoginPage;
import com.thanh.pages.ProductPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    public LoginPage loginPage;
    public CommonPage commonPage;
    public ProductPage productPage;
    @BeforeMethod
    public void setUp(){
        loginPage = new LoginPage();
        commonPage = loginPage.logInValid();
        productPage = commonPage.openProduct();
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Product"),"Information");
    }
    @Test
    public void testProduct(){
        String name = ExcelHelpers.getCellData("name",1);
        productPage.addNewProduct(name);
        productPage.searchProduct(name);
    }
}
