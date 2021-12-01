package org.iit.healthcare.MMP.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.patientmodule.pages.SendMessagePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendMessage extends TestBaseClass{

	//String reason = "Flu-like symptoms";
	//String msg = "Hi Dr. CHarlie, Experiencing cold/cough/body pain for the past 4-5 days. OTC meds not helping. I would like to make an appointment to see you this week. Thanks!-Kade";
	//String msg = "Hi Dr. CHarlie, Experiencing cold/cough/body pain for the past 4-5 days. Would like to see you this week.Thanks!";
	
	//<<< made these public static to access them from Admin module's ChecnkAdminMessages class>>>
	public static String reason = "Flu-like symptoms";
	public static String msg = "Hi Dr. Charlie. Would like to see you for flu symptoms.Thanks!";
	
	@Test
	public void testSendMessage() throws InterruptedException
	{
		System.out.println("+++ In testSendMessage +++");
		HelperClass helper = new HelperClass(driver);
		
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		//String name = helper.getLoginPageName();
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully!!!");
		
		String title = helper.navigateToPage("Messages");
		Assert.assertEquals(title, "Send Messages");
		System.out.println("Navigated to Messages tab successfully!!");
		
		SendMessagePage smp = new SendMessagePage(driver);
		//HashMap<String, String> patientDetails = smp.fillMessageDetails(prop);
		smp.fillMessageDetails(prop);
		boolean result = smp.validateMessages(prop.getProperty("sendMessagesSuccessfulText"));
		Assert.assertTrue(result);	
		System.out.println("Message sent sucessfully!!!");
	}
	
	public void testSendMessageOld() throws IOException, InterruptedException
	{
		System.out.println("+++ TC: testSendMessage +++");
		//instantiateDriver(); //direct call b'cos of inheritance
		HelperClass helper = new HelperClass(driver); //driver -by inheritance
		
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		//String name = helper.getLoginPageName();
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully!!!");
		
		String title = helper.navigateToPage("Messages");
		Assert.assertEquals(title, "Send Messages");
		System.out.println("Navigated to Messages tab successfully!!");
		
		SendMessagePage smp = new SendMessagePage(driver);
		String sendMsgRes = smp.sendMessages(reason, msg);
		Assert.assertTrue(sendMsgRes.contains("Successfully sent"));
		System.out.println("Message sent sucessfully!!!");
		
		name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
		//helper.quitApplication();	 
	}
}
