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

public class ProfilePage {
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;

	public ProfilePage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest) {
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[text()='Profiles']")
	WebElement subHdrProfiles;

	@FindBy(xpath = "//input[@value='New Profile']")
	WebElement btnNewProfile;

	@FindBy(name = "vfFrameId_1587638467165")
	WebElement frameProfile;

	@FindBy(xpath = "//h2[@class='pageDescription']")
	WebElement hdrProfileName;

	@FindBy(xpath = "//h3[contains(text(),'Standard Object Permissions')]")
	WebElement hdrStandardObjPerm;

	@FindBy(id = "setupComponent")
	WebElement divSetupComponent;

	@FindBy(xpath = "//span[contains(text(),'Profiles') and contains(@class, 'breadcrumbDetail')]")
	WebElement hdrProfiles;

	@FindBy(xpath = "//div[@class='oneAlohaPage']")
	WebElement divAlohaPage;

	@FindBy(xpath = "//iframe[@title='Profiles ~ Salesforce - Developer Edition']")
	WebElement iframeProfiles;

	String profileNameLinkDynamicXpath = "//span[text()='?']/..";

	// Replace ? with the profile name
	String profileFrameDynamicXpath = "//iframe[@title='Profile: ? ~ Salesforce - Developer Edition']";

	public void navigateToProfile(String profileName) throws IOException {
		verificationMethods.isElementVisible(hdrProfiles, 30);
		verificationMethods.isElementVisible(iframeProfiles, 30);
		driver.switchTo().frame(iframeProfiles);
		verificationMethods.isElementVisible(subHdrProfiles, 20);
		verificationMethods.isElementVisible(btnNewProfile, 5);
		actionMethods.clickElementWhenVisible(By.xpath(profileNameLinkDynamicXpath.replace("?", profileName)), 10);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(profileFrameDynamicXpath.replace("?", profileName))));
		verificationMethods.isElementVisible(hdrProfileName, 20);
		verificationMethods.verifyTextWhenVisible(hdrProfileName, 5, profileName);
	}

	public void verifyStandardOrCustomObjectPermissions(String fileName, String sheetName) throws IOException {
		actionMethods.scrollIntoViewWhenVisible(hdrStandardObjPerm);
		verificationMethods.isElementVisible(hdrStandardObjPerm, 10);
		ExcelReader excelreaderObj = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\com\\ecrm\\testData\\",fileName);
		ArrayList<String> rowHeaderList = ExcelReader.getRowheaderValueAsListFromTestDataMap(sheetName);
		try {
			verifyPermissions(rowHeaderList, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
	}

	public void verifyPermissions(ArrayList<String> rowHeader, String sheetName) throws Exception {
		String[] columnHeader = { "Read", "Create", "Edit", "Delete", "ViewAll", "ModifyAll" };
		String xpath = "";
		for (String rowName : rowHeader) {
			for (String colName : columnHeader) {
				if (colName.equals("Edit")) {
					xpath = "//th[text()='" + rowName + "']/following-sibling::td[1]//img[contains(@id,'Update')]";
				} else {
					xpath = "//th[text()='" + rowName + "']/following-sibling::td[1]//img[contains(@id,'" + colName
							+ "')]";
				}
				WebElement element = driver.findElement(By.xpath(xpath));
				verificationMethods.verifyAttributeWhenVisible(element, "title", ExcelReader.getExcelValue(sheetName, rowName, colName),30);
							}
		}
	}
}