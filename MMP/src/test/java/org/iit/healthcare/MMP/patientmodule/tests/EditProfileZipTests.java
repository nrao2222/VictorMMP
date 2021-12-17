package org.iit.healthcare.MMP.patientmodule.tests;

import java.util.HashMap;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.patientmodule.pages.EditProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditProfileZipTests extends TestBaseClass {
	
	HashMap<String, String> profileDetails = new HashMap<String, String>();
	HashMap<String, String> editedProfileDetails = new HashMap<String, String>();
	SoftAssert sa = new SoftAssert();
	//HelperClass helper = new HelperClass(driver);
	
	@Test (priority = 1)
	public void editProfileValidZip() throws InterruptedException
	{
		
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH VALID ZIP CODE ++++");
		System.out.println("========================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		//Assert.assertEquals(name, prop.getProperty("patientUser"));
		sa.assertEquals(name, prop.getProperty("patientUser"), "Wrong username in Login page!!");
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		//Assert.assertEquals(title, "profile");
		sa.assertEquals(title, "profile", "Navigation to Profile page failed!!");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		int zip = epp.editProfileZip();
		profileDetails.put("Zip", ""+zip+"");
		System.out.println("Expected Profile Details with new zip: " +profileDetails.entrySet().toString());
		String msg = epp.clickProfileSaveButton();
		//Assert.assertTrue(msg.contains("Your Profile has been updated"));
		sa.assertEquals(msg, "Your Profile has been updated.", "The Alert message doesn't match!!");
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		//Assert.assertEquals(editedProfileDetails, profileDetails);
		sa.assertEquals(editedProfileDetails, profileDetails, "The hashmaps don't match!!");
		System.out.println("Profile Zip Code edited successfully!!!!");
		sa.assertAll();
		
	}
	

	@Test (priority = 2)
	public void editProfileInvalidZip() throws InterruptedException
	{
		System.out.println("\n================================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH INVALID ZIP: SHORT ZIP CODE ++++");
		System.out.println("================================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileInvalidZip("short");
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		//Assert.assertTrue(errMsg.contains("please enter zipcode"));
		sa.assertEquals(errMsg, "please enter zipcode", "Error Message not correct!!");
		System.out.println("Expected Invalid Zip Error Msg:: "+errMsg+" :: is displayed!!!");
	
		helper.pageRefresh();		
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		//Assert.assertEquals(editedProfileDetails, profileDetails);
		sa.assertEquals(editedProfileDetails, profileDetails, "The hashmaps don't match!!");
		System.out.println("Edit Profile - Invalid Zip Test passed!!!!");
		sa.assertAll();
	}
	
	
	@Test (priority = 3)
	public void editProfileLongZip() throws InterruptedException
	{
		System.out.println("\n=================================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH INVALID ZIP: LONG ZIP CODE ++++");
		System.out.println("=================================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileInvalidZip("long");
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		//Assert.assertTrue(errMsg.contains("please enter zipcode"));
		sa.assertEquals(errMsg, "please enter zipcode", "Error Message not correct!!");
		System.out.println("Expected Invalid Zip Error Msg:: "+errMsg+" :: is displayed!!!");
		
		helper.pageRefresh();
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		//Assert.assertEquals(editedProfileDetails, profileDetails);
		sa.assertEquals(editedProfileDetails, profileDetails, "The hashmaps don't match!!");
		System.out.println("Edit Profile - Invalid Zip Test passed!!!!");		
		sa.assertAll();
	}
	
	
	@Test (priority = 4)
	public void editProfileAlphaZip() throws InterruptedException
	{
		System.out.println("\n==================================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH INVALID ZIP: ALPHA ZIP CODE ++++");
		System.out.println("===================================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileInvalidZip("alpha");
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		//Assert.assertTrue(errMsg.contains("please enter zipcode"));
		sa.assertEquals(errMsg, "please enter zipcode", "Error Message not correct!!");
		System.out.println("Expected Invalid Zip Error Msg:: "+errMsg+" :: is displayed!!!");
		
		helper.pageRefresh();
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		//Assert.assertEquals(editedProfileDetails, profileDetails);
		sa.assertEquals(editedProfileDetails, profileDetails, "The hashmaps don't match!!");
		System.out.println("Edit Profile - Invalid Zip Test passed!!!!");	
		sa.assertAll();
	}
	
	
	@Test (priority = 5)
	public void editProfileBlankZip() throws InterruptedException
	{
		System.out.println("\n=========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH A BLANK ZIP CODE ++++");
		System.out.println("=========================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileDeleteZip();
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		//Assert.assertTrue(errMsg.contains("please enter zipcode"));
		sa.assertEquals(errMsg, "please enter zipcode", "Error Message not correct!!");
		System.out.println("Expected Blank Zip Error Msg:: "+errMsg+" :: is displayed!!!");
		
		helper.pageRefresh();
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		//Assert.assertEquals(editedProfileDetails, profileDetails);
		sa.assertEquals(editedProfileDetails, profileDetails, "The hashmaps don't match!!");
		System.out.println("Edit Profile - Invalid Zip Test passed!!!!");
		
	}
}
