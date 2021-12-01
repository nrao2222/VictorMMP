package org.iit.healthcare.MMP.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.healthcare1.Utility;
import org.iit.healthcare.MMP.HelperClass;
import org.iit.healthcare.MMP.TestBaseClass;
import org.iit.healthcare.MMP.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScheduleAppointment extends TestBaseClass {
	
	String tabName = "Schedule Appointment";
	String doc = "Charlie";
	
	@Test
	public void testScheduleAppointment() throws IOException, InterruptedException
	{
		System.out.println("+++ TC: testScheduleAppointment +++");
		//instantiateDriver(); //direct call b'cos of inheritance
		HelperClass helper = new HelperClass(driver); //from inheritance
		
		helper.launchApplication(prop.getProperty("URL"));
		String name = helper.login(prop.getProperty("patientUser"), prop.getProperty("patientPassword"));
		Assert.assertEquals(name, prop.getProperty("patientUser"));
		System.out.println("Logged in successfully!!!");
		
		String title = helper.navigateToPage(tabName);
		Assert.assertEquals(title, "Shedule Appointments");
		System.out.println("Navigated to Schedule Appointment tab successfully!! ");
		
		ScheduleAppointmentPage appt = new ScheduleAppointmentPage(driver);
		HashMap<String, String> expectedHMap = appt.bookAppointment(doc);
		HashMap<String, String> actualHMap = appt.fetchPatientDetails();
		Assert.assertEquals(actualHMap, expectedHMap);
		System.out.println("Scheduled Appointment Successfully!");
		
		name = helper.logOut();
		Assert.assertTrue(name.contains("NAMTG"));
		System.out.println("Logged out successfully");
		//helper.quitApplication();
	}

}
