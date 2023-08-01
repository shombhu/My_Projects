package com.ecrm.tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompareTwoProfiles1 {
	public String filePath = System.getProperty("user.dir")+"/test-output/testReports/";
	public String filePath1 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\SatyaTesting1.profile";
	public String filePath2 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\Read Only.profile";
	public static String testResultFileName = "ProfilesComparisionResults.xlsx";
	public static String testResultsheetName = "Result";
	public static String tags[] = {"applicationVisibilities", "classAccesses", "fieldPermissions",
			"layoutAssignments", "objectPermissions", "pageAccesses", "recordTypeVisibilities",
			"tabVisibilities", "userLicense", "userPermissions" };

  @Test
  public void XMLFileReaderTest() throws Exception {
	  try{
			File file = new File(filePath1);
			System.out.println("Profile File1-Expected:  "+file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());

			File file1 = new File(filePath2);
			System.out.println("Profile File2-Actual:  "+file1);
			DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder1 = dbf1.newDocumentBuilder();
			Document doc1 = dBuilder1.parse(file1);
			doc1.getDocumentElement().normalize();
			System.out.println(doc1.getDocumentElement().getNodeName());

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet spreadsheet = wb.createSheet(testResultsheetName);
			XSSFRow row;
			Map<String, Object[]> testData = new TreeMap<>();
			testData.put("Label" , new Object[] { "Parent Tag", "Primary Tag", "Child Tag", "Expected Value", "", "Actual Value", "Child Tag" });

			System.out.println("Total Nodes in File1 : "+doc.getDocumentElement().getNodeName());
			System.out.println("Total Nodes in File2 : "+doc1.getDocumentElement().getNodeName());
			for (String tag : tags) {
				switch (tag) {
				case "applicationVisibilities":{

				NodeList nodeList = doc.getElementsByTagName(tag);
				NodeList nodeList1 = doc1.getElementsByTagName(tag);
				int tLength = nodeList.getLength();
				int tLength1 = nodeList1.getLength();
				System.out.println(tag+" Length1: "+tLength);
				System.out.println(tag+" Length2: "+tLength1);

				for(int i=0; i<tLength; i++){
					Node node = nodeList.item(i);
					Node node1 = nodeList1.item(i);

					if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
						Element element = (Element)node;
						Element element1 = (Element)node1;
						System.out.println();

						if(element.getElementsByTagName("application").item(0).getTextContent().equals(element1.getElementsByTagName("application").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("application").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("application").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("application").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("application").item(0).getTextContent(), "application",
											element.getElementsByTagName("application").item(0).getTextContent()," is same as ",
											element1.getElementsByTagName("application").item(0).getTextContent(), "application"});

							if(element.getElementsByTagName("default").item(0).getTextContent().equals(element1.getElementsByTagName("default").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("default").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("default").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("application").item(0).getTextContent(), "default",
												element.getElementsByTagName("default").item(0).getTextContent(), " is same as ",
												element1.getElementsByTagName("default").item(0).getTextContent(), "default"});
							}
							else {
								System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("default").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("default").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("application").item(0).getTextContent(), "default",
												element.getElementsByTagName("default").item(0).getTextContent(), " is different from ",
												element1.getElementsByTagName("default").item(0).getTextContent(), "default"});
							}
							if(element.getElementsByTagName("visible").item(0).getTextContent().equals(element1.getElementsByTagName("visible").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visible").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("visible").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("application").item(0).getTextContent(), "visible",
												element.getElementsByTagName("visible").item(0).getTextContent(), " is same as ",
												element1.getElementsByTagName("visible").item(0).getTextContent(),"visible"});
							}
							else {
								System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visible").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("visible").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("application").item(0).getTextContent(), "visible",
												element.getElementsByTagName("visible").item(0).getTextContent(), " is different from ",
												element1.getElementsByTagName("visible").item(0).getTextContent(),"visible"});
							}
						}
						else {
							System.out.println(element.getElementsByTagName("application").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("application").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("application").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("application").item(0).getTextContent(), "application",
											element.getElementsByTagName("application").item(0).getTextContent(), " is different from ",
											element1.getElementsByTagName("application").item(0).getTextContent(), "application"});
						}
					}

				}
				break;
				}//case

				case "classAccesses":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;
							if(element.getElementsByTagName("apexClass").item(0).getTextContent().equals(element1.getElementsByTagName("apexClass").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("apexClass").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("apexClass").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("apexClass").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("apexClass").item(0).getTextContent(), "apexClass",
												element.getElementsByTagName("apexClass").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("apexClass").item(0).getTextContent(), "apexClass"});

								if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("apexClass").item(0).getTextContent(), "enabled",
													element.getElementsByTagName("enabled").item(0).getTextContent(), " is same as ",
													element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
								}
								else {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("apexClass").item(0).getTextContent(), "enabled",
													element.getElementsByTagName("enabled").item(0).getTextContent(), " is different from ",
													element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
								}
							}
							else {
								System.out.println(element.getElementsByTagName("apexClass").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("apexClass").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("apexClass").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("apexClass").item(0).getTextContent(), "apexClass",
												element.getElementsByTagName("apexClass").item(0).getTextContent(), " is different from ",
												element1.getElementsByTagName("apexClass").item(0).getTextContent(), "apexClass"});
							}
						}
				}
				break;
				}//case

				case "fieldPermissions":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;

							if(element.getElementsByTagName("field").item(0).getTextContent().equals(element1.getElementsByTagName("field").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("field").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("field").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("field").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("field").item(0).getTextContent(), "field",
												element.getElementsByTagName("field").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("field").item(0).getTextContent(), "field"});

								if(element.getElementsByTagName("editable").item(0).getTextContent().equals(element1.getElementsByTagName("editable").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("editable").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("editable").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("editable").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("field").item(0).getTextContent(), "editable",
													element.getElementsByTagName("editable").item(0).getTextContent(), " is same as ",
													element1.getElementsByTagName("editable").item(0).getTextContent(), "editable"});
								}
								else {
									System.out.println(element.getElementsByTagName("editable").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("editable").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("editable").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("field").item(0).getTextContent(), "editable",
													element.getElementsByTagName("editable").item(0).getTextContent(), " is different from ",
													element1.getElementsByTagName("editable").item(0).getTextContent(), "editable"});
								}
								if(element.getElementsByTagName("readable").item(0).getTextContent().equals(element1.getElementsByTagName("readable").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("readable").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("readable").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("readable").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("field").item(0).getTextContent(), "readable",
													element.getElementsByTagName("readable").item(0).getTextContent(), " is Same as ",
													element1.getElementsByTagName("readable").item(0).getTextContent(), "readable"});
								}
								else {
									System.out.println(element.getElementsByTagName("readable").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("readable").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("readable").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("field").item(0).getTextContent(), "readable",
													element.getElementsByTagName("readable").item(0).getTextContent(), " is different from ",
													element1.getElementsByTagName("readable").item(0).getTextContent(), "readable"});
								}
							}
							else {
								System.out.println(element.getElementsByTagName("field").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("field").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("field").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("field").item(0).getTextContent(), "field",
												element.getElementsByTagName("field").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("field").item(0).getTextContent(), "field"});
							}
						}
					}
					break;
					}//case

				case "layoutAssignments":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;

							if(element.getElementsByTagName("layout").item(0).getTextContent().equals(element1.getElementsByTagName("layout").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("layout").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("layout").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("layout").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("layout").item(0).getTextContent(), "layout",
												element.getElementsByTagName("layout").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("layout").item(0).getTextContent(), "layout"});
							}
							else {
								System.out.println(element.getElementsByTagName("layout").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("layout").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("layout").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("layout").item(0).getTextContent(), "layout",
												element.getElementsByTagName("layout").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("layout").item(0).getTextContent(), "layout"});
							}
						}
					}
					break;
					}//case

				case "objectPermissions":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;

							if(element.getElementsByTagName("object").item(0).getTextContent().equals(element1.getElementsByTagName("object").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("object").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("object").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("object").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("object").item(0).getTextContent(), "object",
												element.getElementsByTagName("object").item(0).getTextContent()," is Same as ",
												element1.getElementsByTagName("object").item(0).getTextContent(), "object"});
								if(element.getElementsByTagName("allowCreate").item(0).getTextContent().equals(element1.getElementsByTagName("allowCreate").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowCreate").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowCreate").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowCreate").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowCreate").item(0).getTextContent(), "allowCreate",
													element.getElementsByTagName("allowCreate").item(0).getTextContent()," is same as ",
													element1.getElementsByTagName("allowCreate").item(0).getTextContent(), "allowCreate"});
								}
								else {
									System.out.println(element.getElementsByTagName("allowCreate").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowCreate").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowCreate").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowCreate").item(0).getTextContent(), "allowCreate",
													element.getElementsByTagName("allowCreate").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("allowCreate").item(0).getTextContent(), "allowCreate"});
								}
								if(element.getElementsByTagName("allowDelete").item(0).getTextContent().equals(element1.getElementsByTagName("allowDelete").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowDelete").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowDelete").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowDelete").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowDelete").item(0).getTextContent(), "allowDelete",
													element.getElementsByTagName("allowDelete").item(0).getTextContent()," is Same as ",
													element1.getElementsByTagName("allowDelete").item(0).getTextContent(), "allowDelete"});
								}
								else {
									System.out.println(element.getElementsByTagName("allowDelete").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowDelete").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowDelete").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("allowDelete").item(0).getTextContent(), "allowDelete",
											element.getElementsByTagName("allowDelete").item(0).getTextContent()," is different from ",
											element1.getElementsByTagName("allowDelete").item(0).getTextContent(), "allowDelete"});
								}
								if(element.getElementsByTagName("allowEdit").item(0).getTextContent().equals(element1.getElementsByTagName("allowEdit").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowEdit").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowEdit").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowEdit").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("allowEdit").item(0).getTextContent(), "allowEdit",
											element.getElementsByTagName("allowEdit").item(0).getTextContent()," is Same as ",
											element1.getElementsByTagName("allowEdit").item(0).getTextContent(), "allowEdit"});
								}
								else {
									System.out.println(element.getElementsByTagName("allowEdit").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowEdit").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowEdit").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowEdit").item(0).getTextContent(), "allowEdit",
													element.getElementsByTagName("allowEdit").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("allowEdit").item(0).getTextContent(), "allowEdit"});
								}
								if(element.getElementsByTagName("allowRead").item(0).getTextContent().equals(element1.getElementsByTagName("allowRead").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowRead").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowRead").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowRead").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowRead").item(0).getTextContent(), "allowRead",
													element.getElementsByTagName("allowRead").item(0).getTextContent()," is Same as ",
													element1.getElementsByTagName("allowRead").item(0).getTextContent(), "allowRead"});
								}
								else {
									System.out.println(element.getElementsByTagName("allowRead").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowRead").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowRead").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("allowRead").item(0).getTextContent(), "allowRead",
													element.getElementsByTagName("allowRead").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("allowRead").item(0).getTextContent(), "allowRead"});
								}
								if(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent().equals(element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("allowRead").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("modifyAllRecords").item(0).getTextContent(), "modifyAllRecords",
													element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()," is Same as ",
													element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent(), "modifyAllRecords"});
								}
								else {
									System.out.println(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("modifyAllRecords").item(0).getTextContent(), "modifyAllRecords",
													element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent(), "modifyAllRecords"});
								}
								if(element.getElementsByTagName("viewAllRecords").item(0).getTextContent().equals(element1.getElementsByTagName("viewAllRecords").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("viewAllRecords").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("viewAllRecords").item(0).getTextContent(), "viewAllRecords",
													element.getElementsByTagName("viewAllRecords").item(0).getTextContent()," is same as ",
													element1.getElementsByTagName("viewAllRecords").item(0).getTextContent(), "viewAllRecords"});
								}
								else {
									System.out.println(element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("viewAllRecords").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("viewAllRecords").item(0).getTextContent(), "viewAllRecords",
													element.getElementsByTagName("viewAllRecords").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("viewAllRecords").item(0).getTextContent(), "viewAllRecords"});
								}
							}
							else {
								System.out.println(element.getElementsByTagName("object").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("object").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("object").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("object").item(0).getTextContent(), "object",
												element.getElementsByTagName("object").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("object").item(0).getTextContent(), "object"});
							}
						}

					}
					break;
					}//case

				case "pageAccesses":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

				for(int i=0; i<tLength; i++){
					Node node = nodeList.item(i);
					Node node1 = nodeList1.item(i);

					if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
						Element element = (Element)node;
						Element element1 = (Element)node1;

						if(element.getElementsByTagName("apexPage").item(0).getTextContent().equals(element1.getElementsByTagName("apexPage").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("apexPage").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("apexPage").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("apexPage").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("apexPage").item(0).getTextContent(), "apexPage",
									element.getElementsByTagName("apexPage").item(0).getTextContent()," is same as ",
									element1.getElementsByTagName("apexPage").item(0).getTextContent(), "apexPage"});
							if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("enabled").item(0).getTextContent(), "enabled",
												element.getElementsByTagName("enabled").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
							}
							else {
								System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("enabled").item(0).getTextContent(), "enabled",
												element.getElementsByTagName("enabled").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
							}
						}
						else {
							System.out.println(element.getElementsByTagName("apexPage").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("apexPage").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("apexPage").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("apexPage").item(0).getTextContent(), "apexPage",
											element.getElementsByTagName("apexPage").item(0).getTextContent()," is different from ",
											element1.getElementsByTagName("apexPage").item(0).getTextContent(), "apexPage"});
						}
					}
				}
					break;
					}//case

				case "tabVisibilities":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

				for(int i=0; i<tLength; i++){
					Node node = nodeList.item(i);
					Node node1 = nodeList1.item(i);

					if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
						Element element = (Element)node;
						Element element1 = (Element)node1;

						if(element.getElementsByTagName("tab").item(0).getTextContent().equals(element1.getElementsByTagName("tab").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("tab").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("tab").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("tab").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("tab").item(0).getTextContent(), "tab",
											element.getElementsByTagName("tab").item(0).getTextContent()," is same as ",
											element1.getElementsByTagName("tab").item(0).getTextContent(), "tab"});
							if(element.getElementsByTagName("visibility").item(0).getTextContent().equals(element1.getElementsByTagName("visibility").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("visibility").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visibility").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("visibility").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("visibility").item(0).getTextContent(), "visibility",
												element.getElementsByTagName("visibility").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("visibility").item(0).getTextContent(), "visibility"});
							}
							else {
								System.out.println(element.getElementsByTagName("visibility").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visibility").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("visibility").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("visibility").item(0).getTextContent(), "visibility",
												element.getElementsByTagName("visibility").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("visibility").item(0).getTextContent(), "visibility"});
							}
						}
						else {
							System.out.println(element.getElementsByTagName("tab").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("tab").item(0).getTextContent());
							testData.put(element.getNodeName() + element.getElementsByTagName("tab").item(0).getTextContent()+i, new Object[]
									{tag, element.getElementsByTagName("tab").item(0).getTextContent(), "tab",
											element.getElementsByTagName("tab").item(0).getTextContent()," is different from ",
											element1.getElementsByTagName("tab").item(0).getTextContent(), "tab"});
						}
					}
				}
					break;
					}//case

				case "recordTypeVisibilities":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;

							if(element.getElementsByTagName("recordType").item(0).getTextContent().equals(element1.getElementsByTagName("recordType").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("recordType").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("recordType").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("recordType").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("recordType").item(0).getTextContent(), "recordType",
												element.getElementsByTagName("recordType").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("recordType").item(0).getTextContent(), "recordType"});
								if(element.getElementsByTagName("default").item(0).getTextContent().equals(element1.getElementsByTagName("default").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("default").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("default").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("default").item(0).getTextContent(), "default",
													element.getElementsByTagName("default").item(0).getTextContent()," is same as ",
													element1.getElementsByTagName("default").item(0).getTextContent(), "default"});
								}
								else {
									System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("default").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("default").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("default").item(0).getTextContent(), "default",
													element.getElementsByTagName("default").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("default").item(0).getTextContent(), "default"});
								}
								if(element.getElementsByTagName("visible").item(0).getTextContent().equals(element1.getElementsByTagName("visible").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visible").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("visible").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("visible").item(0).getTextContent(), "visible",
													element.getElementsByTagName("visible").item(0).getTextContent()," is same as ",
													element1.getElementsByTagName("visible").item(0).getTextContent(), "visible"});
								}
								else {
									System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visible").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("visible").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("visible").item(0).getTextContent(), "visible",
													element.getElementsByTagName("visible").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("visible").item(0).getTextContent(), "visible"});
								}
							}
							else {
								System.out.println(element.getElementsByTagName("recordType").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("recordType").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("recordType").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("recordType").item(0).getTextContent(), "recordType",
												element.getElementsByTagName("recordType").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("recordType").item(0).getTextContent(), "recordType"});
							}
						}

					}
					break;
					}//case

				case "userLicense":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					System.out.println(tag+" Length"+tLength);

					for(int i=0; i<tLength; i++){
						Node node = nodeList.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							System.out.println("Application: "+element.getAttribute("userLicense"));
							//System.out.println("userLicense: "+element.getElementsByTagName("userLicense").item(0).getTextContent());
							System.out.println();
						}

					}
					break;
					}//case

				case "userPermissions":{

					NodeList nodeList = doc.getElementsByTagName(tag);
					NodeList nodeList1 = doc1.getElementsByTagName(tag);
					int tLength = nodeList.getLength();
					int tLength1 = nodeList1.getLength();
					System.out.println(tag+" Length1: "+tLength);
					System.out.println(tag+" Length2: "+tLength1);


					for(int i=0; i<tLength && i<tLength1; i++){
						Node node = nodeList.item(i);
						Node node1 = nodeList1.item(i);

						if(node.getNodeType()==Node.ELEMENT_NODE && node1.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element)node;
							Element element1 = (Element)node1;

							if(element.getElementsByTagName("name").item(0).getTextContent().equals(element1.getElementsByTagName("name").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("name").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("name").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("name").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("name").item(0).getTextContent(), "name",
												element.getElementsByTagName("name").item(0).getTextContent()," is same as ",
												element1.getElementsByTagName("name").item(0).getTextContent(), "name"});
								if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("enabled").item(0).getTextContent(), "enabled",
													element.getElementsByTagName("enabled").item(0).getTextContent()," is same as ",
													element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
								}
								else {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
									testData.put(element.getNodeName() + element.getElementsByTagName("enabled").item(0).getTextContent()+i, new Object[]
											{tag, element.getElementsByTagName("enabled").item(0).getTextContent(), "enabled",
													element.getElementsByTagName("enabled").item(0).getTextContent()," is different from ",
													element1.getElementsByTagName("enabled").item(0).getTextContent(), "enabled"});
								}
							}
							else {
								System.out.println(element.getElementsByTagName("name").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("name").item(0).getTextContent());
								testData.put(element.getNodeName() + element.getElementsByTagName("name").item(0).getTextContent()+i, new Object[]
										{tag, element.getElementsByTagName("name").item(0).getTextContent(), "name",
												element.getElementsByTagName("name").item(0).getTextContent()," is different from ",
												element1.getElementsByTagName("name").item(0).getTextContent(), "name"});
							}
						}
				}
					break;
					}//case

				}//switch
				Set<String> keyid = testData.keySet();
				int rowid = 0;

				// writing the data into excel sheet...
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

