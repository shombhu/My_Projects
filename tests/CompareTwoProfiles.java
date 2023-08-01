package com.ecrm.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.ecrm.util.ExcelReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CompareTwoProfiles {
	public String testResultFileName = "ProfileComparision.xlsx";
	public String sheet1 = "applicationVisibilities_Exp";
	public String sheet2 = "applicationVisibilities_Actual";
	public String filePath = System.getProperty("user.dir")+"/test-output/testReports/";
	public String filePath1 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\SatyaTesting1.profile";
	public String filePath2 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\Read Only.profile";
	public String tags[] = {"applicationVisibilities", "classAccesses", "fieldPermissions",
			"layoutAssignments", "objectPermissions", "pageAccesses", "recordTypeVisibilities",
			"tabVisibilities", "userPermissions" };
/*	public String tagsChild[] = {"application", "default", "visible", "apexClass", "enabled",
			"field", "editable", "readable", "layout", "object", "allowCreate", "allowDelete",
			"allowEdit", "allowRead", "modifyAllRecords", "viewAllRecords", "apexPage", "apexPage",
			"tab", "visibility", "recordType", "name"};
	String applicationVisibilities[] = {"application", "default", "visible"};
	String classAccesses[] = {"apexClass", "enabled"};
	String fieldPermissions[] = {"field", "editable", "readable"};
	String layoutAssignments[] = {"layout"};
	String objectPermissions[] = {"object", "allowCreate", "allowDelete", "allowEdit", "allowRead", "modifyAllRecords", "viewAllRecords"};
	String pageAccesses[] = {"apexPage", "enabled"};
	String recordTypeVisibilities[] = {"recordType", "default", "visible"};
	String tabVisibilities[] = {"tab", "visibility"};
	String userPermissions[] = {"name","enabled"};

 */

	public static XSSFWorkbook wb = new XSSFWorkbook();
	//public XSSFSheet spreadsheet = wb.createSheet(testResultsheetName);
	//public XSSFSheet spreadsheet2 = wb.createSheet(testResultsheetName2);

	public XSSFRow row;
	public Map<String, Object[]> testData1 = new TreeMap<>();
	public Map<String, Object[]> testData2 = new TreeMap<>();

	@Test
  public void XMLFileReaderTest() throws NullPointerException {
	  try{
		    File file = new File(filePath1);
		    System.out.println("File1:  "+file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());

			File file1 = new File(filePath2);
			System.out.println("File2:  "+file1);
			DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder1 = dbf1.newDocumentBuilder();
			Document doc1 = dBuilder1.parse(file1);
			doc1.getDocumentElement().normalize();
			System.out.println(doc1.getDocumentElement().getNodeName());

			System.out.println("Total Nodes in File1 : "+doc.getDocumentElement().getNodeName());
			System.out.println("Total Nodes in File2 : "+doc1.getDocumentElement().getNodeName());

			for (String tag : tags) {
				switch (tag) {

					case "applicationVisibilities": {
						testData1.put("Reference Label", new Object[]{"application", "default", "visible"});
						testData2.put("Reference Label", new Object[]{"application", "default", "visible"});

						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);
						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;

								testData1.put(element.getNodeName() + element.getElementsByTagName("application").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("application").item(0).getTextContent(),
										element.getElementsByTagName("default").item(0).getTextContent(),
										element.getElementsByTagName("visible").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("application").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("application").item(0).getTextContent(),
										element1.getElementsByTagName("default").item(0).getTextContent(),
										element1.getElementsByTagName("visible").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Exp");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"_Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						//excelComparision(spreadsheet.getSheetName(), spreadsheet2.getSheetName());

						break;
					}//case

					case "classAccesses": {
						testData1.put("Reference Label", new Object[]{"apexClass", "enabled"});
						testData2.put("Reference Label", new Object[]{"apexClass", "enabled"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;

								testData1.put(element.getNodeName() + element.getElementsByTagName("apexClass").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("apexClass").item(0).getTextContent(),
										element.getElementsByTagName("enabled").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("apexClass").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("apexClass").item(0).getTextContent(),
										element1.getElementsByTagName("enabled").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "fieldPermissions": {
						testData1.put("Reference Label", new Object[]{"field", "editable", "readable"});
						testData2.put("Reference Label", new Object[]{"field", "editable", "readable"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;

								testData1.put(element.getNodeName() + element.getElementsByTagName("field").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("field").item(0).getTextContent(),
										element.getElementsByTagName("editable").item(0).getTextContent(),
										element.getElementsByTagName("readable").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("field").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("field").item(0).getTextContent(),
										element1.getElementsByTagName("editable").item(0).getTextContent(),
										element.getElementsByTagName("readable").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "layoutAssignments": {
						testData1.put("Reference Label", new Object[]{"layout"});
						testData2.put("Reference Label", new Object[]{"layout"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("layout").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("layout").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("layout").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("layout").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "objectPermissions": {
						testData1.put("Reference Label", new Object[]{"object", "allowCreate", "allowDelete", "allowEdit","allowRead", "modifyAllRecords", "viewAllRecords"});
						testData2.put("Reference Label", new Object[]{"object", "allowCreate", "allowDelete", "allowEdit","allowRead", "modifyAllRecords", "viewAllRecords"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("object").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("object").item(0).getTextContent(),
										element.getElementsByTagName("allowCreate").item(0).getTextContent(),
										element.getElementsByTagName("allowDelete").item(0).getTextContent(),
										element.getElementsByTagName("allowEdit").item(0).getTextContent(),
										element.getElementsByTagName("allowRead").item(0).getTextContent(),
										element.getElementsByTagName("modifyAllRecords").item(0).getTextContent(),
										element.getElementsByTagName("viewAllRecords").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("object").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("object").item(0).getTextContent(),
										element1.getElementsByTagName("allowCreate").item(0).getTextContent(),
										element.getElementsByTagName("allowDelete").item(0).getTextContent(),
										element.getElementsByTagName("allowEdit").item(0).getTextContent(),
										element.getElementsByTagName("allowRead").item(0).getTextContent(),
										element.getElementsByTagName("modifyAllRecords").item(0).getTextContent(),
										element.getElementsByTagName("viewAllRecords").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "pageAccesses": {
						testData1.put("Reference Label", new Object[]{"apexPage", "enabled"});
						testData2.put("Reference Label", new Object[]{"apexPage", "enabled"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("apexPage").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("apexPage").item(0).getTextContent(),
										element.getElementsByTagName("enabled").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("apexPage").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("apexPage").item(0).getTextContent(),
										element.getElementsByTagName("enabled").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "tabVisibilities": {
						testData1.put("Reference Label", new Object[]{"tab", "visibility"});
						testData2.put("Reference Label", new Object[]{"tab", "visibility"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("tab").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("tab").item(0).getTextContent(),
										element.getElementsByTagName("visibility").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("tab").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("tab").item(0).getTextContent(),
										element.getElementsByTagName("visibility").item(0).getTextContent()});
							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "recordTypeVisibilities": {
						testData1.put("Reference Label", new Object[]{"recordType", "default", "visible"});
						testData2.put("Reference Label", new Object[]{"recordType", "default", "visible"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("recordType").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("recordType").item(0).getTextContent(),
										element.getElementsByTagName("default").item(0).getTextContent(),
										element.getElementsByTagName("visible").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("recordType").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("recordType").item(0).getTextContent(),
										element1.getElementsByTagName("default").item(0).getTextContent(),
										element.getElementsByTagName("visible").item(0).getTextContent()});
							}

						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "userLicense": {
						testData1.put("Reference Label", new Object[]{"userLicense",});
						testData2.put("Reference Label", new Object[]{"userLicense"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						System.out.println(tag + " Length" + tLength);

						for (int i = 0; i < tLength; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("userLicense").item(0).getTextContent(), new Object[]
										{element.getNodeName() + "." + element.getElementsByTagName("userLicense").item(0).getTextContent(),
												element.getElementsByTagName("userLicense").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("userLicense").item(0).getTextContent(), new Object[]
										{element1.getNodeName() + element1.getElementsByTagName("userLicense").item(0).getTextContent(),
												element1.getElementsByTagName("userLicense").item(0).getTextContent()});

							}

						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

					case "userPermissions": {
						testData1.put("Reference Label", new Object[]{"name", "enabled"});
						testData2.put("Reference Label", new Object[]{"name", "enabled"});
						NodeList nodeList = doc.getElementsByTagName(tag);
						NodeList nodeList1 = doc1.getElementsByTagName(tag);
						int tLength = nodeList.getLength();
						int tLength1 = nodeList1.getLength();
						System.out.println(tag + " Length1: " + tLength);
						System.out.println(tag + " Length2: " + tLength1);


						for (int i = 0; i < tLength && i < tLength1; i++) {
							Node node = nodeList.item(i);
							Node node1 = nodeList1.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE && node1.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								Element element1 = (Element) node1;
								testData1.put(element.getNodeName() + element.getElementsByTagName("name").item(0).getTextContent(), new Object[]
										{element.getElementsByTagName("name").item(0).getTextContent(),
										element.getElementsByTagName("enabled").item(0).getTextContent()});
								testData2.put(element1.getNodeName() + "." + element1.getElementsByTagName("name").item(0).getTextContent(), new Object[]
										{element1.getElementsByTagName("name").item(0).getTextContent(),
										element.getElementsByTagName("enabled").item(0).getTextContent()});

							}
						}
						XSSFSheet spreadsheet = wb.createSheet(tag+"_Expected");
						XSSFSheet spreadsheet2 = wb.createSheet(tag+"Actual");
						writeDatatoExcel(spreadsheet, testData1);
						writeDatatoExcel(spreadsheet2, testData2);
						testData1.clear();
						testData2.clear();
						break;
					}//case

				}//switch
			}

		  FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
		  wb.write(out);
		  out.close();
		  excelComparision(sheet1, sheet2);

	  }catch (Exception e){
		e.printStackTrace();
	  }
/*
		try{
			ExcelReader excelreaderObj = new ExcelReader(filePath, testResultFileName, sheet1);
			ExcelReader excelreaderObj2 = new ExcelReader(filePath, testResultFileName, sheet2);
			int lastRowNum = excelreaderObj.getTotalRowsCount();
			int lastCellNum = excelreaderObj.getTotalColumsCount();
			for (int k=1; k<lastRowNum;k++) {
				for (int l = 0; l < lastCellNum; l++) {
					//System.out.println("Excel1:"+excelreaderObj.getCellValue(k,l));
					if (excelreaderObj.getCellValue(k, l).equals(excelreaderObj2.getCellValue(k,l))) {
						excelreaderObj.highlightCellwithGREEN(k, l);
						excelreaderObj2.highlightCellwithGREEN(k, l);
					} else {
						excelreaderObj.highlightCellwithRED(k, l);
						excelreaderObj2.highlightCellwithRED(k, l);
					}
				}
				excelreaderObj.fileWriteClose(filePath,testResultFileName);
				//excelreaderObj2.fileWriteClose(filePath,testResultFileName);
			}
			//FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
			//wb.write(out);
			//out.close();
			//excelreaderObj1.fileWriteClose();
			//excelreaderObj2.fileWriteClose();
		} catch(Exception e) {
			e.printStackTrace();
		}


 */
	}
	public void writeDatatoExcel(XSSFSheet spreadsheet, Map<String, Object[]> testData ){
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

	/*	try {
			FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
			wb.write(out);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}*/
	}

	public void excelComparision(String sheet1, String sheet2){
		try{
			ExcelReader excelreaderObj1 = new ExcelReader(filePath, testResultFileName, sheet1);
			ExcelReader excelreaderObj2 = new ExcelReader(filePath, testResultFileName, sheet2);
			int lastRowNum = excelreaderObj1.getTotalRowsCount();
			int lastCellNum = excelreaderObj1.getTotalColumsCount();
			for (int k=1; k<lastRowNum;k++) {
				for (int l = 0; l < lastCellNum; l++) {
					//System.out.println("Excel1:"+excelreaderObj1.getCellValue(k,l));
					if (excelreaderObj1.getCellValue(k, l).equals("true")) {
						excelreaderObj1.highlightCellwithGREEN(k, l);
						excelreaderObj2.highlightCellwithGREEN(k, l);
					} else {
						excelreaderObj1.highlightCellwithRED(k, l);
						excelreaderObj2.highlightCellwithRED(k, l);
					}
				}
			}
			//FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
			//wb.write(out);
			//out.close();
			excelreaderObj1.fileWriteClose(filePath, testResultFileName);
			//excelreaderObj2.fileWriteClose();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

