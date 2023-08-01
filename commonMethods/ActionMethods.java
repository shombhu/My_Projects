package com.ecrm.commonMethods;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ecrm.base.TestBase;
import com.ecrm.util.Log;

/**
 * @author Pooja Ahir
 * This class consists of all common action methods for a webelement.
 */
public class ActionMethods
{
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public ActionMethods(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
	}


	/**
	 * This method waits for the element to be visible for timeout seconds, and then clicks on it once visible
	 * @author Pooja Ahir
	 * @param element
	 * @param timeout
	 * @throws IOException
	 */
	public void clickElementWhenVisible(WebElement element, int timeout) throws IOException
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
					element.click();
					extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' clicked successfully");
				}
				else
				{
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' is not visible."
							+ "Hence not clicked.");
					extentTest.fail("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
					softAssert.assertTrue(false, "Element is not visible, hence not clicked");
					Log.info(imagePath);
					extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());

				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
				Log.info(imagePath);
				extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' is not found."
					+ "Hence not clicked.");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Click Unsuccessful");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not clicked.");
			e.printStackTrace();
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}
	}

	/**
	 * This method clicks on an element defined by the argumented locator
	 * @param locator
	 * @param timeout
	 * @author Pooja Ahir
	 */
	public void clickElementWhenVisible(By locator, int timeout)
	{
		WebElement element = null;
		try
		{
			element = driver.findElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					element.click();
					extentTest.log(Status.PASS, "Element '" + ElementState.getElementProperties(element) + "' clicked successfully");
				}
				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence not clicked");
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' is not visible."
							+ "Hence not clicked.");
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
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' is not found."
					+ "Hence not clicked.");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Click Unsuccessful");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not clicked.");
			e.printStackTrace();
		}
	}

	/**
	 * This method enters the argument value in specific web element after waiting for timeout seconds for it to be visible
	 * @param element
	 * @param value
	 * @param timeout
	 */
	public void enterValueWhenVisible(WebElement element, String value, int timeout)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			if(element != null)
			{
				if(wait.until(ExpectedConditions.visibilityOf(element)) != null)
				{
					TestBase.highlight(driver, element);
					element.sendKeys(value);
					extentTest.log(Status.PASS, "Value '" + value + "' entered in the "
							+ "element '" + ElementState.getElementProperties(element) + "' successfully");
				}
				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence value could not be entered");
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not visible, hence "
							+ "value '" + value + "' could not be entered.");
				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null, hence "
						+ "value '" + value + "' could not be entered.");
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not found, hence "
					+ "value '" + value + "' could not be entered.");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Entering value unsuccessful.");
			extentTest.log(Status.FAIL, "Entering value unsuccessful.");
			e.printStackTrace();
		}
	}

	/**
	 * This method refreshes the webpage
	 * @author Lalita Chhabra
	 */

	public void refreshWebPage() throws InterruptedException{
		try{
			driver.navigate().refresh();
			Thread.sleep(3000);
			extentTest.log(Status.PASS, "Webpage refreshed.");
		}
		catch(InterruptedException e){
			softAssert.assertTrue(false, "Webpage could not be refreshed due to an exception.");
			extentTest.log(Status.FAIL, "Webpage could not be refreshed due to an exception.");
			e.printStackTrace();
		}

	}

	/**
	 * This method returns url of the currently opened browser
	 * @return current url
	 * @author Lalita Chhabra
	 */
	public String getCurrentUrl()
	{
		extentTest.log(Status.PASS, "Getting the current url as: '" + driver.getCurrentUrl() + "'");
		return driver.getCurrentUrl();
	}


	/**
	 * This method is used to kill a specific process passed as an argument
	 * @param processName
	 * @author Lalita Chhabra
	 */
	public void processKill(String processName){
		try
		{
			if(WindowsUtils.thisIsWindows())
			{
				WindowsUtils.killByName(processName);
				extentTest.log(Status.PASS, "Windows process '" + processName + "' killed successfully");
			}
		}
		catch (Exception e)
		{
			softAssert.assertTrue(false, "Windows process '" + processName + "' could not be killed due to an unexpected error");
			extentTest.log(Status.FAIL, "Windows process '" + processName + "' could not be killed due to an unexpected error");
			e.printStackTrace();
		}
	}

	/**
	 * This method select elements from drop down for elements which don't have 'select' tag, we can provide the custom xpath for the option.
	 * @author simple.sahu
	 * @param dropdown
	 * @param optionxpath
	 * @param option
	 */
	public void selectFromDropdownWhenVisible(WebElement dropdown, String optionxpath, String option)
	{
		try {
			if(dropdown != null)
			{
				if(dropdown.isDisplayed())
				{
					TestBase.highlight(driver, dropdown);
					dropdown.click();
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					WebElement drpdownRatingValue= driver.findElement(By.xpath(optionxpath.replace("?", option)));
					TestBase.highlight(driver, drpdownRatingValue);
					drpdownRatingValue.click();
					extentTest.log(Status.PASS, "'" + option + "' selected from dropdown '" + ElementState.getElementProperties(dropdown) + "' successfully");

				}
				else
				{
					softAssert.assertTrue(false, "Dropdown is not visible, hence the value could not be selected");

					extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' is not visible."
							+ "Hence value is not selected.");

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
			extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not found, hence "
					+ "value '" + option + "' could not be selected.");
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Selecting value unsuccessful.");
			extentTest.log(Status.FAIL, "Selecting value unsuccessful.");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to Scroll Down the window
	 * @author Lalita Chhabra
	 */
	public void scrollDown() {
		try{
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			extentTest.log(Status.PASS, "Webpage ScrollDown executed");
		}
		catch(Exception e){
		softAssert.assertTrue(false, "Webpage ScrollDown not executed due to an exception.");
		extentTest.log(Status.FAIL, "Webpage ScrollDown not executed due to an exception.");
		e.printStackTrace();
		}
	}

	/**
	 * This method scrolls to a specific webelement
	 * @param element
	 * @author Pooja Ahir
	 */
	public void scrollIntoViewWhenVisible(WebElement element)
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);",element);
			extentTest.log(Status.PASS, "Scrolled till element '" + ElementState.getElementProperties(element) + "'");
		}
		catch(Exception e)
		{
			softAssert.assertTrue(false, "Scroll till element '" + ElementState.getElementProperties(element) + "' failed");
			extentTest.log(Status.FAIL, "Scroll till element '" + ElementState.getElementProperties(element) + "' failed");
			e.printStackTrace();
		}
	}

	/**
	 * This method navigate to URL
	 * @author simple.sahu
	 * @param url
	 */

	public void navigateToURL(String url)

	{
		try {
			if(driver != null)

			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.navigate().to(url);
				System.out.println("Navigated to URL successfully");
			}

			else
			{
				Assert.assertTrue(false, "Driver is not initiated");
			}

		}
		catch(Exception e) {
			Assert.assertTrue(false, "Entering url unsuccessful.");
			extentTest.log(Status.FAIL, "Entering url unsuccessful.");
			e.printStackTrace();
		}
	}


	/**
	 * This method selects option from drop down by index
	 * @author simple.sahu
	 * @param dropdown
	 * @param ddlIndex
	 */
	public void selectByIndexWhenVisible(WebElement dropdown, int ddlIndex)
	{
		try {
			if(dropdown != null)
			{
				if(dropdown.isDisplayed())
				{
					TestBase.highlight(driver, dropdown);
					dropdown.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Select select = new Select(dropdown);
					select.selectByIndex(ddlIndex);
					System.out.println("Element in dropdown is selected");
					extentTest.log(Status.PASS, "Index: '" + ddlIndex + "' entered in the "
							+ "dropdown '" + ElementState.getElementProperties(dropdown) + "' successfully");
				}
				else
				{
					softAssert.assertTrue(false, "Dropdown is not visible, hence value could not be selected");
					extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not visible, hence "
							+ "index '" + ddlIndex + "' could not be entered.");
				}

			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null, hence "
						+ "index '" + ddlIndex + "' could not be entered.");
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not found, hence "
					+ "index '" + ddlIndex + "' could not be entered.");
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Selecting value unsuccessful.");
			extentTest.log(Status.FAIL, "Selecting value unsuccessful.");
			e.printStackTrace();
		}
	}


	/**
	 * This method selects option from drop down by Visible Text
	 * @author simple.sahu
	 * @param dropdown
	 * @param ddlText
	 */
	public void selectByTextWhenVisible(WebElement dropdown, String ddlText)
	{
		try {
			if(dropdown != null)
			{
				if(dropdown.isDisplayed())
				{
					TestBase.highlight(driver, dropdown);
					Select select = new Select(dropdown);
					select.selectByVisibleText(ddlText);
					System.out.println("Element in dropdown is selected");
					extentTest.log(Status.PASS, "Text: '" + ddlText + "' entered in the "
							+ "dropdown '" + ElementState.getElementProperties(dropdown) + "' successfully");
				}
				else
				{
					softAssert.assertTrue(false, "Dropdown is not visible, hence value could not be selected");
					extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not visible, hence "
							+ "text '" + ddlText + "' could not be entered.");
				}

			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null, hence "
						+ "text '" + ddlText + "' could not be entered.");
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not found, hence "
					+ "text '" + ddlText + "' could not be entered.");
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Selecting value unsuccessful.");
			extentTest.log(Status.FAIL, "Selecting value unsuccessful.");
			e.printStackTrace();
		}
	}

	/**
	 * This method selects option from drop down by Visible value
	 * @author simple.sahu
	 * @param dropdown
	 * @param ddlValue
	 */

	public void selectByValueWhenVisible(WebElement dropdown, String ddlValue)
	{
		try {
			if(dropdown != null)
			{
				if(dropdown.isDisplayed())
				{
					TestBase.highlight(driver, dropdown);
					Select select = new Select(dropdown);
					select.selectByValue(ddlValue);
					System.out.println("Element in dropdown is selected");
					extentTest.log(Status.PASS, "Value: '" + ddlValue + "' entered in the "
							+ "dropdown '" + ElementState.getElementProperties(dropdown) + "' successfully");
				}
				else
				{
					softAssert.assertTrue(false, "Dropdown is not visible, hence value could not be selected");
					extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not visible, hence "
							+ "value '" + ddlValue + "' could not be entered.");
				}

			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null, hence "
						+ "value '" + ddlValue + "' could not be entered.");
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not found, hence "
					+ "value '" + ddlValue + "' could not be entered.");
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Selecting value unsuccessful.");
			extentTest.log(Status.FAIL, "Selecting value unsuccessful.");
			e.printStackTrace();
		}
	}


	/**
	 * This method used  to return the already selected value in dropdown
	 * @author simple.sahu
	 * @param dropdown
	 */

	public void getFirstSelectedOption(WebElement dropdown)
	{
		try {
			if(dropdown != null)
			{
				if(dropdown.isDisplayed())
				{

					TestBase.highlight(driver, dropdown);
					Select select = new Select(dropdown);
					WebElement option= select.getFirstSelectedOption();
					String selectedOption= option.getText();
					System.out.println(selectedOption + "is the first selected option in dropdown");
					extentTest.log(Status.PASS, "Text: '" + selectedOption + "' is the first selected option in the "
							+ "dropdown '" + ElementState.getElementProperties(dropdown));

				}

				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence not clicked");
					extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not visible");
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
			extentTest.log(Status.FAIL, "Dropdown '" + ElementState.getElementProperties(dropdown) + "' not found");
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Retriving  value unsuccessful.");
			extentTest.log(Status.FAIL, "Retriving value unsuccessful.");
			e.printStackTrace();
		}
	}

	/**
	 * This method retrieve text of a visible web element
	 * @author simple.sahu
	 * @param element
	 */

	public String getTextWhenVisible(WebElement element)
	{
		String textElement = "";
		try {
			if(element!= null)
			{
				if(element.isDisplayed())
				{

					TestBase.highlight(driver, element);
					textElement= element.getText();
					extentTest.log(Status.PASS, "Text '" + textElement + "' is retrieved ");

				}

				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence the value could not be retrieved");
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not visible");
				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
			}
			return textElement;
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not found");
			return textElement;
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Retriving  value unsuccessful.");
			extentTest.log(Status.FAIL, "Retriving value unsuccessful.");
			e.printStackTrace();
			return textElement;
		}
	}

	/**
	 * This method retrieve attribute of a visible  web element
	 * @author simple.sahu
	 * @param element
	 * @param  Attribute
	 */

	public String getAttributeWhenVisible(WebElement element, String Attribute)
	{
		String elementAttributeText = "";
		try {
			if(element!= null)
			{
				if(element.isDisplayed())
				{

					TestBase.highlight(driver, element);
					elementAttributeText= element.getAttribute(Attribute);
					extentTest.log(Status.PASS, "Attribute: '" + elementAttributeText + "' is retrieved ");

				}

				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence the attribute value could not be retrieved");
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not visible");
				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
			}
			return elementAttributeText;
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not found");
			return elementAttributeText;
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Retriving  attribute unsuccessful.");
			extentTest.log(Status.FAIL, "Retriving attribute unsuccessful.");
			e.printStackTrace();
			return elementAttributeText;
		}
	}


	/**
	 * This method retrieve CssValue of a visible  web element
	 * @author simple.sahu
	 * @param element
	 * @param cssValue
	 */

	public String getCssValueWhenVisible(WebElement element, String  cssValue )
	{
		String elementCssValue = "";
		try {
			if(element!= null)
			{
				if(element.isDisplayed())
				{

					TestBase.highlight(driver, element);
					elementCssValue= element.getCssValue(cssValue);
					extentTest.log(Status.PASS, "CSS value: '" + elementCssValue + "' is retrived ");

				}

				else
				{
					softAssert.assertTrue(false, "Element is not visible, hence not clicked");
					extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not visible");
				}
			}
			else
			{
				softAssert.assertTrue(false, "Element is null");
				extentTest.log(Status.FAIL, "Element is null");
			}
			return elementCssValue;
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not found");
			return elementCssValue;
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "Retriving  css value unsuccessful.");
			extentTest.log(Status.FAIL, "Retriving css value unsuccessful.");
			e.printStackTrace();
			return elementCssValue;
		}
	}

	/**
	 * This method is use to switch between frames on a web page.
	 * @author simple.sahu
	 * @param element
	 * @throws IOException
	 */

	public void switchToFrameByWebElement(WebElement element) throws IOException
	{
		String imagePath = TestBase.takeScreenshot();
		try {

		if(element!= null)
		{
			if(element.isDisplayed())
			{

				TestBase.highlight(driver, element);
				driver.switchTo().frame(element);
				System.out.println("Frame is Switched");
				extentTest.log(Status.PASS, "Frame: '" + ElementState.getElementProperties(element) + "' switched successfully");

			}

			else
			{
			softAssert.assertTrue(false, "Frame is not visible, hence not clicked");
			extentTest.log(Status.FAIL, "Frame '" + ElementState.getElementProperties(element) + "' not visible");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
			}
	    }
		else
		{
			softAssert.assertTrue(false, "Element is null");
			extentTest.log(Status.FAIL, "Element is null");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + ElementState.getElementProperties(element) + "' not found");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());

		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "iframe access unsuccessful.");
			extentTest.log(Status.FAIL, "iframe access unsuccessful.");
			e.printStackTrace();
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}
	}

	/**
	 * This method is use to select the frame by locator on the webpage
	 * @author simple.sahu
	 * @param element
	 * @throws IOException
	 */

	public void switchToFrameByLocator(String locator) throws IOException
	{
		String imagePath = TestBase.takeScreenshot();
		try {

		if(locator!= null)
		{
				WebElement element = driver.findElement(By.name(locator));
		        driver.switchTo().frame(element);
				System.out.println("Switched frame as per the locator");
				extentTest.log(Status.PASS, "Frame: '" + locator + "' switched successfully");
		}
			else
			{
			softAssert.assertTrue(false, "Frame is not visible, hence not clicked");
			extentTest.log(Status.FAIL, "Frame '" + locator + "' not visible");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
			}
		}
		catch(NoSuchElementException e)
		{
			softAssert.assertTrue(false, "There is no such element found.");
			extentTest.log(Status.FAIL, "Element '" + locator + "' not found");
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}

		catch(Exception e)
		{
			softAssert.assertTrue(false, "iframe access unsuccessful.");
			extentTest.log(Status.FAIL, "iframe access unsuccessful.");
			e.printStackTrace();
			Log.info(imagePath);
			extentTest.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(TestBase.takeScreenshot()).build());
		}
  }
}