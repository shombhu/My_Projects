package com.ecrm.commonMethods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.util.Log;

public class WaitMethods {
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public WaitMethods(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
	}


	/**
	 *This method waits until the element is disabled
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void waituntilElementDisabled(WebElement element,int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(!element.isEnabled())
			{

				TestBase.highlight(driver, element);
				Log.info("Element "+element.toString()+" is Now Disabled");
				extentTest.log(Status.PASS, "Element '" + element.toString() + "' now is Disabled");
			}
			else
			{
				if(wait.until(ExpectedConditions.elementToBeClickable(element))==null)
				{
					TestBase.highlight(driver, element);
					Log.info("Element "+element.toString()+" is Now Disabled");
					extentTest.log(Status.PASS, "Element '" + element.toString() + "' now is Disabled");
				}
				else
				{
					Log.info("Element "+element.toString()+" is Enabled");
					extentTest.log(Status.FAIL, "Element '" + element.toString() + "' now Enabled");
				}

			}

		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + element.toString() + "' not found, hence ");
		}
		catch(Exception e)
		{

			softAssert.assertTrue(false, "Element is  Visible");
			extentTest.log(Status.FAIL, "Element is  Visible");
			Log.info("Exception "+e.toString());
			e.printStackTrace();
		}
	}


	/**
	 * 	This method waits until element is Not Visible
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void waitUntilElementNotVisible(WebElement element,int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) == null)
				{
					Log.info("Element "+element.toString()+" is Not Visible");
					extentTest.log(Status.PASS, "Element '" + element.toString() + "' not Visible");
				}
				else
				{
					softAssert.assertTrue(false, "Element is visible, hence operation not performed");
					Log.info("Element "+element.toString()+" is Visible");
					extentTest.log(Status.FAIL, "Element '" + element.toString() + "' Visible");
				}

			}


		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + element.toString() + "' not found, hence ");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Element is  Visible");
			Log.info("Exception "+e.toString());
			extentTest.log(Status.FAIL, "Element is  Visible");
			e.printStackTrace();
		}
	}

	/**
	 * This method waits until element is visible
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void waitUntilElementVisible(WebElement element,int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					//System.out.print(wait.until(ExpectedConditions.visibilityOf(element)));
					Log.info("Element "+element.toString()+" is now Visible");

					extentTest.log(Status.PASS, "Element '" + element.toString() + "' now is Visible");
				}
				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence operation not performed");

				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");

			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + element.toString() + "' not found, hence ");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Element is Not Visible");
			extentTest.log(Status.FAIL, "Element is Not Visible");
			e.printStackTrace();
		}

	}


	/**
	 * Wait until element is Enabled
	 * @param timeout
	 * @author Akshay Yelsange
	 */
	public void waitUntilElementEnabled(WebElement element,int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.elementToBeClickable(element))!=null)
				{
					TestBase.highlight(driver, element);
					Log.info("Element "+element.toString()+" is Now enabled");
					extentTest.log(Status.PASS, "Element '" + element.toString() + "' now is Enabled");

				}
				else
				{
					softAssert.assertTrue(false, "Element is not Enabled, hence value could not be entered");
					extentTest.log(Status.FAIL, "Element '" + element.toString() + "' is not Enabled hence value could not be entered");
					Log.error("Element "+element.toString()+" is not Enabled hence value could not be entered");
				}

			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "There is no such element found.");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Wait unsuccessful");
			extentTest.log(Status.FAIL, "Wait unsuccessful");
			e.printStackTrace();
		}
	}

	public void waitFor(WebDriver driver,int timeout)
    {
        try

        {
            if(driver!=null)
            {
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                extentTest.log(Status.PASS, "Waited for " + timeout + " seconds");
            }
            else
            {
                softAssert.assertTrue(false, "Driver is Null");
                extentTest.log(Status.FAIL, "Driver is Null");
            }


        }
        catch(Exception e)
        {
        	softAssert.assertTrue(false, "Wait Failed");
            extentTest.log(Status.FAIL, "Wait Failed");
            Log.info("Exception "+e.toString());
            e.printStackTrace();
        }
    }
}
