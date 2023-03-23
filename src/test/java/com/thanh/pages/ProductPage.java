package com.thanh.pages;

import com.thanh.constants.ConstantGlobal;
import com.thanh.helpers.ExcelHelpers;
import com.thanh.helpers.PropertiesHelpers;
import com.thanh.helpers.SystemsHelpers;
import com.thanh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class ProductPage extends CommonPage {
    public String pageText = ConstantGlobal.ALL_PRODUCT;
    private By buttonAddNewProduct = By.xpath("//a[@class='btn btn-circle btn-info']//span[contains(text(),'Add New Product')]");
    private By checkAddProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    private By checkFormProduct = By.xpath("//h5[normalize-space()='Product Information']");
    private By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    private By categoryDropdown = By.xpath("//div[@id='category']//label[@class='col-md-3 col-from-label']//following-sibling::div");
    private By brandDropdown = By.xpath("//label[normalize-space()='Brand']//following-sibling::div");
    private By inputUnit = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    private By inputQty = By.xpath("//input[@name='min_qty']");
    private By fileGallery = By.xpath ("//label[normalize-space()='Gallery Images (600x600)']//following-sibling::div");
    private By scrollProductVariation = By.xpath("//h5[normalize-space()='Product Variation']");
    private By buttonOnOff = By.xpath("//div[@class='col-md-1']//span");
    private By colorDropdown = By.xpath("//input[@value='Colors']//following::button[@title='Nothing selected'][1]");
    private By colorLabel = By.xpath("//input[@value='Colors']");
    private By inputUnitPrice = By.xpath("//input[@placeholder='Unit price']");
    private By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    private By inputMetaTitle = By.xpath("//input[@placeholder='Meta Title']");
    private By scrollSEO = By.xpath("//h5[normalize-space()='SEO Meta Tags']");
    private By buttonSavePublish = By.xpath("//button[normalize-space()='Save & Publish']");

    public void CTRL_A_DELETE() {
        WebUI.pressCTRL_A();
        WebUI.pressDELETE();
    }

    public void clickAddProduct() {
        Assert.assertEquals(WebUI.getElementText(buttonAddNewProduct), "Add New Product");
        WebUI.clickElement(buttonAddNewProduct);
        Assert.assertEquals(WebUI.getElementText(checkAddProduct), "Add New Product", "Không phải trang Add New Product");
        Assert.assertEquals(WebUI.getElementText(checkFormProduct), "Product Information", "Không phải trang Add New Product");
    }

    public void inputInformation(String name) {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Product"), "Information");
        WebUI.setText(inputProductName, name);
        WebUI.clickElement(categoryDropdown);
        WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("cateDrop", 1));
        WebUI.pressENTER(searchDropdownInput);
        WebUI.sleep(0.5);
        WebUI.clickElement(brandDropdown);
        WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("brandDrop", 1));
        WebUI.pressENTER(searchDropdownInput);
        WebUI.sleep(0.5);
        WebUI.setText(inputUnit, ExcelHelpers.getCellData("unit", 1));
        WebUI.sleep(0.5);
        WebUI.clickElement(inputQty);
        CTRL_A_DELETE();
        WebUI.setText(inputQty, ExcelHelpers.getCellData("Qty", 1));
        WebUI.sleep(0.5);
    }
    public void uploadGallery(){
        WebUI.clickElement(fileGallery);
        WebUI.clickElement(buttonUploadNew);
        WebUI.clickElement(buttonBrowse); //Mở form select file từ local PC

        String filePath = SystemsHelpers.getCurrentDir() + "datatest\\pilot.png";

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        WebUI.sleep(1);

        WebUI.pressPASTE();

        WebUI.sleep(2);

        // Nhấn Enter
        WebUI.pressENTER();

        WebUI.sleep(3);
        WebUI.clickElement(buttonSelectFile);
        WebUI.setText(inputSearchFile, "pilot");
        WebUI.sleep(1);
        WebUI.clickElement(itemFile);
        WebUI.sleep(1);
        WebUI.clickElement(buttonAddFiles);
        WebUI.sleep(2);
    }

    public void inputVariation() {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Product"), "Variation");
        if (WebUI.getWebElement(buttonOnOff).isEnabled() == true) {
            WebUI.clickElement(buttonOnOff);
        }
        WebUI.sleep(0.5);
        WebUI.clickElement(colorDropdown);
        WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("color1", 1));
        WebUI.pressENTER(searchDropdownInput);
        CTRL_A_DELETE();
        WebUI.sleep(2);
        WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("color2", 1));
        WebUI.pressENTER(searchDropdownInput);
        CTRL_A_DELETE();
        WebUI.sleep(2);
        WebUI.clickElement(colorLabel);
    }

    public void inputExternalLink() {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Product"), "External + Meta");
        Assert.assertTrue(WebUI.getWebElement(inputUnitPrice).isDisplayed(), "Không tìm thấy element Unit Price");
        WebUI.clickElement(inputUnitPrice);
        CTRL_A_DELETE();
        WebUI.setText(inputUnitPrice, ExcelHelpers.getCellData("unit price", 1));
        WebUI.sleep(0.5);
        Assert.assertTrue(WebUI.getWebElement(inputDiscount).isDisplayed(), "Không tìm thấy element Discount");
        WebUI.clickElement(inputDiscount);
        CTRL_A_DELETE();
        WebUI.setText(inputDiscount, ExcelHelpers.getCellData("discount price", 1));
        WebUI.sleep(0.5);
    }

    public void inputMeta() {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Product"), "External + Meta");
        Assert.assertTrue(WebUI.getWebElement(inputMetaTitle).isDisplayed(), "Không phải element Meta Title");
        WebUI.setText(inputMetaTitle, ExcelHelpers.getCellData("meta_Title", 1));
    }

    public void clickSavePublish() {
        Assert.assertTrue(WebUI.getWebElement(buttonSavePublish).isDisplayed(), "Không phải element Save Publish");
        WebUI.clickElement(buttonSavePublish);
    }

    public void addNewProduct(String name) {
        clickAddProduct();
        inputInformation(name);
        uploadGallery();
        WebUI.scrollToElement(scrollProductVariation);
        WebUI.sleep(0.5);
        inputVariation();
        WebUI.sleep(1);
        inputExternalLink();
        WebUI.sleep(1);
        WebUI.scrollToElement(scrollSEO);
        WebUI.sleep(0.5);
        inputMeta();
        clickSavePublish();

    }
    public void checkSearchTableByColumn(int column, String name) {
        WebUI.waitForPageLoaded();
        WebUI.sleep(2);

        //Xác định số dòng của table sau khi search
        List<WebElement> row = WebUI.getWebElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);
        //Duyệt từng dòng
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = WebUI.getWebElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]"));

            WebUI.scrollToElement(elementCheck);

            System.out.print(name + " - ");
            System.out.println(elementCheck.getText());
            Assert.assertEquals(elementCheck.getText(), name, "Dòng số " + i + " không chứa giá trị tìm kiếm.");
        }

    }
    public void searchProduct(String name){
        Assert.assertTrue(WebUI.getWebElement(inputSearch).isDisplayed(), "Không hiển thị element Search");
        WebUI.sleep(0.5);
        WebUI.setText(inputSearch, name);
        WebUI.sleep(0.5);
        WebUI.pressENTER(inputSearch);
        WebUI.sleep(1);
        checkSearchTableByColumn(2, name);

    }
}
