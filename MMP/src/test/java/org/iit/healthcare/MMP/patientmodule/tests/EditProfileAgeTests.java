package org.iit.healthcare.MMP.patientmodule.tests;

import java.util.HashMap;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.patientmodule.pages.EditProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfileAgeTests extends TestBaseClass{

	HashMap<String, String> profileDetails = new HashMap<String, String>();
	HashMap<String, String> editedProfileDetails = new HashMap<String, String>();
	HelperClass helper = new HelperClass(driver);
	
	@Test (priority = 1)
	public void editProfileValidAge() throws InterruptedException
	{
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-AGE WITH VALID AGE ++++");
		System.out.println("========================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		Assert.assertEquals(title, "profile");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		int age = epp.editProfileValidAge();
		profileDetails.put("Age", ""+age+"");
		System.out.println("Expected Profile Details with Age field updated: " +profileDetails.entrySet().toString());
		String msg = epp.clickProfileSaveButton();
		Assert.assertTrue(msg.contains("Your Profile has been updated"));
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		Assert.assertEquals(editedProfileDetails, profileDetails);
		System.out.println("Profile Age edited successfully!!!!");
		
	}
	
	@Test (priority = 2)
	public void editProfileLargeAge() throws InterruptedException
	{
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-AGE WITH AGE 101 ++++");
		System.out.println("========================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileInvalidAge(101);
		String errMsg = epp.editProfileInvalidAgeSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter valid age"));
		System.out.println("Expected Invalid Age Error Msg:: "+errMsg+" :: is displayed!!!");
		
		String name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
		helper.launchApplication(prop.getProperty("URL"));
		name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		Assert.assertEquals(title, "profile");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		Assert.assertEquals(editedProfileDetails, profileDetails);
		System.out.println("Edit Profile - Invalid Age Test passed!!!!");			
	}
	
	@Test (priority = 3)
	public void editProfileDeleteAge() throws InterruptedException
	{
		HelperClass helper = new HelperClass(driver);
		
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-AGE WITH BLANK AGE ++++");
		System.out.println("========================================================\n");
		
		EditProfilePage epp = new EditProfilePage(driver);
		profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet().toString());
		epp.clickEditButton();
		epp.editProfileDeleteAge();
		String errMsg = epp.editProfileBlankAgeSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter age"));
		System.out.println("Expected Invalid Age Error Msg:: "+errMsg+" :: is displayed!!!");
		
		String name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
		helper.launchApplication(prop.getProperty("URL"));
		name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		Assert.assertEquals(title, "profile");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		Assert.assertEquals(editedProfileDetails, profileDetails);
		System.out.println("Edit Profile - Blank Age Test passed!!!!");			
	}
	
	@Test (priority = 4)
	public void editProfileNegativeAge() throws InterruptedException
	{
		HelperClass helper = new HelperClass(driver);
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-AGE WITH NEGATIVE AGE ++++");
		System.out.println("========================================================\n");
		
		EditProfilePage epp = new EditProfilePage(driver);
		epp.clickEditButton();
		epp.editProfileInvalidAge(-10);
		String errMsg = epp.editProfileInvalidAgeSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter valid age"));
		System.out.println("Expected Invalid Age Error Msg:: "+errMsg+" :: is displayed!!!");
		
		String name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
		helper.launchApplication(prop.getProperty("URL"));
		name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		Assert.assertEquals(title, "profile");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		editedProfileDetails = epp.getProfileDetails();
		System.out.println("Actual Profile Details After Edit: " +editedProfileDetails.entrySet().toString());
		Assert.assertEquals(editedProfileDetails, profileDetails);
		System.out.println("Edit Profile - Invalid Age Test passed!!!!");	
	}
		
	//@Test (priority = 5)
	public void editProfileSmallAge() throws InterruptedException
	{
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-AGE WITH AGE 0 ++++");
		System.out.println("========================================================\n");
		
		EditProfilePage epp = new EditProfilePage(driver);
		epp.editProfileInvalidAge(0);
		String errMsg = epp.editProfileInvalidAgeSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter valid age"));
		System.out.println("Expected Invalid Age Error Msg:: "+errMsg+" :: is displayed!!!");
		
	}
	
}
