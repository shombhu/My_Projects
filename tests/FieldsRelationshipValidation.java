package com.ecrm.tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ecrm.util.XMLReader;

public class FieldsRelationshipValidation {


	public static String filePathSource = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/";
	public static String filePathDest = System.getProperty("user.dir")+"/test-output/testReports/";
	public static String dataFileName = "ObjectManagerFieldsValidation.xlsx";
	public static String sheetName = "Trip1__c";

	public static String filePathXML = System.getProperty("user.dir")+"/src/test/java/com/ecrm/testdata/unpackaged/objects/";
	public static String fileNameXML = "Trip1__c.object";


	@Test
	public void fieldandRelationship() throws IOException, ParserConfigurationException, SAXException {
		XMLReader xmlReaderObject = new XMLReader();
		xmlReaderObject.XMLReaders(filePathSource, filePathDest, dataFileName, filePathXML, fileNameXML);
	}
}

