package com.ecrm.pages;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecrm.commonMethods.ActionMethods;
import com.ecrm.commonMethods.FieldPosition;
import com.ecrm.commonMethods.VerificationMethods;
import com.ecrm.util.CommonUtility;

public class ContactPage {
	
	WebDriver driver;
	SoftAssert softAssert;
	ActionMethods actionMethods;
	ExtentTest extentTest;
	VerificationMethods verificationMethods;
	FieldPosition fieldPosition;	
	
	public ContactPage(WebDriver driver, SoftAssert softAssert, ExtentTest extentTest)
	{
		this.driver = driver;
		this.softAssert = softAssert;
		this.extentTest = extentTest;
		actionMethods = new ActionMethods(driver, softAssert, extentTest);
		verificationMethods = new VerificationMethods(driver, softAssert, extentTest);
		fieldPosition = new FieldPosition(driver, softAssert, extentTest);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[@class='slds-button slds-show']")
	@CacheLookup
	WebElement BtnAppLancher;
	
	@FindBy(how = How.XPATH, using = "//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[text()='Contacts']]]")
	@CacheLookup
	WebElement BtnContact;
	
 	@FindBy(how=How.XPATH, using = "//div[contains(text(),'New')]")
 	@CacheLookup
	WebElement BtnNew;
 	
	@FindBy(how=How.XPATH, using = "//h2[text()='New Contact']")
	@CacheLookup
	WebElement HdrNewContact;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Salutation']/following-sibling::div")
	@CacheLookup
	WebElement InpSalutation;
	
	@FindBy(how=How.XPATH, using = "//label[text()='First Name']/following::input[@name='firstName']")
	@CacheLookup
	WebElement InpFirstName;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Last Name']/following::input[@name='lastName']")
	@CacheLookup
	WebElement InpLastName;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Account Name']/following-sibling::div")
	@CacheLookup
	WebElement InpAccountName;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Title']/following::input[@name='Title']")
	@CacheLookup
	WebElement InpTitle;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Department']/following::input[@name='Department']")
	@CacheLookup
	WebElement InpDepartment;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Birthdate']/following::input[@name='Birthdate']")
	@CacheLookup
	WebElement InpBirthdate;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Lead Source']/following-sibling::div")
	@CacheLookup
	WebElement InpContactLeadSource;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Contact Currency']/following-sibling::div")
	@CacheLookup
	WebElement InpContactCurrency;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Loan Amount']/following::input[@name='Loan_Amount__c']")
	@CacheLookup
	WebElement InpLoanAmount;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Stage']/following-sibling::div")
	@CacheLookup
	WebElement InpContactStage;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Phone']/following::input[@name='Phone']")
	@CacheLookup
	WebElement InpPhone;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Home Phone']/following::input[@name='HomePhone']")
	@CacheLookup
	WebElement InpHomePhone;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Mobile']/following::input[@name='MobilePhone']")
	@CacheLookup
	WebElement InpMobile;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Other Phone']/following::input[@name='OtherPhone']")
	@CacheLookup
	WebElement InpOtherPhone;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Fax']/following::input[@name='Fax']")
	@CacheLookup
	WebElement InpFax;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Email']/following::input[@name='Email']")
	@CacheLookup
	WebElement InpEmail;
	
	@FindBy(how=How.XPATH, using = "(//label[text()='Mailing Street']/following-sibling::div)/textarea")
	@CacheLookup
	WebElement InpMailingStreet;
	
	@FindBy(how=How.XPATH, using = "(//label[text()='Mailing City']/following-sibling::div)/input")
	@CacheLookup
	WebElement InpMailingCity;
	
	@FindBy(how=How.XPATH, using = "(//label[text()='Mailing State/Province']/following-sibling::div)/input")
	@CacheLookup
	WebElement InpMailingState;
	
	@FindBy(how=How.XPATH, using = "(//label[text()='Mailing Zip/Postal Code']/following-sibling::div)/input")
	@CacheLookup
	WebElement InpMailingZipPostal;
	
	@FindBy(how=How.XPATH, using = "(//label[text()='Mailing Country']/following-sibling::div)/input")
	@CacheLookup
	WebElement InpMailingCountry;

	@FindBy(how=How.XPATH, using = "(//label[text()='Description']/following-sibling::div)/textarea")
	@CacheLookup
	WebElement InpDescription;
	
	@FindBy(how=How.XPATH, using = "//button[text()='Cancel']")
	@CacheLookup
	WebElement BtnCancel;
	
	@FindBy(how=How.XPATH, using = "//button[text()='Save']")
	@CacheLookup
	WebElement BtnSave;
	
	@FindBy(how=How.XPATH, using = "//button[text()='Save & New']")
	@CacheLookup
	WebElement BtnSaveNew;
	
	@FindBy(how=How.XPATH, using="//a[@data-label='Details']")
	@CacheLookup
	WebElement DetailsTab;
	
	String dropdownOptionsXpath = "//span[contains(text(),'?')]";
	
	public void clickContactfromtab() throws IOException {
		verificationMethods.isElementEnabled(BtnContact, 10);
		actionMethods.clickElementWhenVisible(BtnContact, 5);
	}
	
	public void clickNewBtn() throws IOException {
		verificationMethods.isElementEnabled(BtnNew, 10);
		actionMethods.clickElementWhenVisible(BtnNew, 5);
	}
 	
	public void VerifyNewContactHeader() {
		verificationMethods.isElementVisible(HdrNewContact, 20);
	}
	
	public void SelectContactSalutation(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.scrollIntoViewWhenVisible(InpSalutation);
        actionMethods.selectFromDropdownWhenVisible(InpSalutation,dropdownOptionsXpath, map.get("Salutation").toString());
	}
 	
	public void EnterContactFName(Map<Object, Object> map) throws IOException, Exception
	{
		String dynamicContactFirstName = map.get("ContactFirstName").toString() + "_" + CommonUtility.getDateTime();
		actionMethods.enterValueWhenVisible(InpFirstName, dynamicContactFirstName, 5);
	}
	
	public void EnterContactLName(Map<Object, Object> map) throws IOException, Exception
	{
		String dynamicContactLastName = map.get("ContactLastName").toString() + "_" + CommonUtility.getDateTime();
		actionMethods.enterValueWhenVisible(InpLastName, dynamicContactLastName, 5);
		
	}
	
	public void SelectAccountName(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpAccountName, map.get("AccountName").toString(), 20);
		//actionMethods.scrollIntoViewWhenVisible(InpAccountName);
		//Thread.sleep(5000);
        actionMethods.selectFromDropdownWhenVisible(InpAccountName,dropdownOptionsXpath, map.get("AccountName").toString());
        //String AccountName = map.get("AccountName").toString();
        //actionMethods.clickElementWhenVisible(By.xpath("//a[@title='"+AccountName+"']"), 5);
		
	}
	
	public void EnterTitle(Map<Object, Object> map) throws IOException, Exception
	{
		verificationMethods.isElementEnabled(InpTitle, 10);
		actionMethods.enterValueWhenVisible(InpTitle, map.get("Title").toString(), 20);
	}
	
	public void EnterDepartment(Map<Object, Object> map) throws IOException, Exception
	{
	    actionMethods.enterValueWhenVisible(InpDepartment, map.get("Department").toString(), 20);
	}
	
	public void EnterBirthdate(Map<Object, Object> map) throws IOException, Exception
	{
	    actionMethods.enterValueWhenVisible(InpBirthdate, map.get("Birthdate").toString(), 20);
	}
	
	public void SelectContactLeadSource(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.scrollIntoViewWhenVisible(InpContactLeadSource);
        actionMethods.selectFromDropdownWhenVisible(InpContactLeadSource,dropdownOptionsXpath, map.get("ContactLeadSource").toString());
	}	
	
	public void SelectContactCurrency(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.scrollIntoViewWhenVisible(InpContactCurrency);
        actionMethods.selectFromDropdownWhenVisible(InpContactCurrency,dropdownOptionsXpath, map.get("ContactCurrency").toString());
	}
	
	public void EnterLoanAmount(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpLoanAmount, map.get("LoanAmount").toString(), 20);
	}
	
	public void SelectContactStage(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.scrollIntoViewWhenVisible(InpContactStage);
        actionMethods.selectFromDropdownWhenVisible(InpContactStage,dropdownOptionsXpath, map.get("ContactStage").toString());
	}
	
	public void EnterContactPhone(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpPhone, map.get("ContactPhone").toString(), 20);
	}
	
	public void EnterContactHomePhone(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpHomePhone, map.get("HomePhone").toString(), 20);
	}
	
	public void EnterContactMobile(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpMobile, map.get("Mobile").toString(), 20);
	}
	
	public void EnterContactOtherPhone(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpOtherPhone, map.get("OtherPhone").toString(), 20);
	}
	
	public void EnterContactFax(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpFax, map.get("Fax").toString(), 20);
	}
	
	public void EnterContactEmail(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpEmail, map.get("Email").toString(), 20);
	}
	
	public void EnterContactMailingStreet(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpMailingStreet, map.get("MailingStreet").toString(), 20);
	}
	
	public void EnterContactMailingCity(Map<Object, Object> map) throws IOException, Exception 
	{
		actionMethods.enterValueWhenVisible(InpMailingCity, map.get("MailingCity").toString(), 20);
	}
	
	public void EnterContactMailingState(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpMailingState, map.get("MailingState").toString(), 20);
	}
	
	public void EnterContactMailingZipPostal(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpMailingZipPostal, map.get("MailingZipPostal").toString(), 20);
	}
	
	public void EnterContactMailingCountry (Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpMailingCountry, map.get("MailingCountry").toString(), 20);
	}
	
	public void EnterContactDescription(Map<Object, Object> map) throws IOException, Exception
	{
		actionMethods.enterValueWhenVisible(InpDescription, map.get("Description").toString(), 20);
	}
	
	public void BtnContactCancel() throws IOException {
		verificationMethods.isElementEnabled(BtnCancel, 10);
		actionMethods.clickElementWhenVisible(BtnCancel, 5);
	}
	
	public void BtnContactSave() throws IOException {
		verificationMethods.isElementEnabled(BtnSave, 10);
		actionMethods.clickElementWhenVisible(BtnSave, 5);
	}

	public void BtnContactSaveNew() throws IOException {
		verificationMethods.isElementEnabled(BtnSaveNew, 10);
		actionMethods.clickElementWhenVisible(BtnSaveNew, 5);
	}
	
	public void DetailsTab() throws IOException {
		verificationMethods.isElementEnabled(DetailsTab, 10);
		actionMethods.clickElementWhenVisible(DetailsTab, 5);
	}	

}
