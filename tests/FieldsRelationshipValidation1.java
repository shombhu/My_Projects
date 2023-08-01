package com.ecrm.tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.ecrm.util.XMLReader2;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ecrm.util.XMLReader;

public class FieldsRelationshipValidation1 {


	public static String filePath = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/";
	public static String testdataFileName = "ObjectManagerFieldsValidation.xlsx";
	public static String sheetName = "Trip1__c";

	public static String filePathXML = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/unpackaged/objects/";
	public static String fileNameXML = "Trip1__c.object";

	public static String tags[] = {"fields", "nameField"};

 @Test
	public void fieldandRelationship() throws IOException, ParserConfigurationException, SAXException {
	XMLReader2 xmlReaderObject = new XMLReader2();
	xmlReaderObject.XMLReaders(filePath, testdataFileName, sheetName, filePathXML, fileNameXML);
 }
}

