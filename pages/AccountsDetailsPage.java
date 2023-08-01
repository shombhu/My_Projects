package com.ecrm.pages;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.VerificationMethods;

public class AccountsDetailsPage
{
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;

	public AccountsDetailsPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Account Name']/../following-sibling::div/span/slot/slot/lightning-formatted-text")
	WebElement txtAccountName;

	@FindBy(xpath = "//span[text()='Type']/../following-sibling::div/span/slot/slot/lightning-formatted-text")
	WebElement txtAccountType;

	@FindBy(xpath = "//span[text()='Industry']/../following-sibling::div/span/slot/slot/lightning-formatted-text")
	WebElement txtAccountIndustry;

	@FindBy(xpath = "//span[text()='Billing Address']/../following::span//a/div[1]")
	WebElement txtBAStreet;	//50 Eastgate Avenue, West Jefferson

	@FindBy(xpath = "//span[text()='Billing Address']/../following::span//a/div[2]")
	//@FindBy(xpath = "//p[text()='Billing Address']/following::a/div[2]")
	WebElement txtBACityStateZip;	//Cleveland Ohio 43162.0

	@FindBy(xpath = "//span[text()='Billing Address']/../following::span//a/div[3]")
	WebElement txtBACountry;	//United States

	@FindBy(xpath = "//span[text()='Description']/../following-sibling::div/span//slot[@slot='outputField']/lightning-formatted-text")
	WebElement txtDescription;

	@FindBy(xpath = "//a[@data-label='Details']")
	WebElement detailsTab;




	public void verifyCreatedAccountDetails(String dynamicAccountName, Map<Object, Object> map) throws IOException
	{
		clickOnElement(detailsTab, 6);

		verificationMethods.verifyTextWhenVisible(txtAccountName, 5, dynamicAccountName);
		verificationMethods.verifyTextWhenVisible(txtAccountType, 5, map.get("AccountType").toString());
		verificationMethods.verifyTextWhenVisible(txtAccountIndustry, 5, map.get("AccountIndustry").toString());
		verificationMethods.verifyTextWhenVisible(txtBAStreet, 5, map.get("BillingStreet").toString());
		verificationMethods.verifyTextWhenVisible(txtBACityStateZip, 5, map.get("BillingCity").toString() + " " +
				map.get("BillingState").toString() + " " + map.get("BillingZip").toString());
		verificationMethods.verifyTextWhenVisible(txtBACountry, 5, map.get("BillingCountry").toString());
		verificationMethods.verifyTextWhenVisible(txtDescription, 5, map.get("Description").toString());
	}

	public void clickOnElement(WebElement element, int timeOut) throws IOException {

		actionMethods.clickElementWhenVisible(element, timeOut);
	}


}