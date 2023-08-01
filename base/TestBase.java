package com.ecrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ecrm.util.CommonUtility;
import com.ecrm.util.Log;
import com.ecrm.util.TestUtil;
import com.google.common.io.Files;

public class TestBase
{
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static SoftAssert soft;
	public static ExtentHtmlReporter htmlReporter;
	String loggerPath = "";

	/**
	 *  This is the Base class Constructor .
	 *  This function loads the config.properties file, and the log4j.xml.
	 */
	public TestBase()
	{
		try
		{
			loggerPath = System.getProperty("user.dir")+"/src/test/java/com/ecrm/config/log4j.xml";
			DOMConfigurator.configure(loggerPath);
			prop=new Properties();
			FileInputStream fip=new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/ecrm/config/config.properties");
			prop.load(fip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This method return the web driver based on the browser name mentioned in config.properties
	 * @returns webdriver instance
	 * @author
	 */
	public static WebDriver getDriver()
	{
		Log.info("Driver being initialized");
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			String driverPath=System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver_100.exe";
			System.setProperty("webdriver.chrome.driver",driverPath);
			driver = new ChromeDriver();
		}
		if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * This methods takes the
	 * @param driver
	 * @param element
	 * and highlight the specific webElement
	 */
	public static void highlight(WebDriver driver, WebElement element){
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='3px solid blue'", element);
			Thread.sleep(200);
			js.executeScript("arguments[0].style.border=''", element);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**This method returns a SoftAssert instance
	 * @return SoftAssert instance
	 * @author
	 */
	public static SoftAssert getSoftAssert()
	{
		soft = new SoftAssert();
		return soft;
	}

	/**This method performs the report initialization by setting the report path, and creating necessary class instances
	 * @param testCase
	 * @return ExtentTest instance
	 */
	public static ExtentTest reportInitialization(String testCase)
	{
		System.setProperty("org.freemarker.loggerLibrary", "none");
		String currentdateTime = CommonUtility.getDateTime();
		String reportPath = System.getProperty("user.dir") + prop.getProperty("reportPath") + "testReport_" + testCase + "_" + currentdateTime +".html";
		htmlReporter = new ExtentHtmlReporter(reportPath);
		// initialize ExtentReports and attach the HtmlReporter
		extent  = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// creating tests
		test = extent.createTest(testCase);
		return test;
	}

	/**
	 * This method writes the content of extent report object into an html file
	 * @author
	 */
	public static void writeToHtmlReport()
	{
		extent.flush();
	}

	/**
	 * This method is used to take screenshots
	 */
	public static String takeScreenshot() throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        String currentdateTime = CommonUtility.getDateTime();
        File source=ts.getScreenshotAs(OutputType.FILE);
        String screenshotpath = System.getProperty("user.dir") + prop.getProperty("screenshotsPath") + "screenshot_"+ currentdateTime+".png";
        File dest=new File(screenshotpath);
        Files.copy(source, dest);
        return screenshotpath;
    }
}