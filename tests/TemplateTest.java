package com.ecrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.base.TestBase;
import com.ecrm.util.TestDataParam;


/**
 * This is a test class which TestBase.java class
 * @author Pooja Ahir
 *
 */
public class TemplateTest extends TestBase {

	/**
	 * This is a test method
	 * @param map
	 * @param m
	 * @throws IOException
	 */
	@Test (dataProvider = "data", dataProviderClass = TestDataParam.class, enabled=true)
	public void TestMethod(Map<Object,Object> map, Method m) throws IOException {

		WebDriver driver = TestBase.getDriver();
		SoftAssert softAssert =  TestBase.getSoftAssert();
		ExtentTest extentTest = TestBase.reportInitialization(m.getName());

		//pass the desired url to below statement
		driver.get("");

		//write the test steps here

		driver.close();
		softAssert.assertAll();
	}


	/**
	 * This method executes after every test method.
	 * It writes all the reporting statements captured while executing the testcase, to an html report.
	 */
	@AfterMethod
	public void exit()
	{
		TestBase.writeToHtmlReport();
	}
}