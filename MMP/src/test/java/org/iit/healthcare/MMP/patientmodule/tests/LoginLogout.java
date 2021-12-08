package org.iit.healthcare.MMP.patientmodule.tests;

import java.io.IOException;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

//11/29/2021: committing to github ...trying again with git token. works but 2 "master" branches

public class LoginLogout extends TestBaseClass {
	
	
	@Test(priority=1)
	public void testLoginValidUser() throws Exception, InterruptedException
	{
		System.out.println("==================================================");
		System.out.println("+++ TC: testLogin With Valid Credentials+++");
		System.out.println("==================================================");
		HelperClass helper = new HelperClass(driver);
	
		helper.launchApplication(prop.getProperty("URL"));
		System.out.println("calling helper.launchApplication...");
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully with Valid credentials!!!");
		name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
	}
	
	@Test (priority=2)
	public void testLoginInvalidUser() throws IOException, InterruptedException
	{
		System.out.println("==================================================");
		System.out.println("+++ TC: testLogin With InValid Credentials");
		System.out.println("==================================================");
		HelperClass helper = new HelperClass(driver);
		
		helper.launchApplication(prop.getProperty("URL"));
		String msg = helper.loginWithInValidUser(prop.getProperty("invalidPatientUser"), prop.getProperty("patientPassword"));
		System.out.println("Alert msg is: " +msg);
		Assert.assertTrue(msg.contains("Wrong username and password"));
		System.out.println("Invalid User Login Test Passed!!");
		//helper.quitApplication();
	}

}
