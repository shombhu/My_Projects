`	package com.ecrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.pages.AccountsDetailsPage;
import com.ecrm.pages.AccountsPage;
import com.ecrm.pages.LoginPage;
import com.ecrm.util.Log;
import com.ecrm.util.TestDataParam;


public class AccountTest extends TestBase {

	WebDriver driver = null;
	@BeforeClass
	public void setDriver()
	{
		driver = TestBase.getDriver();
	}

	@BeforeClass
	public void setTestdataSheets()
	{
		TestDataParam.testdataFileName = "TestData.xlsx";
		TestDataParam.sheetName = "C360-Demographics";
	}

	@Test (dataProvider = "data", dataProviderClass = TestDataParam.class, enabled=true)
	public void CreateNewAccount(Map<Object,Object> map, Method m) throws IOException, Exception {

		//WebDriver driver = TestBase.getDriver();
		SoftAssert softAssert =  TestBase.getSoftAssert();
		ExtentTest extentTest = TestBase.reportInitialization(m.getName());

		driver.get(TestBase.prop.getProperty("SF_TEST_URL"));
		extentTest.log(Status.INFO, "Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");
		Log.info("Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");

		LoginPage loginPage = new LoginPage(driver, softAssert, extentTest);
		AccountsPage AccountsPage = new AccountsPage(driver, softAssert, extentTest);
		AccountsDetailsPage accountDetailsPage = new AccountsDetailsPage(driver, softAssert, extentTest);

		loginPage.login(TestBase.prop.getProperty("sf_test_username"), TestBase.prop.getProperty("sf_test_password"));
		String accName = AccountsPage.openNewAccountWindowAndSave(map);
		accountDetailsPage.verifyCreatedAccountDetails(accName, map);
		loginPage.logOut();

		extentTest.log(Status.INFO, "Testcase completed");
		//softAssert.assertAll();
	}


	@AfterMethod
	public void generateHtmlReport()
	{
		TestBase.writeToHtmlReport();
	}


	@AfterClass
	public void exit()
	{
		driver.close();
	}
}
