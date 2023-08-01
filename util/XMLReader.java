package com.ecrm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	public static String filePathsource= System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/";
	public static String filePathdest = System.getProperty("user.dir")+"/test-output/testReports/";
	public static String testResultFileName = "ObjectManagerFieldsValidation.xlsx";
	public static String testResultsheetName = "Result";

	public static String tags[] = {"fields", "nameField"};
	public static String tagsChild[] = {"label", "fullName", "type", "defaultValue", "externalId", "required",
			"precision", "scale", "unique" };

	//variables declaration for storing the data into map and writing onto Excel file
	public XSSFWorkbook wb = new XSSFWorkbook();
	public XSSFSheet spreadsheet = wb.createSheet(testResultsheetName);
	public XSSFRow row;

	public void XMLReaders(String filePathsource, String filePathdest, String dataFileName, String filePathXML, String fileNameXML) throws IOException, ParserConfigurationException, SAXException{
		try {
			CopyExcelFile fileCopy = new CopyExcelFile(filePathsource,filePathdest,dataFileName);

			for(int sheetCount=0;sheetCount<fileCopy.getTotalSheetCount();sheetCount++){

				ExcelReader excelreaderObj = new ExcelReader(filePathdest, dataFileName, fileCopy.getCurrentSheet(sheetCount));
				int lastRowNum = excelreaderObj.getTotalRowsCount();
				int lastCellNum = excelreaderObj.getTotalColumsCount();

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
												excelreaderObj.highlightCellwithGREEN(i,j);
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
			//writeDatatoExcel(filePathdest);
			excelreaderObj.fileWriteClose(filePathdest, dataFileName);
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
}

