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

public class XMLReader1 {
	public static String testResultFileName = "TestResult.xlsx";
	public static String testResultsheetName = "Result";
	public static String tags[] = {"fields", "nameField"};

	public void XMLReaders(String filePath, String testdataFileName, String sheetName, String filePathXML, String fileNameXML) throws IOException, ParserConfigurationException, SAXException{
		try {

		 	ExcelReader excelreaderObj = new ExcelReader(filePath, testdataFileName, sheetName);
			int lastRowNum = excelreaderObj.getTotalRowsCount();
			int lastCellNum = excelreaderObj.getTotalColumsCount();

			System.out.println("Total Rows: "+lastRowNum);
			System.out.println("Total Columns: "+lastCellNum);
			//ExcelWriter excelwriterObj = new ExcelWriter();

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet spreadsheet = wb.createSheet(testResultsheetName);
			XSSFRow row;
			Map<String, Object[]> testData = new TreeMap<>();
			testData.put("Label" , new Object[] { "Label", "Expected", "Actual" });



			//xml file initialization
			File file = new File(filePathXML + "/" + fileNameXML);
			System.out.println("XML File:  "+file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			System.out.println(doc.getDocumentElement().getNodeName());

			for (String tag : tags) {

				NodeList nodeList = doc.getElementsByTagName(tag);
				int tLength = nodeList.getLength();
				System.out.println("Length: "+tLength);
				int count=0;
				for(int k=0; k<tLength; k++){
					Node node = nodeList.item(k);

					if(node.getNodeType()==Node.ELEMENT_NODE){
						Element element = (Element)node;
						System.out.println();
						System.out.println("====================================================");

						for (int i=1; i<=lastRowNum; i++) {
							for (int j=0;j<lastCellNum;j++) {

							if(element.getElementsByTagName("label").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,0))) {
								if(element.getElementsByTagName("label").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("label") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("label").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("label:		"+element.getElementsByTagName("label").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("label"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"label",excelreaderObj.getCellValue(i,j),element.getElementsByTagName("label").item(0).getTextContent() });
								}

								if(element.getElementsByTagName("fullName").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("fullName") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("fullName").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("fullName:	"+element.getElementsByTagName("fullName").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("fullName"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"fullName",excelreaderObj.getCellValue(i,j),element.getElementsByTagName("fullName").item(0).getTextContent() });
								}

								if(element.getElementsByTagName("type").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("type") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("type").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("type:		"+element.getElementsByTagName("type").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("type"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"type",excelreaderObj.getCellValue(i,j),element.getElementsByTagName("type").item(0).getTextContent() });
								}

								if(element.getElementsByTagName("precision").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("precision") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("precision").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("Pecision:	"+element.getElementsByTagName("precision").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("Pecision"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"precision",excelreaderObj.getCellValue(i,j),element.getElementsByTagName("precision").item(0).getTextContent() });
								}

								if(element.getElementsByTagName("scale").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("scale") && excelreaderObj.getCellValue(0,j) != null && element.getElementsByTagName("scale").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("scale:		"+element.getElementsByTagName("scale").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("scale"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"scale",excelreaderObj.getCellValue(i,j),element.getElementsByTagName("scale").item(0).getTextContent() });
								}

								if(element.getElementsByTagName("required").getLength() == 1 && excelreaderObj.getCellValue(i,j).equals("required") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("required").item(0).getTextContent().equals(excelreaderObj.getCellValue(i,j))) {
									System.out.println("required:	"+element.getElementsByTagName("required").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("required"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"required", excelreaderObj.getCellValue(i,j),element.getElementsByTagName("required").item(0).getTextContent() });
								}
								if(element.getElementsByTagName("unique").getLength() == 1 && excelreaderObj.getCellValue(0,j).equals("unique") && excelreaderObj.getCellValue(i,j) != null && element.getElementsByTagName("unique").item(0).getTextContent().equalsIgnoreCase(excelreaderObj.getCellValue(i,j))) {
									System.out.println("unique:		"+element.getElementsByTagName("unique").item(0).getTextContent()+" is Same as "+excelreaderObj.getCellValue(i,j));
									count++;
									testData.put("unique"+count, new Object[] { excelreaderObj.getCellValue(i,0)+"."+"unique", excelreaderObj.getCellValue(i,j),element.getElementsByTagName("unique").item(0).getTextContent() });
								}
							}
							else {break;}
							}
						}
					}
				}


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
				FileOutputStream out = new FileOutputStream(new File(filePath + "/" + testResultFileName));
				wb.write(out);
				out.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
 	}
}

