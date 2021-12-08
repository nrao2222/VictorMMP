package org.iit.healthcare.MMP.patientmodule.tests;

import java.util.HashMap;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.patientmodule.pages.EditProfileZipPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditProfileZip extends TestBaseClass {
	
	@Test (priority = 1)
	public void editProfileZipTests() throws InterruptedException
	{
		String field = "zip";
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH VALID ZIP CODE ++++");
		System.out.println("========================================================\n");
		
		HelperClass helper = new HelperClass(driver);
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		
		String title = helper.navigateToPage("Profile");
		Assert.assertEquals(title, "profile");
		System.out.println("Navigated to Profile tab successfully!!!");
		
		EditProfileZipPage epp = new EditProfileZipPage(driver);
		epp.clickEditButton();
		HashMap<String, String> profileDetails = epp.getProfileDetails();
		System.out.println("Profile Details Before Edits: " +profileDetails.entrySet());
		epp.editProfileZip();
		String msg = epp.clickProfileSaveButton();
		Assert.assertTrue(msg.contains("Your Profile has been updated"));
		HashMap<String, String> editedProfileDetails = epp.getProfileDetails();
		System.out.println("Profile Details After Edits: " +editedProfileDetails.entrySet());
		boolean res = epp.compareMaps(profileDetails, editedProfileDetails, field);
		Assert.assertTrue(res);  //Assertion occurs when the boolean value is false, otherwise executes the next line of code
		System.out.println("Profile field: "+field+" edited successfully!!!!");
		
	}

	@Test (priority = 2)
	public void editProfileInvalidZip() throws InterruptedException
	{
		System.out.println("\n========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH INVALID ZIP CODE ++++");
		System.out.println("========================================================\n");
		
		EditProfileZipPage epp = new EditProfileZipPage(driver);
		epp.clickEditButton();
		epp.editProfileInvalidZip();
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter zipcode"));
		System.out.println("Expected Invalid Zip Error Msg:: "+errMsg+" :: is displayed!!!");
	}
	
	@Test (priority = 3)
	public void editProfileBlankZip() throws InterruptedException
	{
		System.out.println("\n=========================================================");
		System.out.println("++++ TC:  EDIT PROFILE-ZIP WITH A BLANK ZIP CODE ++++");
		System.out.println("=========================================================\n");
		
		EditProfileZipPage epp = new EditProfileZipPage(driver);
		//epp.clickEditButton();
		epp.editProfileDeleteZip();
		String errMsg = epp.editProfileInvalidZipSaveBtn();
		Assert.assertTrue(errMsg.contains("please enter zipcode"));
		System.out.println("Expected Blank Zip Error Msg:: "+errMsg+" :: is displayed!!!");
		
	}
}
