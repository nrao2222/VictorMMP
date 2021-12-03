package org.iit.healthcare.MMP.patientmodule.pages;

import java.util.HashMap;
import java.util.List;

import org.iit.healthcare.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {

	WebDriver driver;
	String time = "10Am";
	
	public ScheduleAppointmentPage(WebDriver dr) {
		this.driver = dr;
	}
	
	public HashMap<String,String> bookAppointment(String docName) throws InterruptedException
	{
	
		HashMap<String, String> expectedHMap = new HashMap<String, String>();
		
		System.out.println("+++In bookAppointment()+++");
		String date = Utility.getFutureDate(10, 4, 0, "MMMM/d/YYYY");
		System.out.println("Future date is: " +date);	
		clickCreateNewApptButton();
		clickBookApptButton(docName);
		switchToFrame();
		System.out.println("Switched to frame.......!");
		
		//Date logic
		System.out.println("clicking date box...");
		String appointmentDate = date.split("/")[1];
		String appointmentMonth = date.split("/")[0];
		String appointmentYear = date.split("/")[2];
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		String displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		String displayedYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		while (!(displayedYear.equals(appointmentYear))) {
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			displayedYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		}
		System.out.println("displayedYear is: " + displayedYear);
		while (!(displayedMonth.equals(appointmentMonth))) {
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			displayedMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		}
		System.out.println("displayedMonth is: " + displayedMonth);

		driver.findElement(By.xpath("//a[normalize-space()='" + appointmentDate + "']")).click();

		//**time dropdown
		Thread.sleep(2000);
		System.out.println("clicking time...");
		// driver.findElement(By.xpath("//body/p[2]/select/option[1]")).click();
		WebElement drp = driver.findElement(By.xpath("//select[@id='time']"));
		Select sel = new Select(drp);
		sel.selectByValue(time);
		Thread.sleep(2000);

		// Fetch the text you entered in a text box, by getAttribute("value")
		// ...before leaving the frame!
		expectedHMap.put("date", driver.findElement(By.xpath("//input[@id='datepicker']")).getAttribute("value"));
		
		System.out.println("clicking continue...");
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		
		//clicking submit without entering symptoms
		System.out.println("clicking submit without entering symptoms....");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Alert alert = driver.switchTo().alert();
		//String popupMsg= alert.getText();
		System.out.println("pop up msg is:: " +alert.getText());
		alert.accept();
		//clicking submit after entering symptoms
		System.out.println("clicking submit after entering symptoms....");
		
		//generate ramdom number to add to symptom string....
		int num = Utility.getRandomNumber(1000);
		//int num = getRandomNumber(1000);
		driver.findElement(By.xpath("//textarea[@id='sym']")).sendKeys("headache" + num + "");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// these are values that are passed
		expectedHMap.put("doctor", docName);
		expectedHMap.put("time", time);
		expectedHMap.put("symptoms", "headache" + num + "");

		return expectedHMap;
	}

	public void clickCreateNewApptButton()
	{
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
	}
	
	public void clickBookApptButton(String doc){
		driver.findElement(By.xpath("(//h4[normalize-space()='Dr."+doc+"']/ancestor::ul/following-sibling::button)[1]")).click();
	}
	
	public void switchToFrame()
	{
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='myframe']"));
		driver.switchTo().frame(frame);
	}
	
	public HashMap<String,String> fetchPatientDetails()
	{
		HashMap<String, String> actualHMap = new HashMap<String, String>();
		
		//fetching the web table row contents to validate it with values entered - FindElements()
		List<WebElement> tdList = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[1]/td"));
		actualHMap.put("date", tdList.get(0).getText());
		actualHMap.put("time", tdList.get(1).getText());
		actualHMap.put("symptoms", tdList.get(2).getText());
		actualHMap.put("doctor", tdList.get(3).getText());
		return actualHMap;
	}
}
