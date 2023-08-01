package com.ecrm.commonMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecrm.util.Log;

public class AlertMethods
{
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public AlertMethods(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
	}

	/**
	 * This method checks if Alertbox is present for timeout seconds and then dismiss the Alert box
	 * @param driver
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void dismissAlert(WebDriver driver,int timeout)
	{
		try
		{
			if(isAlertPresent(driver,timeout))
			{
				Alert alert = driver.switchTo().alert();

				alert.dismiss();
				extentTest.log(Status.PASS, "Alert Dialog now is Dismissed");
			}
			else
			{
				softAssert.assertTrue(false, "There is no Alert dialog");
				extentTest.log(Status.FAIL, "Alert Dialog not found ");
			}

		}

		catch(Exception e)
		{

			softAssert.assertTrue(false, " Alert Dialog is not Visible");
			extentTest.log(Status.FAIL, " Alert Dialog is not Visible");
			Log.info("Exception "+e.toString());
			e.printStackTrace();
		}
	}


	/**
	 * This methods identifies if AlertBox is present or not
	 * @param driver
	 * @param timeout
	 * @return
	 * @author Akshay Yelsange
	 */
	public boolean isAlertPresent(WebDriver driver,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if(wait.until(ExpectedConditions.alertIsPresent())!=null)
		   return true;
		else
		    return false;
	}


	/**
	 * This method is used to identify if Alertbox is persent and then return the text present in alert box
	 * @param driver
	 * @param timeout
	 * @return
	 * @author Akshay Yelsange
	 */
	public String getTextFromAlert(WebDriver driver,int timeout)
	{
		String result=null;
		try
		{
			if(isAlertPresent(driver,timeout))
			{
				Alert alert = driver.switchTo().alert();
				result = alert.getText().toString();
				extentTest.log(Status.PASS, "Alert Dialog has text'"+ result);
				return result;
			}
			else
			{

				softAssert.assertTrue(false, "There is no Alert dialog");
				extentTest.log(Status.FAIL, "Alert Dialog not found ");
			}

		}
		catch(Exception e)
		{

			softAssert.assertTrue(false, "Alert Dialog is not Visible");
			extentTest.log(Status.FAIL, "Alert Dialog is not Visible");
			Log.info("Exception "+e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method checks if Alertbox is present for timeout seconds and then Accept the Alert box
	 * @param driver
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void acceptAlert(WebDriver driver,int timeout)
	{
		try
		{
			if(isAlertPresent(driver,timeout))
			{
				Alert alert = driver.switchTo().alert();

				alert.accept();
				extentTest.log(Status.PASS, "Alert Dialog now is Accepted");
			}
			else
			{
				softAssert.assertTrue(false, "There is no Alert dialog");
				extentTest.log(Status.FAIL, "Alert Dialog not found ");
			}


		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Alert Dialog is not Visible");
			extentTest.log(Status.FAIL, "Alert Dialog is not Visible");
			Log.info("Exception "+e.toString());
			e.printStackTrace();
		}
	}
}
