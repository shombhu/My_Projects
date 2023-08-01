package com.ecrm.commonMethods;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.util.ExcelReader;


public class FieldPosition {
	WebDriver driver;
	SoftAssert softAssert;
	ExtentTest extentTest;

	public static String testdataFileName = "FieldList.xlsx";
	public static String sheetName = "AccountPageFields";
	public static String filePath = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/";

	public FieldPosition(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
	}

/*	String [] Expectedlabels= {"Rating", "*Account Name", "Phone","Parent Account", "Fax" , "Account Number", "Website" , "Account Site", "Ticker Symbol", "Type", "Ownership"
			  , "Industry", "Employees" , "Annual Revenue" , "SIC Code", "*Account Currency", "Billing Street", "Billing City", "Billing State/Province"
			  ,"Billing Zip/Postal Code", "Billing Country", "Shipping Street", "Shipping City", "Shipping State/Province", "Shipping Zip/Postal Code"
			  ,"Shipping Country", "Customer Priority","SLA", "SLA Expiration Date","SLA Serial Number" };
	*/

	public void FieldOrder(java.util.List<WebElement> newAccountlabels) throws IOException {

			ExcelReader excelreaderObj = new ExcelReader(filePath, testdataFileName, sheetName);
			//int lastRowNum = excelreaderObj.getTotalRowsCount();
			int lastCellNum = excelreaderObj.getTotalRowsCount();

			ArrayList <String> Expectedlabels = new ArrayList<>();

			int testScriptIdColNum = 0;
			for(int a=1; a< lastCellNum+1; a++)
			{
				Expectedlabels.add(excelreaderObj.getCellValue(a, 0));
			}



			 ArrayList<String> ActualValues= new ArrayList<>();

			 for(WebElement el: newAccountlabels) {

			 ActualValues.add(el.getText());

			 }


			 for(int i=0; i<Expectedlabels.size(); i++) {
			 System.out.println("Expected label value: "+Expectedlabels.get(i));
			 System.out.println("Actual label value: "+ActualValues.get(i));
			 softAssert.assertEquals(ActualValues.get(i), Expectedlabels.get(i), "Both actual and expected are same");
			 }
			}

}

