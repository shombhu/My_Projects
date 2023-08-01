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

public class SetupPage
{
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;

	public SetupPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Users']")
	WebElement lnkUsers;

	@FindBy(xpath = "//a[text()='Profiles']")
	WebElement lnkProfiles;

	@FindBy(xpath = "//span[text()='Object Manager']")
	WebElement lnkObjectManager;


	public void navigateToProfilesPage() throws IOException
	{
		actionMethods.clickElementWhenVisible(lnkUsers, 20);
		actionMethods.clickElementWhenVisible(lnkProfiles, 5);
	}

	public void navigateToObjectManagerPage() throws IOException
	{
		actionMethods.clickElementWhenVisible(lnkObjectManager, 20);
	}

}