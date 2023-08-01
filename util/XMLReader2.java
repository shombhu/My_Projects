package com.ecrm.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class XMLReader2 {
	public static String testResultFileName = "TestResult.xlsx";
	public static String testResultsheetName = "Result";
	public static String tags[] = {"fields", "nameField"};
	public static String tagsChild[] = {"label", "fullName", "type", "defaultValue", "externalId", "required",
			"precision", "scale", "unique" };

	//variables declaration for storing the data into map and writing onto Excel file
	public XSSFWorkbook wb = new XSSFWorkbook();
	public XSSFSheet spreadsheet = wb.createSheet(testResultsheetName);
	public XSSFRow row;
	public Map<String, Object[]> testData = new TreeMap<>();


	public void XMLReaders(String filePath, String testdataFileName, String sheetName, String filePathXML, String fileNameXML) throws IOException, ParserConfigurationException, SAXException{
		try {

			ExcelReader excelreaderObj = new ExcelReader(filePath, testdataFileName, sheetName);
			int lastRowNum = excelreaderObj.getTotalRowsCount();
			int lastCellNum = excelreaderObj.getTotalColumsCount();

			testData.put("Reference Label", new Object[]{"Reference Label", "Expected", "Actual"});



			//xml file initialization
			File file = new File(filePathXML + "/" + fileNameXML);
			System.out.println("XML File:  " + file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			System.out.println(doc.getDocumentElement().getNodeName());
			int count = 0;
			for (String tag : tags) {

				NodeList nodeList = doc.getElementsByTagName(tag);
				int tLength = nodeList.getLength();
				//System.out.println("Length: " + tLength);
				//
				for (int k = 0; k < tLength; k++) {
					Node node = nodeList.item(k);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println();
						System.out.println("====================================================");

						for (int i = 1; i <= lastRowNum; i++) {
							for (int j = 0; j < lastCellNum; j++) {
								int c = 0;

								if (element.getElementsByTagName(tagsChild[c]).item(0).getTextContent().equals(excelreaderObj.getCellValue(i, 0))) {
									do {
										if (element.getElementsByTagName(tagsChild[c]).getLength() == 1 &&
												excelreaderObj.getCellValue(0, j).equals(tagsChild[c]) &&
												excelreaderObj.getCellValue(i, j) != null &&
												element.getElementsByTagName(tagsChild[c]).item(0).getTextContent().equals(excelreaderObj.getCellValue(i, j))) {
											System.out.println(tagsChild[c] + ":		" + element.getElementsByTagName(tagsChild[c]).item(0).getTextContent() + " is Same as " + excelreaderObj.getCellValue(i, j));
											testData.put(tagsChild[c] + count, new Object[]
													{excelreaderObj.getCellValue(i, 0) + "." + tagsChild[c],
															excelreaderObj.getCellValue(i, j),
															element.getElementsByTagName(tagsChild[c]).item(0).getTextContent()});
											count++;
										}
										c++;
									} while (c < tagsChild.length);
								} else {
									break;
								}
							}
						}
					}
				}
			}
		writeDatatoExcel(filePath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void writeDatatoExcel(String filePath){
		Set<String> keyid = testData.keySet();
		int rowid = 0;

		// writing the data into the sheets...
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = testData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
			wb.write(out);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

