package com.ecrm.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.FieldPosition;
import com.ecrm.commonMethods.VerificationMethods;
import com.ecrm.util.CommonUtility;
import com.ecrm.util.Log;
import com.ecrm.util.TestUtil;


public class AccountsPage {
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;
	FieldPosition fieldPosition;

	public AccountsPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest) {
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		fieldPosition = new FieldPosition(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[text()='Accounts']]]")
	WebElement lnkAccounts;

	@FindBy(xpath = "//div[contains(text(),'New')]")
	WebElement btnNew;

	@FindBy(xpath = "//h2[text()='New Account: Accout Page Layout']")
	WebElement hdrNewAccount;

	/*@FindBy(xpath = "//span[contains(text(),'Account Name')]/../following-sibling::input[@type='text']")*/
	@FindBy(xpath = "//label[text()='Account Name']/following::input[@name='Name']")
	WebElement inpAccountName;

	@FindBy(xpath = "//label[text()='Rating']/following-sibling::div")
	WebElement lstRating;
	
	@FindBy(xpath = "//label[text()='Type']/following-sibling::div")
	WebElement lstType;
	
	//@FindBy(xpath = "//label[text()='Ownership']/following-sibling::div")
	WebElement ownershipType;

	@FindBy(xpath = "//label[text()='Industry']/following-sibling::div")
	WebElement lstIndustry;

	@FindBy(xpath = "(//label[text()='Billing Street']/following-sibling::div)/textarea")
	WebElement inpBillingStreet;

	@FindBy(xpath = "(//label[text()='Billing City']/following-sibling::div)/input")
	WebElement inpBillingCity;

	@FindBy(xpath = "(//label[text()='Billing State/Province']/following-sibling::div)/input")
	WebElement inpBillingState;

	@FindBy(xpath = "(//label[text()='Billing Zip/Postal Code']/following-sibling::div)/input")
	WebElement inpBillingZipPostal;

	@FindBy(xpath = "(//label[text()='Billing Country']/following-sibling::div)/input")
	WebElement inpBillingCountry;

	@FindBy(xpath = "(//label[text()='Description']/following-sibling::div)/textarea")
	WebElement inpDescription;
	
	@FindBy(xpath = "(//label[text()='SLA']/following-sibling::div)[1]")
	WebElement inpSLA;
	
	@FindBy(xpath = "//label[text()='SLA Serial Number']/following::input[@name='SLASerialNumber__c']")
	WebElement inpSLASerialNum;
	
	@FindBy(xpath = "//label[text()='SLA Expiration Date']/following::input[@name='SLAExpirationDate__c']")
    WebElement inpSLAExpirationDate;	
	
	//Satya added here for items position
	@FindBy(xpath ="//force-record-layout-item//label")
	List<WebElement> NewAccountlabels;

	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement btnCancel;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement btnSave;

	@FindBy(xpath = "//button[text()='Save & Next']")
	WebElement btnNext;

	@FindBy(xpath = "//div[contains(@class,'outputNameWithHierarchyIcon')]/span[contains(@class,'OutputText')]")
	WebElement txtCreatedAccountName;

	@FindBy(className = "errorsList")
	WebElement errorMessage;

	@FindBy(xpath = "//iframe[@title='Profiles ~ Salesforce - Developer Edition']")
	WebElement iframeProfiles;

	@FindBy(xpath = "//input[@title='New Profile']")
	WebElement btnNewProfile;

	String dropdownOptionsXpath_old = "//li[contains(@class, 'uiMenuItem')]/a[text()='?']";
	String locatorone_old;
	
	String dropdownOptionsXpath = "//span[contains(text(),'?')]";
	String locatorone;

	public void openNewAccountWindowAndClose() throws IOException {

		verificationMethods.isElementEnabled(lnkAccounts, 10);
		actionMethods.clickElementWhenVisible(lnkAccounts, 5);

		verificationMethods.isElementEnabled(btnNew, 10);
		actionMethods.clickElementWhenVisible(btnNew, 5);

		verificationMethods.isElementVisible(hdrNewAccount, 20);

		verificationMethods.isElementEnabled(btnNext, 10);
		actionMethods.clickElementWhenVisible(btnNext, 5);

		verificationMethods.isElementEnabled(btnCancel, 10);
		actionMethods.clickElementWhenVisible(btnCancel, 5);
	}

	public String openNewAccountWindowAndSave(Map<Object, Object> map) throws IOException, Exception
	{
		  String dynamicAccName = map.get("AccountName").toString() + "_" + CommonUtility.getDateTime();
		  String dynamicAccSLASerialNumber = CommonUtility.getDateTime();
		
		  Log.info("Navigating to Account creation page"); extentTest.log(Status.INFO,
		  "Navigating to Account creation page");
		  
		  verificationMethods.isElementVisible(lnkAccounts, 20);
		  actionMethods.clickElementWhenVisible(lnkAccounts, 10);
		  driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT,
		  TimeUnit.SECONDS);
		  
		  Log.info("Creating new account"); extentTest.log(Status.INFO,
		  "Creating new account"); verificationMethods.isElementEnabled(btnNew, 10);
		  actionMethods.clickElementWhenVisible(btnNew, 5);
		  
		  verificationMethods.isElementVisible(hdrNewAccount, 20);
		  
		  //verificationMethods.isElementEnabled(btnNext, 10);
		  //actionMethods.clickElementWhenVisible(btnNext, 5);
		  
		 //fieldPosition.FieldOrder(NewAccountlabels);
		  //Satya Added here - Position of Fields
	 
		  actionMethods.enterValueWhenVisible(inpAccountName, dynamicAccName, 5);
		  //actionMethods.enterValueWhenVisible(inpAccountName,map.get("AccountName").toString(), 5);
		  actionMethods.selectFromDropdownWhenVisible(lstRating, dropdownOptionsXpath,
		  map.get("Rating").toString());
		  
		  //actionMethods.scrollIntoViewWhenVisible(lstType);
		  //actionMethods.selectFromDropdownWhenVisible(lstType,dropdownOptionsXpath, map.get("AccountType").toString());
		  
		  //actionMethods.scrollIntoViewWhenVisible(ownershipType);
		  //actionMethods.selectFromDropdownWhenVisible(ownershipType,dropdownOptionsXpath, map.get("Ownership").toString());
		  
		  actionMethods.scrollIntoViewWhenVisible(lstIndustry);
		  actionMethods.selectFromDropdownWhenVisible(lstIndustry,dropdownOptionsXpath, map.get("AccountIndustry").toString());
		  
		  actionMethods.scrollIntoViewWhenVisible(inpSLA);
		  actionMethods.selectFromDropdownWhenVisible(inpSLA,dropdownOptionsXpath, map.get("AccountSLA").toString());
		  
		  actionMethods.enterValueWhenVisible(inpSLASerialNum, dynamicAccSLASerialNumber, 5);
		  actionMethods.enterValueWhenVisible(inpSLAExpirationDate,  map.get("AccountSLAExpirationDate").toString(), 5);
		  
		  actionMethods.enterValueWhenVisible(inpBillingStreet,
		  map.get("BillingStreet").toString(), 5);
		  actionMethods.enterValueWhenVisible(inpBillingCity,
		  map.get("BillingCity").toString(), 5);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  actionMethods.enterValueWhenVisible(inpBillingState,
		  map.get("BillingState").toString(), 5);
		  actionMethods.enterValueWhenVisible(inpBillingZipPostal,
		  map.get("BillingZip").toString(), 5);
		  actionMethods.enterValueWhenVisible(inpBillingCountry,
		  map.get("BillingCountry").toString(), 5);
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  actionMethods.enterValueWhenVisible(inpDescription,
		  map.get("Description").toString(), 5);
		  
		  actionMethods.clickElementWhenVisible(btnSave, 5);
		  
		  verificationMethods.isElementVisible(txtCreatedAccountName, 20);
		  
		  verificationMethods.verifyTextWhenVisible(txtCreatedAccountName, 5,
		  dynamicAccName);
		 
		Log.info("New account created successfully with name: " + dynamicAccName);
		extentTest.log(Status.INFO, "New account created successfully with name: " + dynamicAccName);
		return dynamicAccName;
	}

	public void openNewAccountMissingMandatoryFields(Map<Object, Object> map) throws IOException {

		verificationMethods.isElementVisible(lnkAccounts, 20);
		actionMethods.clickElementWhenVisible(lnkAccounts, 10);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		Log.info("Creating new account");
		extentTest.log(Status.INFO, "Creating new account");
		verificationMethods.isElementEnabled(btnNew, 10);
		actionMethods.clickElementWhenVisible(btnNew, 5);

		verificationMethods.isElementVisible(hdrNewAccount, 20);

		verificationMethods.isElementEnabled(btnNext, 10);
		actionMethods.clickElementWhenVisible(btnNext, 5);

		actionMethods.selectFromDropdownWhenVisible(lstType, dropdownOptionsXpath, map.get("AccountType").toString());
		actionMethods.selectFromDropdownWhenVisible(lstIndustry, dropdownOptionsXpath,map.get("AccountIndustry").toString());
		actionMethods.enterValueWhenVisible(inpBillingStreet, map.get("BillingStreet").toString(), 5);
		actionMethods.enterValueWhenVisible(inpBillingCity, map.get("BillingCity").toString(), 5);
		actionMethods.enterValueWhenVisible(inpBillingState, map.get("BillingState").toString(), 5);
		actionMethods.enterValueWhenVisible(inpBillingZipPostal, map.get("BillingZip").toString(), 5);
		actionMethods.enterValueWhenVisible(inpBillingCountry, map.get("BillingCountry").toString(), 5);
		actionMethods.enterValueWhenVisible(inpDescription, map.get("Description").toString(), 5);
		actionMethods.clickElementWhenVisible(btnSave, 5);
		verificationMethods.isElementVisible(errorMessage, 10);
		String imagePath = TestBase.takeScreenshot();
		Log.info(imagePath);
		extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
		Log.info("Missing Mandatory Field: " + inpAccountName);
		extentTest.log(Status.PASS, "Missing Mandatory Fields: " + inpAccountName);
		Log.info("Error Message: " + errorMessage);
		extentTest.log(Status.PASS, "Error Message: " + errorMessage.getText());
		actionMethods.clickElementWhenVisible(btnCancel, 10);
	}
}