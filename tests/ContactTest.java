package com.ecrm.tests;

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
import com.aventstack.extentreports.ExtentTest;
import com.ecrm.base.TestBase;
import com.ecrm.pages.ContactPage;
import com.ecrm.pages.LoginPage;
import com.ecrm.util.TestDataParam;
import com.ecrm.util.Log;

public class ContactTest  extends TestBase {
	WebDriver driver = null;
	@BeforeClass
	public void setDriver()
	{
		driver = TestBase.getDriver();
	}
	@BeforeClass
	public void setTestdataSheets()
	{
		TestDataParam.testdataFileName = "TestData(2).xlsx";
		TestDataParam.sheetName = "C360-Demographics";
	}
	@Test (dataProvider = "data", dataProviderClass = TestDataParam.class, enabled=true)
	public void CreateNewContact(Map<Object,Object> map, Method m) throws IOException, Exception 
	{
		SoftAssert softAssert =  TestBase.getSoftAssert();
		ExtentTest extentTest = TestBase.reportInitialization(m.getName());
        driver.get(TestBase.prop.getProperty("SF_TEST_URL"));
		extentTest.log(Status.INFO, "Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");
		Log.info("Application Url '" + TestBase.prop.getProperty("SF_TEST_URL") + "' launched on browser");
		LoginPage loginPage = new LoginPage(driver, softAssert, extentTest);
		ContactPage ContactPage = new ContactPage(driver, softAssert, extentTest);
		loginPage.login(TestBase.prop.getProperty("sf_test_username"), TestBase.prop.getProperty("sf_test_password"));
		
		ContactPage.clickContactfromtab();
		Log.info("Navigating to Contact Tab");
		extentTest.log(Status.INFO,"Navigating to Contact Tab");
		
		ContactPage.clickNewBtn();
		Log.info("Navigating to Contact creation page after clicking New Button");
		extentTest.log(Status.INFO,"Navigating to Contact creation page after clicking New Button");
		
		ContactPage.VerifyNewContactHeader();
		Log.info("Verify to Contact creation page Heading");
		extentTest.log(Status.INFO,"Verify to Contact creation page Heading");
		
		ContactPage.SelectContactSalutation(map);
		Log.info("Verify to Contact creation page Heading");
		extentTest.log(Status.INFO,"Verify to Contact creation page Heading");
		
		ContactPage.EnterContactFName(map);
		Log.info("Contact First Name is Entered");
		extentTest.log(Status.INFO,"Contact First Name is Entered");
		
		ContactPage.EnterContactLName(map);
		Log.info("Contact Last Name is Entered");
		extentTest.log(Status.INFO,"Contact Last Name is Entered");
		
	/*	ContactPage.SelectAccountName(map);
		Log.info("Account Name is Entered");
		extentTest.log(Status.INFO,"Account Name is Entered"); */
		
		ContactPage.EnterTitle(map);
		Log.info("Contact Title is Entered");
		extentTest.log(Status.INFO,"Contact Title is Entered");
		
		ContactPage.EnterDepartment(map);
		Log.info("Contact Department is Entered");
		extentTest.log(Status.INFO,"Contact Department is Entered");
		
		ContactPage.EnterBirthdate(map);
		Log.info("Contact Birthdate is Entered");
		extentTest.log(Status.INFO,"Contact Birthdate is Entered");
		
		ContactPage.SelectContactLeadSource(map);
		Log.info("Contact Birthdate is Entered");
		extentTest.log(Status.INFO,"Contact Birthdate is Entered");
		
		ContactPage.SelectContactCurrency(map);
		Log.info("Contact Currency is Entered");
		extentTest.log(Status.INFO,"Contact Currency is Entered");
		
		ContactPage.EnterLoanAmount(map);
		Log.info("Contact LoanAmount is Entered");
		extentTest.log(Status.INFO,"Contact LoanAmount is Entered");
		
		ContactPage.SelectContactStage(map);
		Log.info("Contact Stage is Entered");
		extentTest.log(Status.INFO,"Contact Stage is Entered");
		
		ContactPage.EnterContactPhone(map);
		Log.info("Contact Phone is Entered");
		extentTest.log(Status.INFO,"Contact Phone is Entered");
		
		ContactPage.EnterContactHomePhone(map);
		Log.info("Contact Home Phone is Entered");
		extentTest.log(Status.INFO,"Contact Home Phone is Entered");
		
		ContactPage.EnterContactOtherPhone(map);
		Log.info("Contact Other Phone is Entered");
		extentTest.log(Status.INFO,"Contact Other Phone is Entered");
		
		ContactPage.EnterContactFax(map);
		Log.info("Contact Fax is Entered");
		extentTest.log(Status.INFO,"Contact Fax is Entered");
		
		ContactPage.EnterContactEmail(map);
		Log.info("Contact Email is Entered");
		extentTest.log(Status.INFO,"Contact Email is Entered");
		
		ContactPage.EnterContactMailingStreet(map);
		Log.info("Contact Mailing Street is Entered");
		extentTest.log(Status.INFO,"Contact Mailing Street is Entered");
		
		ContactPage.EnterContactMailingCity(map);
		Log.info("Contact Mailing City is Entered");
		extentTest.log(Status.INFO,"Contact Mailing City is Entered");
		
		ContactPage.EnterContactMailingState(map);
		Log.info("Contact Mailing State is Entered");
		extentTest.log(Status.INFO,"Contact Mailing State is Entered");
		
		ContactPage.EnterContactMailingZipPostal(map);
		Log.info("Contact Mailing Zip Postal is Entered");
		extentTest.log(Status.INFO,"Contact Mailing Zip Postal is Entered");
		
		ContactPage.EnterContactMailingCountry(map);
		Log.info("Contact Mailing Country is Entered");
		extentTest.log(Status.INFO,"Contact Mailing Country is Entered");
		
		ContactPage.EnterContactDescription(map);
		Log.info("Contact Description is Entered");
		extentTest.log(Status.INFO,"Contact Description is Entered");
		
		ContactPage.BtnContactSave();
		Log.info("Save Button is Clicked");
		extentTest.log(Status.INFO,"Save Button is Clicked");
		
		ContactPage.DetailsTab();
		Log.info("Contact Details Tab is Clicked");
		extentTest.log(Status.INFO,"Contact Details Tab is Clicked");
		
		loginPage.logOut();
		Log.info("Logged out Successful");
		extentTest.log(Status.INFO,"Logged out Successful");
		
		extentTest.log(Status.INFO, "Testcase completed");
		softAssert.assertAll();
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
