package com.ecrm.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

/**
 * This class consists of the test data provider
 * @author Pooja Ahir
 *
 */
public class TestDataParam {

	public static String testdataFileName = "TestData";
	public static String sheetName = "C360-Demographics";
	public static String filePath = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testData";

	@DataProvider(name = "data")
	public static Object[][] dataSupplier(Method m) throws IOException {

		//String filePath = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testData";
		ExcelReader excelreaderObj = new ExcelReader(filePath, testdataFileName, sheetName);

		int lastRowNum = excelreaderObj.getTotalRowsCount();
		int lastCellNum = excelreaderObj.getTotalColumsCount();

		Object[][] obj = null;

		//Identify column number for TestScriptId header
		int testScriptIdColNum = 0;
		for(int a=0; a< lastCellNum; a++)
		{
			if(excelreaderObj.getCellValue(0, a).equalsIgnoreCase("TestScriptId"))
				testScriptIdColNum = a;
		}


		//Get the row number for matching row same as test method name
		int totalMatchedRow = 0;
		ArrayList<Integer> matchedRowsList = new ArrayList<>();
		for(int k = 1; k<= lastRowNum ; k++)
		{
			if(excelreaderObj.verifyMatchedRowNum(k, testScriptIdColNum, m.getName()))
			{
				matchedRowsList.add(k);
				totalMatchedRow++;
			}
		}

		//Initialize object array for totalMatchedRow number of count
		obj = new Object[totalMatchedRow][1];

		//Fetch data for matchedRow from excel
		for (int l = 0; l < matchedRowsList.size(); l++)
		{
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++)
			{
				datamap.put(excelreaderObj.getCellValue(0, j), excelreaderObj.getCellValue(matchedRowsList.get(0), j));
			}
			obj[l][0] = datamap;
		}
		return  obj;
	}
}
