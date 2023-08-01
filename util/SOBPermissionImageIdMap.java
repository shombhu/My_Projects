package com.ecrm.util;


/**
 * This is a mapping class created for Standard Object Permissions Page.
 * The constants in this class are the permissions that user sees on the application.
 * The values for those constants are the 'id' for the img tag of the permission value.
 * Below are the sample examples for the xpaths of permission checkboxes:
 * //img[contains(@id,'Account') and contains(@id,'Read')]
 * //img[contains(@id,'AuthorizationForm') and contains(@id,'Delete')]
 * //img[contains(@id,'AppAnalyticsQueryRequest') and contains(@id,'Update')]
 *
 * We can store all the img id values for all the permission types on the application UI.
 *
 * @author Pooja Ahir
 */
public class SOBPermissionImageIdMap {

	public static String ACCOUNTS = "Account";
	public static String ASSETS = "Asset";
	public static String AUTHORIZATION_FORM = "AuthorizationForm";
	public static String AUTHORIZATION_FORM_CONSENT = "AuthorizationFormConsent";
	public static String CAMPAIGNS = "Campaign";
	public static String CASES = "Case";
	public static String ACTIVE_SCRATCH_ORGS = "ActiveScratchOrg";
	public static String ADDRESSES = "Address";

}
