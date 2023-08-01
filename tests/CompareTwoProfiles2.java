package com.ecrm.tests;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class CompareTwoProfiles2 {

  @Test
  public void XMLFileReaderTest() throws Exception {
	  try{
			String filePath = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\SatyaTesting1.profile";
			String filePath1 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\Read Only.profile";
			File file = new File(filePath);
			System.out.println("File1:  "+file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());

			File file1 = new File(filePath1);
			System.out.println("File2:  "+file1);
			DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder1 = dbf1.newDocumentBuilder();
			Document doc1 = dBuilder1.parse(file1);
			doc1.getDocumentElement().normalize();
			System.out.println(doc1.getDocumentElement().getNodeName());

			String tags[] = {"applicationVisibilities", "classAccesses", "fieldPermissions",
					"layoutAssignments", "objectPermissions", "pageAccesses", "recordTypeVisibilities",
					"tabVisibilities", "userLicense", "userPermissions" };

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
						System.out.println("====================================================");
						System.out.println("File1 Application Node is : "+element.getNodeName());
						System.out.println("File1 Application: "+element.getElementsByTagName("application").item(0).getTextContent());
						System.out.println("File1 Default: "+element.getElementsByTagName("default").item(0).getTextContent());
						System.out.println("File1 Visible: "+element.getElementsByTagName("visible").item(0).getTextContent());
						System.out.println();
						Element element1 = (Element)node1;
						System.out.println("File2 Application1 Node is : "+element1.getNodeName());
						System.out.println("File2 Application1: "+element1.getElementsByTagName("application").item(0).getTextContent());
						System.out.println("File2 Default1: "+element1.getElementsByTagName("default").item(0).getTextContent());
						System.out.println("File2 Visible1: "+element1.getElementsByTagName("visible").item(0).getTextContent());
						System.out.println();

						if(element.getElementsByTagName("application").item(0).getTextContent().equals(element1.getElementsByTagName("application").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("application").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("application").item(0).getTextContent());
							if(element.getElementsByTagName("default").item(0).getTextContent().equals(element1.getElementsByTagName("default").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("default").item(0).getTextContent());
							}
							else {
								System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("default").item(0).getTextContent());
							}
							if(element.getElementsByTagName("visible").item(0).getTextContent().equals(element1.getElementsByTagName("visible").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visible").item(0).getTextContent());
							}
							else {
								System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visible").item(0).getTextContent());
							}
						}
						else {
							System.out.println(element.getElementsByTagName("application").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("application").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 apexClass: "+element.getElementsByTagName("apexClass").item(0).getTextContent());
							System.out.println("File1 enabled: "+element.getElementsByTagName("enabled").item(0).getTextContent());
							System.out.println();

							System.out.println("File2 Application Node is : "+element1.getNodeName());
							System.out.println("File2 apexClass: "+element1.getElementsByTagName("apexClass").item(0).getTextContent());
							System.out.println("File2 enabled: "+element1.getElementsByTagName("enabled").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("apexClass").item(0).getTextContent().equals(element1.getElementsByTagName("apexClass").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("apexClass").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("apexClass").item(0).getTextContent());
								if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								}
							}
							else {
								System.out.println(element.getElementsByTagName("apexClass").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("apexClass").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 field: "+element.getElementsByTagName("field").item(0).getTextContent());
							System.out.println("File1 editable: "+element.getElementsByTagName("editable").item(0).getTextContent());
							System.out.println("File1 readable: "+element.getElementsByTagName("readable").item(0).getTextContent());
							System.out.println();
							Element element1 = (Element)node1;
							System.out.println("File2 Application Node is : "+element1.getNodeName());
							System.out.println("File2 field: "+element1.getElementsByTagName("field").item(0).getTextContent());
							System.out.println("File2 editable: "+element1.getElementsByTagName("editable").item(0).getTextContent());
							System.out.println("File2 readable: "+element1.getElementsByTagName("readable").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("field").item(0).getTextContent().equals(element1.getElementsByTagName("field").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("field").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("field").item(0).getTextContent());
								if(element.getElementsByTagName("editable").item(0).getTextContent().equals(element1.getElementsByTagName("editable").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("editable").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("editable").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("editable").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("editable").item(0).getTextContent());
								}
								if(element.getElementsByTagName("readable").item(0).getTextContent().equals(element1.getElementsByTagName("readable").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("readable").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("readable").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("readable").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("readable").item(0).getTextContent());
								}
							}
							else {
								System.out.println(element.getElementsByTagName("field").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("field").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 layout: "+element.getElementsByTagName("layout").item(0).getTextContent());
							System.out.println();

							System.out.println("File2 Application Node is : "+element1.getNodeName());
							System.out.println("File2 layout: "+element1.getElementsByTagName("layout").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("layout").item(0).getTextContent().equals(element1.getElementsByTagName("layout").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("layout").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("layout").item(0).getTextContent());
								}
							else {
								System.out.println(element.getElementsByTagName("layout").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("layout").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 object: "+element.getElementsByTagName("object").item(0).getTextContent());
							System.out.println("File1 allowCreate: "+element.getElementsByTagName("allowCreate").item(0).getTextContent());
							System.out.println("File1 allowDelete: "+element.getElementsByTagName("allowDelete").item(0).getTextContent());
							System.out.println("allowEdit: "+element.getElementsByTagName("allowEdit").item(0).getTextContent());
							System.out.println("allowRead: "+element.getElementsByTagName("allowRead").item(0).getTextContent());
							System.out.println("modifyAllRecords: "+element.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
							System.out.println("viewAllRecords: "+element.getElementsByTagName("viewAllRecords").item(0).getTextContent());
							System.out.println();

							Element element1 = (Element)node1;
							System.out.println("File2 Application1 Node is : "+element1.getNodeName());
							System.out.println("File2 object: "+element1.getElementsByTagName("object").item(0).getTextContent());
							System.out.println("File2 allowCreate: "+element1.getElementsByTagName("allowCreate").item(0).getTextContent());
							System.out.println("File2 allowDelete: "+element1.getElementsByTagName("allowDelete").item(0).getTextContent());
							System.out.println("allowEdit: "+element1.getElementsByTagName("allowEdit").item(0).getTextContent());
							System.out.println("allowRead: "+element1.getElementsByTagName("allowRead").item(0).getTextContent());
							System.out.println("modifyAllRecords: "+element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
							System.out.println("viewAllRecords: "+element1.getElementsByTagName("viewAllRecords").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("object").item(0).getTextContent().equals(element1.getElementsByTagName("object").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("object").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("object").item(0).getTextContent());
								if(element.getElementsByTagName("allowCreate").item(0).getTextContent().equals(element1.getElementsByTagName("allowCreate").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowCreate").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowCreate").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("allowCreate").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowCreate").item(0).getTextContent());
								}
								if(element.getElementsByTagName("allowDelete").item(0).getTextContent().equals(element1.getElementsByTagName("allowDelete").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowDelete").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowDelete").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("allowDelete").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowDelete").item(0).getTextContent());
								}
								if(element.getElementsByTagName("allowEdit").item(0).getTextContent().equals(element1.getElementsByTagName("allowEdit").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowEdit").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowEdit").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("allowEdit").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowEdit").item(0).getTextContent());
								}
								if(element.getElementsByTagName("allowRead").item(0).getTextContent().equals(element1.getElementsByTagName("allowRead").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("allowRead").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("allowRead").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("allowRead").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("allowRead").item(0).getTextContent());
								}
								if(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent().equals(element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("modifyAllRecords").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("modifyAllRecords").item(0).getTextContent());
								}
								if(element.getElementsByTagName("viewAllRecords").item(0).getTextContent().equals(element1.getElementsByTagName("viewAllRecords").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("viewAllRecords").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("viewAllRecords").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("viewAllRecords").item(0).getTextContent());
								}
							}
							else {
								System.out.println(element.getElementsByTagName("object").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("object").item(0).getTextContent());
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
						System.out.println("====================================================");
						System.out.println("File1 Application Node is : "+element.getNodeName());
						System.out.println("File1 apexPage: "+element.getElementsByTagName("apexPage").item(0).getTextContent());
						System.out.println("File1 enabled: "+element.getElementsByTagName("enabled").item(0).getTextContent());
						System.out.println();

						System.out.println("File2 Application1 Node is : "+element1.getNodeName());
						System.out.println("File2 apexPage: "+element1.getElementsByTagName("apexPage").item(0).getTextContent());
						System.out.println("File2 enabled: "+element1.getElementsByTagName("enabled").item(0).getTextContent());
						System.out.println();

						if(element.getElementsByTagName("apexPage").item(0).getTextContent().equals(element1.getElementsByTagName("apexPage").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("apexPage").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("apexPage").item(0).getTextContent());
							if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
							}
							else {
								System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
							}
						}
						else {
							System.out.println(element.getElementsByTagName("apexPage").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("apexPage").item(0).getTextContent());
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
						System.out.println("====================================================");
						System.out.println("File1 Application Node is : "+element.getNodeName());
						System.out.println("File1 tab: "+element.getElementsByTagName("tab").item(0).getTextContent());
						System.out.println("File1 visibility: "+element.getElementsByTagName("visibility").item(0).getTextContent());
						System.out.println();

						System.out.println("File2 Application1 Node is : "+element1.getNodeName());
						System.out.println("File2 tab: "+element1.getElementsByTagName("tab").item(0).getTextContent());
						System.out.println("File2 visibility: "+element1.getElementsByTagName("visibility").item(0).getTextContent());
						System.out.println();

						if(element.getElementsByTagName("tab").item(0).getTextContent().equals(element1.getElementsByTagName("tab").item(0).getTextContent())) {
							System.out.println(element.getElementsByTagName("tab").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("tab").item(0).getTextContent());
							if(element.getElementsByTagName("visibility").item(0).getTextContent().equals(element1.getElementsByTagName("visibility").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("visibility").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visibility").item(0).getTextContent());
							}
							else {
								System.out.println(element.getElementsByTagName("visibility").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visibility").item(0).getTextContent());
							}
						}
						else {
							System.out.println(element.getElementsByTagName("tab").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("tab").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 recordType: "+element.getElementsByTagName("recordType").item(0).getTextContent());
							System.out.println("File1 Default: "+element.getElementsByTagName("default").item(0).getTextContent());
							System.out.println("File1 Visible: "+element.getElementsByTagName("visible").item(0).getTextContent());
							System.out.println();
							Element element1 = (Element)node1;
							System.out.println("File2 Application1 Node is : "+element1.getNodeName());
							System.out.println("File2 recordType: "+element1.getElementsByTagName("recordType").item(0).getTextContent());
							System.out.println("File2 Default1: "+element1.getElementsByTagName("default").item(0).getTextContent());
							System.out.println("File2 Visible1: "+element1.getElementsByTagName("visible").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("recordType").item(0).getTextContent().equals(element1.getElementsByTagName("recordType").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("recordType").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("recordType").item(0).getTextContent());
								if(element.getElementsByTagName("default").item(0).getTextContent().equals(element1.getElementsByTagName("default").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("default").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("default").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("default").item(0).getTextContent());
								}
								if(element.getElementsByTagName("visible").item(0).getTextContent().equals(element1.getElementsByTagName("visible").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("visible").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("visible").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("visible").item(0).getTextContent());
								}
							}
							else {
								System.out.println(element.getElementsByTagName("recordType").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("recordType").item(0).getTextContent());
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
							System.out.println("====================================================");
							System.out.println("File1 Application Node is : "+element.getNodeName());
							System.out.println("File1 name: "+element.getElementsByTagName("name").item(0).getTextContent());
							System.out.println("File1 enabled: "+element.getElementsByTagName("enabled").item(0).getTextContent());
							System.out.println();

							System.out.println("File2 Application Node is : "+element1.getNodeName());
							System.out.println("File2 name: "+element1.getElementsByTagName("name").item(0).getTextContent());
							System.out.println("File2 enabled: "+element1.getElementsByTagName("enabled").item(0).getTextContent());
							System.out.println();

							if(element.getElementsByTagName("name").item(0).getTextContent().equals(element1.getElementsByTagName("name").item(0).getTextContent())) {
								System.out.println(element.getElementsByTagName("name").item(0).getTextContent()+" is Same as "+element.getElementsByTagName("name").item(0).getTextContent());
								if(element.getElementsByTagName("enabled").item(0).getTextContent().equals(element1.getElementsByTagName("enabled").item(0).getTextContent())) {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is Same as "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								}
								else {
									System.out.println(element.getElementsByTagName("enabled").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("enabled").item(0).getTextContent());
								}
							}
							else {
								System.out.println(element.getElementsByTagName("name").item(0).getTextContent()+" is different from "+element1.getElementsByTagName("name").item(0).getTextContent());
							}
						}
				}
					break;
					}//case

				}//switch
			}
			}catch (Exception e){
				e.printStackTrace();
			}

		}

	}

