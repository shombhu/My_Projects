package com.ecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class TemplatePage
{
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public TemplatePage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		PageFactory.initElements(driver, this);
	}

	//*****************Initialize web elements here**************************




	//********************Define methods here********************************



}