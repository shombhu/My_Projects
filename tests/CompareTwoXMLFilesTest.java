package com.ecrm.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class CompareTwoXMLFilesTest {
	CompareTwoXMLFiles r = new CompareTwoXMLFiles();
    String xml1 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\SatyaTesting1.profile";
	String xml2 = "C:\\Users\\satya.chintala\\Automation\\SFDC-ECRM\\SFDC-ECRM\\src\\test\\java\\com\\ecrm\\testdata\\unpackaged\\profiles\\SatyaTesting.profile";
	List<String> l = new ArrayList<>();


@Test
  public void test() {
	  try {
		r.diff(xml1, xml2, l);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

 }
