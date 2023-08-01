package com.ecrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.base.TestBase;
import com.ecrm.pages.FieldAndRelationshipPage;
import com.ecrm.pages.LoginPage;
import com.ecrm.pages.SetupPage;
import com.ecrm.util.Log;
//import com.sun.org.apache.xerces.internal.util.Status;

public class FiieldAndRelationshipTest extends TestBase
{
	@Test
	public void verifyDataValues(Method m) throws IOException {

		WebDriver driver = TestBase.getDriver();
		SoftAssert softAssert = TestBase.getSoftAssert();
		ExtentTest extentTest = TestBase.reportInitialization(m.getName());

		String url = TestBase.prop.getProperty("SF_TEST_SETUP_URL");
		driver.get(url);
//SAtya commented		extentTest.log(Status.INFO, "Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");
		Log.info("Application Url '" + url + "' launched on browser");

		LoginPage loginPage = new LoginPage(driver, softAssert, extentTest);
		SetupPage setupPage = new SetupPage(driver, softAssert, extentTest);
		FieldAndRelationshipPage fieldAndRelationshipPage = new FieldAndRelationshipPage(driver, softAssert, extentTest);

		loginPage.login(TestBase.prop.getProperty("sf_test_username"), TestBase.prop.getProperty("sf_test_password"));
		setupPage.navigateToObjectManagerPage();
		fieldAndRelationshipPage.navigateToFieldsAndRelationship("Account");
		fieldAndRelationshipPage.verifyFieldsAndRelationship("ObjectDataType.xlsx", "Account");
		loginPage.logOut();
		driver.close();
		softAssert.assertAll();
}

	@AfterMethod
	public void exit() {
		TestBase.writeToHtmlReport();
	}
}
   