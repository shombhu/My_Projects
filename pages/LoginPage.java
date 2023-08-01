package com.ecrm.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.VerificationMethods;
import com.ecrm.util.Log;

/**
 *  Created by Pooja Ahir on 4/8/2020
 *  This is the Login Page Class extends Base Class
 *  It consists of Login page webelement's xpaths and associated methods
 *
 */
public class LoginPage
{
		WebDriver driver;
		SoftAssert softAssert;
		ExtentTest extentTest;
		ActionMethods actionMethods;
	    VerificationMethods verificationMethods;

		public LoginPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
		{
			this.driver = driver;
			this.softAssert = softAssert;
			this.extentTest = extentTest;
			actionMethods = new ActionMethods(driver, softAssert, extentTest);
			verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
			PageFactory.initElements(driver, this);
		}

	@FindBy(xpath = "//img[@id='logo' and @alt='Salesforce']")
	WebElement imgSFLogo;

	@FindBy(id = "username")
	WebElement inpUsername;

	@FindBy(id = "password")
	WebElement inpPassword;

	@FindBy(id = "Login")
	WebElement btnLogin;

	@FindBy(xpath = "//span[@title='Retail Banking']")
	WebElement hdrRetailBanking;

	@FindBy(xpath = "//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[text()='Accounts']]]")
	WebElement lnkAccounts;

	@FindBy(xpath = "//span[text()='View profile']/preceding-sibling::span[1]/div")
	WebElement btnProfilePicture;

	@FindBy(xpath = "(//a[text()='Log Out'])[1]")
	WebElement lnkLogout;

	@FindBy(xpath = "//div[@class='slds-icon-waffle']")
	WebElement btnAppLauncher;

	/**
	 * Validate LoginPage Title
	 * @return LoginPage Title
	 */
	public String validatePageTitle()
	{
		return driver.getTitle();
	}

	/**
	 * Validate Logo in LoginPage
	 * @return true/false
	 */
	public Boolean validateSalesForceLogo()
	{
		return imgSFLogo.isDisplayed();
	}

	public void login(String uname,String pwd) throws IOException
	{
		verificationMethods.isElementVisible(imgSFLogo, 10);
		verificationMethods.isElementVisibleAbort(inpUsername, 10);
		verificationMethods.isElementVisibleAbort(inpPassword, 10);
		verificationMethods.isElementVisibleAbort(btnLogin, 10);
		actionMethods.enterValueWhenVisible(inpUsername, uname, 5);
		actionMethods.enterValueWhenVisible(inpPassword, pwd, 5);
		verificationMethods.isElementVisible(btnLogin, 10);
		verificationMethods.isElementEnabled(btnLogin, 10);
		actionMethods.clickElementWhenVisible(btnLogin, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		verificationMethods.isElementVisibleAbort(btnProfilePicture, 30);
		Log.info("Logged in SuccessFully");
		extentTest.log(Status.PASS, "Login successful");
	}

	public void logOut() throws IOException
	{
		actionMethods.clickElementWhenVisible(btnProfilePicture, 10);
		actionMethods.clickElementWhenVisible(lnkLogout, 10);
		verificationMethods.isElementVisible(inpUsername, 10);
		Log.info("Logged out SuccessFully");
	}
}
