package com.ecrm.pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.VerificationMethods;
import com.ecrm.util.ExcelReader;

public class FieldAndRelationshipPage {

	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;

	public FieldAndRelationshipPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest){

		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}

	String objectNameDynamicXpath = "//table[contains(@class,'slds-table slds-table--bordered uiVirtualDataGrid--default uiVirtualDataGrid')]//a[contains(text(),'?')]";


	@FindBy(xpath = "//a[@data-list='Fields & Relationships']")
	WebElement fieldAndRelationshipLink;

	@FindBy(xpath = "//a[@class='toggle ']")
	WebElement labelLink;

	@FindBy(xpath = "//th[@class='slds-text-heading--label slds-is-sortable standard-col slds-truncate ascending']")
	WebElement fieldLabelLink;




	public void navigateToFieldsAndRelationship(String omName) throws IOException
	{
		verificationMethods.isElementVisible(labelLink, 20);
		actionMethods.clickElementWhenVisible(By.xpath(objectNameDynamicXpath.replace("?", omName)), 10);
		verificationMethods.isElementVisible(fieldAndRelationshipLink, 20);
		actionMethods.clickElementWhenVisible(fieldAndRelationshipLink, 10);
		verificationMethods.isElementVisible(fieldLabelLink, 20);

	}

	public void verifyFieldsAndRelationship(String fileName, String sheetName) throws IOException {

		verificationMethods.isElementVisible(fieldLabelLink, 10);
		ExcelReader excelreaderObj = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ecrm\\testData\\",fileName);
		ArrayList<String> rowHeaderList = ExcelReader.getRowheaderValueAsListFromTestDataMap(sheetName);
		try {
			verifyFieldValue(rowHeaderList, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyFieldValue(ArrayList<String> rowHeader, String sheetName) throws Exception	{
		String[] columnHeader = { "Field Name", "Data Type", "Indexed"};
		String xpath = "";
		for (String rowName : rowHeader) {
			for (String colName : columnHeader) {
				switch(colName) {

				case "Field Name":
					xpath = "//span[text()='" + rowName + "']/following::td[1]";
					break;

				case "Data Type":
					xpath = "//span[text()='" + rowName + "']/following::td[2]";
					break;

				case "Indexed":
					xpath = "//span[text()='" + rowName + "']/following::td[4]";
					break;

				default :



				}

				WebElement element = driver.findElement(By.xpath(xpath));

				verificationMethods.verifyContainsTextWhenVisible(element, 30, ExcelReader.getExcelValue(sheetName, rowName, colName));
							}

		}
	}

	}



