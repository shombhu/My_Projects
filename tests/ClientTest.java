package com.ecrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.pages.ClientPage;
import com.ecrm.pages.LoginPage;
import com.ecrm.util.Log;


public class ClientTest {

	public class AccountTest extends TestBase {

		WebDriver driver = null;
		@BeforeClass
		public void setDriver()
		{
			driver = TestBase.getDriver();
		}

		public void ValidateClientInfo(Map<Object,Object> map, Method m) throws IOException {

			//WebDriver driver = TestBase.getDriver();
			SoftAssert softAssert =  TestBase.getSoftAssert();
			ExtentTest extentTest = TestBase.reportInitialization(m.getName());

			driver.get(TestBase.prop.getProperty("SF_TEST_URL"));
			extentTest.log(Status.INFO, "Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");
			Log.info("Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");

			LoginPage loginPage = new LoginPage(driver, softAssert, extentTest);
			ClientPage ClientPage = new ClientPage(driver, softAssert, extentTest);

			loginPage.login(TestBase.prop.getProperty("sf_test_username"), TestBase.prop.getProperty("sf_test_password"));
			ClientPage.readHeaderPannel();

			loginPage.logOut();

			extentTest.log(Status.INFO, "Testcase completed");
			softAssert.assertAll();
		}
}
}
