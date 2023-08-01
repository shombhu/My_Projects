package com.ecrm.commonMethods;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.util.Log;

/**
 * This class consists of all verification methods for a webelement
 * @author Pooja Ahir
 */
public class VerificationMethods
{
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public VerificationMethods(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
	}

	/**This method waits for timeout seconds for an element to be visible and returns true/false
	 * @param element
	 * @param timeout
	 * @return
	 */
	public boolean isElementVisible(WebElement element, int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' is visible.");
					return true;
				}
				else
				{
					softAssert.assertTrue(false, "Waited for " + timeout +" seconds. Element is not visible");
					extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. Element '" +
							ElementState.getElementProperties(element) + "' is not displayed");
					return false;
				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
				return false;
			}

		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "Waited for " + timeout +" seconds. There is no such element found.");
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. There is no such element '"
					+ ElementState.getElementProperties(element) + "' found.");
			return false;
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Waited for " + timeout + " seconds. Element is not visible");
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. Element '" +
					ElementState.getElementProperties(element) + "' is not displayed");
			e.printStackTrace();
			return false;
		}
	}

	/**This method waits for timeout seconds for an element to be visible, returns true/false,
	 * aborts execution if element is not visible.
	 * @param element
	 * @param timeout
	 * @return
	 */
	public boolean isElementVisibleAbort(WebElement element, int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' is visible.");
					return true;
				}
				else
				{
					extentTest.log(Status.FAIL, "Waited for "+ timeout + " seconds."
							+ " Element '" + ElementState.getElementProperties(element) + "' is not visible.");
					return false;
				}
			}
			else
			{
				extentTest.log(Status.FAIL, "Element is null");
				Assert.assertTrue(false, "Element is null");
				return false;
			}

		}
		catch(NoSuchElementException e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. There is no such element '"
					+ ElementState.getElementProperties(element) + "' found.");
			Assert.assertTrue(false, "Element is not visible");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. Element '" +
					ElementState.getElementProperties(element) + "' is not displayed");
			Assert.assertTrue(false, "Element is not visible");
			e.printStackTrace();
			return false;
		}
	}

	/** This method waits for timeout seconds for the element to be visible and then checks if it is enabled
	 * @param element
	 * @param timeout
	 */
	public void isElementEnabled(WebElement element, int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					if(element.isEnabled())
					{
						extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' is enabled.");
					}
					else
					{
						extentTest.log(Status.FAIL, "Waited for "+ timeout + " seconds."
								+ " Element '" + ElementState.getElementProperties(element) + "' is not enabled.");
						softAssert.assertTrue(false, "Element is not visible");
					}
				}
				else
				{
					extentTest.log(Status.FAIL, "Element is either null or not visible");
					softAssert.assertTrue(false, "Element is either null or not visible");
				}

			}
		}
		catch(NoSuchElementException e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. There is no such element '"
					+ ElementState.getElementProperties(element) + "' found.");
			softAssert.assertTrue(false, "Element is not visible");
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. Element '" +
					ElementState.getElementProperties(element) + "' is not displayed");
			softAssert.assertTrue(false, "Element is not visible");
			e.printStackTrace();
		}
	}

	/** This method waits for timeout seconds for the element to be visible and then checks if it is enabled,
	 * aborts if it is not visible or if it not visible
	 * @param element
	 * @param timeout
	 */
	public void isElementEnabledAbort(WebElement element, int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					if(element.isEnabled())
					{
						extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' is enabled.");
					}
					else
					{
						extentTest.log(Status.FAIL, "Waited for "+ timeout + " seconds."
								+ " Element '" + ElementState.getElementProperties(element) + "' is not enabled.");
						Assert.assertTrue(false, "Element is not visible");
					}
				}
				else
				{
					extentTest.log(Status.FAIL, "Element is either null or not visible");
					Assert.assertTrue(false, "Element is either null or not visible");
				}
			}
		}
		catch(NoSuchElementException e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. There is no such element '"
					+ ElementState.getElementProperties(element) + "' found.");
			Assert.assertTrue(false, "Element is not visible");
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "Waited for " + timeout +" seconds. Element '" +
					ElementState.getElementProperties(element) + "' is not displayed");
			Assert.assertTrue(false, "Element is not visible");
			e.printStackTrace();
		}
	}

	/**
	 * This method verifies whether element is disabled or not
	 * @param element Checks
	 * @return
	 * @author Akshay Yelsange
	 */
	public boolean isElementDisabled(WebElement element)
	{

		try
		{
			if(!element.isEnabled())
			{

				extentTest.log(Status.PASS, "Element '" + element.toString() + "' is Disabled.");
				Log.info("Element "+element.toString()+" is Now Disabled");
				return true;
			}
			else
			{
				extentTest.log(Status.FAIL, "Element '" + element.toString() + "' is enabled.");
				Log.info("Element "+element.toString()+" is enabled");
				Assert.assertTrue(false, "Element is Enabled");
				return false;
			}

		}
		catch(NoSuchElementException e)
		{

			extentTest.log(Status.FAIL, "There is no such element '"
					+ element.toString() + "'");
			Assert.assertTrue(false, "No Such Element");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "Element '" +
					element.toString() + "' is not Disabled");
			Assert.assertTrue(false, "Element is Not Disabled");
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * This method verifies whether element is checked or not
	 * @param element
	 * @return
	 * @author Akshay Yelsange
	 */
	public boolean isElementChecked(WebElement element)
	{

		try
		{
			if(element.isSelected())
			{

				extentTest.log(Status.PASS, "Element '" + element.toString() + "' is Checked.");
				Log.info("Element "+element.toString()+" is Now Checked");
				return true;
			}
			else
			{
				extentTest.log(Status.FAIL, "Element '" + element.toString() + "' is not Checked.");
				Log.info("Element "+element.toString()+" is not Checked");
				Assert.assertTrue(false, "Element is not Checked");
				return false;
			}

		}
		catch(NoSuchElementException e)
		{

			extentTest.log(Status.FAIL, "There is no such element '"
					+ element.toString() + "'");
			Assert.assertTrue(false, "No Such Element");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "Element '" +
					element.toString() + "' is not Checked");
			Assert.assertTrue(false, "Element is Not Checked");
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * This method checks whether element is editable or not
	 * @param element
	 * @return
	 * @author Akshay  Yelsange
	 */
	public boolean isElementEditable(WebElement element) {
	    try
	    {
	    	 if(element.isEnabled())

	    	 {

	    		 String fieldNameVal = element.getAttribute("value");
	    		 if(fieldNameVal.equals(""))
	    		 {
	    			 //element.sendKeys("");
	    			 extentTest.log(Status.PASS, "Element '" + element.toString() + "' is Editable.");
					 Log.info("Element "+element.toString()+" is  Editable");

				    	return true;
	    		 }
	    		 else
	    		 {
	    			 	extentTest.log(Status.FAIL, "Element '" + element.toString() + "' is not Editable.");
						Log.info("Element "+element.toString()+" is not Editable");
						Assert.assertTrue(false, "Element is not Editable");
				    	return false;
	    		 }


	    	 }
	    	 else
	    	 {
	    		 	extentTest.log(Status.FAIL, "Element '" + element.toString() + "' is not Editable.");
					Log.info("Element "+element.toString()+" is not Editable");
					Assert.assertTrue(false, "Element is not Editable");
			    	return false;
	    	 }

	    }
	    catch(NoSuchElementException e)
		{

			extentTest.log(Status.FAIL, "There is no such element '"
					+ element.toString() + "'");
			Assert.assertTrue(false, "No Such Element");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "'Exception " +
					element.toString() + "'"+ e.toString());
			Assert.assertTrue(false, "Element is Not Editable");
			e.printStackTrace();
			return false;
		}


	}

	 /**
	  * This method waits for the element to be visible for timeout seconds and then verifies the text of element with expectedresult value
	 * @param element
	 * @param timeout
	 * @author Akshay Yelsange
	 * @throws IOException
	 */
	public  boolean verifyTextWhenVisible(WebElement element, int timeout,String expectedresult) throws IOException
	 {
		String imagePath = TestBase.takeScreenshot();
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					if(element.isDisplayed())
					{
						if(element.getText().equals(expectedresult))
						{
							extentTest.log(Status.PASS, "Text for Element '" + element.toString() + "' is verified."
									+ "Expected Text: '" + expectedresult + "', Actual Text: '" + element.getText() + "'.");
							return true;
						}
						else
						{
							extentTest.log(Status.FAIL," Element '" + element.toString() + "'text not Verified"
									+ "Expected Text: '" + expectedresult + "', Actual Text: '" + element.getText() + "'.");
							extentTest.fail("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
							softAssert.assertTrue(false, "Element is not visible");
							Log.info(imagePath);
							extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
							return false;
						}

					}

				}
				else
				{
					extentTest.log(Status.FAIL," Element '" + element.toString() + "' is not Displayed");
					softAssert.assertTrue(false, "Element is not visible");
					extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
					return false;
				}


			}
			else
			{
				extentTest.log(Status.FAIL, "Element is either null or not visible");
				softAssert.assertTrue(false, "Element is either null or not visible");
				return false;
			}
		}
		catch(NoSuchElementException e)
		{

			extentTest.log(Status.FAIL, "There is no such element '"
					+ element.toString() + "'");
			Assert.assertTrue(false, "No Such Element");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "'Exception " +
					element.toString() + "'"+ e.toString());
			Assert.assertTrue(false, "Element is Not Visible");
			e.printStackTrace();
			return false;

		}
		return false;


	 }


	/**
	 *
	 * This method waits for the element to be visible for timeout seconds
	 * and then Verify the attribute value of element with expected result
	 * @param element
	 * @param attribute
	 * @param expectedresult
	 * @param timeout
	 * @return
	 * @author Akshay Yelsange
	 */
	public boolean verifyAttributeWhenVisible(WebElement element,String attribute,String expectedresult,int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);

			if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
			{
				TestBase.highlight(driver, element);
				if(element.isDisplayed())
				{
					if(element.getAttribute(attribute).equals(expectedresult))
					{
						extentTest.log(Status.PASS, "Attribute value for element '" + element.toString() + "' verified."
								+ "Expected attribute value: '" + expectedresult + "', Actual attribute value: '" +
								element.getAttribute(attribute) + "', for attribute " + attribute);
						System.out.println("Attribue are visible and verified");//simple
						return true;
					}
					else
					{
						extentTest.log(Status.FAIL,"Attribute value for element '" + element.toString() + "' not verified."
								+ "Expected attribute value: '" + expectedresult + "', Actual attribute value: '" +
								element.getAttribute(attribute) + "', for attribute " + attribute);
						softAssert.assertTrue(false, "Element do not contains the expected Text");
						System.out.println("Attribue are getting fail");//simple
					}

				}

			}
			else
			{

					extentTest.log(Status.FAIL," Element '" + element.toString() + "' is not Visible");
					softAssert.assertTrue(false, "Element is not visible");
					return false;

			}

		}
		catch(NoSuchElementException e)
		{

			extentTest.log(Status.FAIL, "There is no such element '"
					+ element.toString() + "'");
			Assert.assertTrue(false, "No Such Element");
			return false;
		}
		catch(Exception e)
		{
			extentTest.log(Status.FAIL, "'Exception " +
					element.toString() + "'"+ e.toString());
			Assert.assertTrue(false, "Element is Not Visible");
			e.printStackTrace();
			return false;

		}
		return false;
	}


/**This method waits for the element to be visible for timeout seconds
 * and then verifies whether element contains the  expectedresult value
 * @param element
 * @param timeout
 * @param expectedresult
 * @return
 * @author Akshay Yelsange
 */
public boolean verifyContainsTextWhenVisible(WebElement element, int timeout,String expectedresult)
{

	try
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if(element != null)
		{
			if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
			{
				TestBase.highlight(driver, element);
				if(element.isDisplayed())
				{
					if(element.getText().contains(expectedresult))
					{
						extentTest.log(Status.PASS, "Element '" + element.toString() + "' contains the expected"
								+ " text '" + expectedresult + "'");
						return true;
					}
					else
					{
						extentTest.log(Status.FAIL," Element '" + element.toString() + "' do not contains '"+ expectedresult + "'");
						softAssert.assertTrue(false, "Element do not contains the expected Text");
						return false;
					}

				}

			}
			else
			{
				extentTest.log(Status.FAIL," Element '" + element.toString() + "' is not Visible");
				softAssert.assertTrue(false, "Element is not visible");
				return false;
			}


		}
		else
		{
			extentTest.log(Status.FAIL, "Element is either null or not visible");
			softAssert.assertTrue(false, "Element is either null or not visible");
			return false;
		}

	}
	catch(NoSuchElementException e)
	{

		extentTest.log(Status.FAIL, "There is no such element '"
				+ element.toString() + "'");
		Assert.assertTrue(false, "No Such Element");
		return false;
	}
	catch(Exception e)
	{
		extentTest.log(Status.FAIL, "'Exception " +
				element.toString() + "'"+ e.toString());
		Assert.assertTrue(false, "Element is Not Visible");
		e.printStackTrace();
		return false;

	}
	return false;
}
}