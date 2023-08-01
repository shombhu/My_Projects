package com.ecrm.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.VerificationMethods;

public class ClientPage {
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;

	public ClientPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest) {
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath = "//a[@title='Clients']")
	WebElement lnkClients;

	@FindBy(xpath = "(//one-app-nav-bar-menu-button[@class='slds-dropdown-trigger slds-dropdown-trigger--click'])[1]")
	WebElement lconClients;

	@FindBy(xpath = "//span[contains(text(),'All Clients')]")
	WebElement lnkAllClients;

	@FindBy(xpath = "//span[contains(text(),'All Clients')]")
	WebElement hdrAllClients;

	@FindBy(xpath = "//a[@title='Carl Jones Dr.']")
	WebElement lnkClientName;

	@FindBy(xpath = "//a[@title='Carl Jones Dr.']")
	WebElement hdrClientName;

	@FindBy(xpath = "//p[@title='Segment']")
	WebElement lblSegment;

	@FindBy(xpath = "//p[@title='Client Value Indicator']")
	WebElement lblClientValueIndicator;

	@FindBy(xpath = "//p[@title='Client Type']")
	WebElement lblClientType;

	@FindBy(xpath = "//p[@title='Relationship Manager']")
	WebElement lblRelationshipManager;

	@FindBy(xpath = "//li[@title='Details']")
	WebElement tabDetails;

	@FindBy(xpath = "(//p[@title='Segment'])[2]/following-sibling::p/slot/lightning-formatted-text")
	WebElement txtSegment;

	@FindBy(xpath = "(//p[@title='Client Value Indicator'])[2]/following-sibling::p/slot/lightning-formatted-text")
	WebElement txtClientValueIndicator;

	@FindBy(xpath = "(//p[@title='Client Type'])[2]/following-sibling::p/slot/lightning-formatted-text")
	WebElement txtClientType;

	@FindBy(xpath = "(//p[@title='Relationship Manager'])[2]/following-sibling::p/slot/lightning-formatted-text")
	WebElement txtRelationshipManager;

	@FindBy(xpath = "//span[contains(text(),'Indigenous')]//following::input[@type='checkbox']")
	WebElement chkbxIndigenous;

	@FindBy(xpath = "//span[contains(text(),'Senior')]//following::input[@type='checkbox']")
	WebElement chkbxSenior;


	public String readHeaderPannel() throws IOException {

		verificationMethods.isElementVisible(lnkClients, 20);
		actionMethods.clickElementWhenVisible(lconClients, 5);

		verificationMethods.isElementVisible(hdrAllClients, 20);
		actionMethods.clickElementWhenVisible(lnkClientName, 5);

		verificationMethods.isElementVisible(lblSegment, 20);
		verificationMethods.verifyTextWhenVisible(txtSegment, 10, "Bank@work");
		verificationMethods.verifyTextWhenVisible(txtClientValueIndicator, 10, "VIP");
		verificationMethods.verifyTextWhenVisible(txtClientType, 10, "Non-Client");
		verificationMethods.verifyTextWhenVisible(txtRelationshipManager, 10, "Sonam Gupta");
		return null;
	}












}
