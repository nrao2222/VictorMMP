package org.iit.healthcare.MMP.adminmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.adminmodule.pages.AdminMessagesPage;
import org.iit.healthcare.MMP.patientmodule.pages.SendMessagePage;
import org.iit.healthcare.MMP.patientmodule.tests.SendMessage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckAdminMessages extends TestBaseClass{
	
	String tabName = "Messages";
	//===>modify hard asserts to soft asserts <========
	//String reason = "Flu-like symptoms";
	//String msg = "Hi Dr. CHarlie, Experiencing cold/cough/body pain for the past 4-5 days. OTC meds not helping. I would like to make an appointment to see you this week. Thanks!-Kade";
	//String msg = "Hi Dr. Charlie. Would like to see you for flu symptoms.Thanks!";
	
	@Test
	public void testAdminMessages() throws InterruptedException
	{
		System.out.println("+++ TC: E2E Validate Admin Messages +++");
		
		HelperClass helper = new HelperClass(driver); 
		SoftAssert sa = new SoftAssert();
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		//String name = helper.getLoginPageName();
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged into Patient module successfully!!!");
		String title = helper.navigateToPage(tabName);
		Assert.assertEquals(title, "Send Messages");
		System.out.println("Navigated to Patient-Messages tab successfully!!");
		SendMessagePage smp = new SendMessagePage(driver);
		HashMap<String, String> expectedHMap = smp.fillMessageDetails(prop);
		Thread.sleep(2000);
		boolean result = smp.validateMessages(prop.getProperty("sendMessagesSuccessfulText"));
		Assert.assertTrue(result);	
		System.out.println("Patient Message sent sucessfully!!!");
		
		
		helper.launchApplication(prop.getProperty("urlAdmin"));
		name = helper.adminLogin(prop.getProperty("adminUser"), prop.getProperty("adminPassword"));
		Assert.assertTrue(name.contains("Admin Portal"));
		System.out.println("Logged into the Admin module successfully!!!");
		title = helper.navigateToPage(tabName);
		Assert.assertEquals(title, tabName);
		System.out.println("Navigated to Admin-Messages tab successfully!! ");
		AdminMessagesPage amp = new AdminMessagesPage(driver);
		HashMap<String, String> actualHMap = amp.fetchLatestMsgDetails();
		Thread.sleep(2000);
		sa.assertEquals(actualHMap, expectedHMap);
		sa.assertAll();
		System.out.println("Validate Admin Message with Patient Message: PASS!!");
	}
	
	//@Test
	void testAdminMessagesOld() throws IOException, InterruptedException
	{
		HashMap<String, String> expectedHMap = new HashMap<String, String>();
		expectedHMap.put("Reason", SendMessage.reason); //reading public static vars from SendMessage class in Patient module pkg
		expectedHMap.put("Message", SendMessage.msg);
		
		System.out.println("+++ TC: Validate Admin Messages +++");
		//instantiateDriver(); //create driver obj
		HelperClass helper = new HelperClass(driver); 
		
		helper.launchApplication(prop.getProperty("urlAdmin"));
		String name = helper.adminLogin(prop.getProperty("adminUser"), prop.getProperty("adminPassword"));
		Assert.assertTrue(name.contains("Admin Portal"));
		System.out.println("Logged into the Admin module successfully!!!");
		
		String title = helper.navigateToPage(tabName);
		Assert.assertEquals(title, tabName);
		System.out.println("Navigated to Admin-Messages tab successfully!! ");
		
		AdminMessagesPage amp = new AdminMessagesPage(driver);
		HashMap<String, String> actualHMap = amp.getAdminMessage();
		Assert.assertEquals(actualHMap, expectedHMap);
		//msg getting truncated in the Admin module. So TC is failing if msg is long..!
		System.out.println("Admin Message validated with Patient Message!!");
		
		name = helper.logOut();
		Assert.assertTrue(name.contains("Login1"));
		System.out.println("Logged out of Admin module successfully!!!");
		//helper.quitApplication();
	}
	

}
