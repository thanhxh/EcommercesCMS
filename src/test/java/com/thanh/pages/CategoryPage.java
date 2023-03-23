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
import java.awt.event.KeyEvent;
import java.util.List;

public class CategoryPage extends CommonPage {
    public String pageText = ConstantGlobal.CATEGORIES;
    private By buttonAddCategories = By.xpath("//span[normalize-space()='Add New category']");
    //Object Add Category
    private By checkCategoryForm = By.xpath("//h5[normalize-space()='Category Information']");
    private By inputName = By.xpath("//input[@id='name']");
    private By parentCateDropdown = By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div");
    private By inputOrderNumber = By.xpath("//input[@id='order_level']");
    private By typeDropdown = By.xpath("//label[normalize-space()='Type']//following-sibling::div");
    private By inputDigital = By.xpath("//span[normalize-space()='Digital']");
    private By chooseFileBanner = By.xpath("//label[normalize-space()='Banner (200x200)']/following-sibling::div//div[normalize-space()='Choose File']");
    private By inputMetaType = By.xpath("//input[@placeholder='Meta Title']");
    private By inputMetaDes = By.xpath("//textarea[@name='meta_description']");
    private By filterDropdown = By.xpath("//label[normalize-space()='Filtering Attributes']//following-sibling::div");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By buttonNext = By.xpath("//a[contains(text(),'›')]");

    //Object Search Category

    private By allCategoryText = By.xpath("//h1[normalize-space()='All categories']");
    private By categoriesText = By.xpath("//h5[normalize-space()='Categories']");
    public void enterName(String name){
        WebUI.setText(inputName,name);
    }
    public void dynamicDropdown(String field)  {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Categories"),"Dynamic Dropdown");
        if (field.equals("Parent Category")) {
            WebUI.clickElement(parentCateDropdown);
            WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("parent_cate",1));
            WebUI.pressENTER(searchDropdownInput);
        }


        if (field.equals("Filtering Attributes")) {
            WebUI.clickElement(filterDropdown);
            WebUI.setText(searchDropdownInput, ExcelHelpers.getCellData("filtering",1));
            WebUI.pressENTER(searchDropdownInput);
        }

    }

    public void staticDropdown(String field) {
        if (field.equals("Type")) {
            WebUI.clickElement(typeDropdown);
            WebUI.clickElement(inputDigital);
        }
    }

    public void inputOrderNumber()  {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(WebUI.getWebElement(inputOrderNumber).isDisplayed(), "Không phải element Order Number");
        WebUI.setText(inputOrderNumber, ExcelHelpers.getCellData("order",1));
    }
    public void uploadBanner(){
        WebUI.clickElement(chooseFileBanner);
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
    public void enterMetaType()  {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(WebUI.getWebElement(inputMetaType).isDisplayed(), "Không phải element Meta Type");
        WebUI.setText(inputMetaType, ExcelHelpers.getCellData("metaType",1));
    }

    public void enterMetaDes()  {
        ExcelHelpers.setExcelFile(PropertiesHelpers.getValue("excelPath_Categories")," Order + MetaType + MetaDes");
        Assert.assertTrue(WebUI.getWebElement(inputMetaDes).isDisplayed(), "Không phải element Meta Des");
        WebUI.setText(inputMetaDes, ExcelHelpers.getCellData("metaDes",1));
    }

    public void clickSave() {
        Assert.assertTrue(WebUI.getWebElement(buttonSave).isDisplayed(), "Không phải element Save");
        WebUI.clickElement(buttonSave);
    }

    public void addNewCategory(String name)  {
        WebUI.clickElement(buttonAddCategories);
        WebUI.verifyEquals(WebUI.getElementText(checkCategoryForm), "Category Information", "Không phải trang Information");
        enterName(name);
        WebUI.sleep(0.5);
        dynamicDropdown("Parent Category");
        WebUI.sleep(0.5);
        inputOrderNumber();
        WebUI.sleep(0.5);
        staticDropdown("Type");
        WebUI.sleep(0.5);
        uploadBanner();
        WebUI.sleep(1);
        enterMetaType();
        enterMetaDes();
        WebUI.sleep(0.5);
        dynamicDropdown("Filtering Attributes");

        WebUI.scrollToElement(buttonSave);
        clickSave();
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
    public void searchCategories(String name){
        WebUI.verifyEquals(WebUI.getElementText(allCategoryText), "All categories");
        WebUI.sleep(0.5);
        WebUI.verifyEquals(WebUI.getElementText(categoriesText), "Categories");
        WebUI.sleep(0.5);
        Assert.assertTrue(WebUI.getWebElement(inputSearch).isDisplayed(), "Không hiển thị element Search");
        WebUI.sleep(0.5);
        WebUI.setText(inputSearch, name);
        WebUI.sleep(0.5);
        WebUI.pressENTER(inputSearch);
        WebUI.sleep(1);
        checkSearchTableByColumn(2, name);
        WebUI.clickElement(buttonNext);
        checkSearchTableByColumn(2, name);
    }
}
