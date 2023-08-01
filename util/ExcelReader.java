package com.ecrm.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class consists of all methods related to Excel file
 *
 * @author
 *
 */
public class ExcelReader {

	public static XSSFWorkbook wb = null;
	public static XSSFWorkbook wb1 = null;
	public static XSSFWorkbook wb2 = null;
	public static XSSFSheet sheet = null;
	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> testdataMap;

	public Cell cell =null;
	public CellStyle style = null;

	public ExcelReader(String filePath, String fileName, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath + "/" + fileName));
		wb1 = new XSSFWorkbook(fis);
		sheet = wb1.getSheet(sheetName);
		fis.close();
		//wb1.close();
	}

	public ExcelReader(String filePath, String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath + "/" + fileName));
		wb2 = new XSSFWorkbook(fis);
		testdataMap = readsheet(wb2);
		wb2.close();
	}

	public int getTotalRowsCount() {
		return sheet.getLastRowNum();
	}

	public int getTotalColumsCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	public boolean verifyMatchedRowNum(int rowNum, int colNum, String value) {
		return sheet.getRow(rowNum).getCell(colNum).toString().equalsIgnoreCase(value);
	}

	public String getCellValue(int rowNum, int colNum) {
		sheet.getRow(rowNum).getCell(colNum).setCellType(CellType.STRING);
		return sheet.getRow(rowNum).getCell(colNum).toString();
	}

	public int identifyColNum(int totalColCount, int rowNum, String value) {
		int identifiedColNum = 0;
		for (int i = 0; i < totalColCount; i++) {
			if (getCellValue(rowNum, i).equalsIgnoreCase(value))
				identifiedColNum = i;
		}
		return identifiedColNum;
	}

	public int indentifyRowNum(int totalRowCount, int colNum, String value) {
		int identifiedRowNum = 0;
		for (int i = 0; i < totalRowCount; i++) {
			if (getCellValue(i, colNum).equalsIgnoreCase(value))
				identifiedRowNum = i;
		}
		return identifiedRowNum;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> readsheet(XSSFWorkbook wb)
			throws IOException {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> sheets = new LinkedHashMap<>();
		LinkedHashMap<String, LinkedHashMap<String, String>> rows = new LinkedHashMap<>();
		LinkedHashMap<String, String> cols;
		int sheetCount = wb.getNumberOfSheets();
		for (int s = 0; s < sheetCount; s++) {
			XSSFSheet sh = wb.getSheetAt(s);
			int rowCount = sh.getLastRowNum();
			int colCount = sh.getRow(0).getLastCellNum();
			XSSFRow excelHeader = sh.getRow(0);
			Cell currentCell = sh.getRow(0).getCell(0);

			for (int i = 1; i < rowCount; i++) {
				cols = new LinkedHashMap<>();
				for (int j = 1; j < colCount; j++) {
					if (sh.getRow(i).getCell(j) != null) {
						currentCell = sh.getRow(i).getCell(j);
						switch (currentCell.getCellType()) {
						case STRING:
							cols.put(excelHeader.getCell(j).getStringCellValue(),
									sh.getRow(i).getCell(j).getStringCellValue());
							break;
						case NUMERIC:
							cols.put(excelHeader.getCell(j).getStringCellValue(),
									String.valueOf(sh.getRow(i).getCell(j).getNumericCellValue()));
							break;
						case BOOLEAN:
							cols.put(excelHeader.getCell(j).getStringCellValue(),
									String.valueOf(sh.getRow(i).getCell(j).getBooleanCellValue()));
						case BLANK:
							cols.put(excelHeader.getCell(j).getStringCellValue(), "");
							break;
						}
					}
				}
				rows.put(sh.getRow(i).getCell(0).getStringCellValue(), cols);
			}
			sheets.put(wb.getSheetName(s), rows);
			rows = new LinkedHashMap<>();
		}

		return sheets;
	}

	public static String getExcelValue(String sheetName, String rowHeader, String columnHeader) throws IOException {
		String cellValue = "";
		if (testdataMap.containsKey(sheetName) && testdataMap.get(sheetName).containsKey(rowHeader)
				&& testdataMap.get(sheetName).get(rowHeader).containsKey(columnHeader)) {
			cellValue = testdataMap.get(sheetName).get(rowHeader).get(columnHeader);
		}
		return cellValue;

	}

	public static ArrayList<String> getRowheaderValueAsListFromTestDataMap(String sheetName) {
		ArrayList<String> rowHeader = new ArrayList<>();
		Iterator<Map.Entry<String, LinkedHashMap<String, String>>> itr = ExcelReader.testdataMap.get(sheetName)
				.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, LinkedHashMap<String, String>> entry = itr.next();
			rowHeader.add(entry.getKey());
		}
		return rowHeader;
	}
	public void highlightCellwithGREEN(int i, int j) throws IOException {
		cell = sheet.getRow(i).getCell(j);
		style = wb1.createCellStyle();
		// Setting Background color
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		cell.setCellStyle(style);

	}

	public void highlightCellwithRED(int i, int j) throws IOException {
		cell = sheet.getRow(i).getCell(j);
		style = wb1.createCellStyle();
		// Setting Background color
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		cell.setCellStyle(style);
	}

	public void fileWriteClose(String fpath, String ffile) throws IOException {
		FileOutputStream out = new FileOutputStream(new File(fpath + "/" + ffile));
		wb1.write(out);
		out.close();
	}
}