package org.iit.healthcare.MMP.patientmodule.pages;

import java.util.HashMap;
import java.util.Properties;

import org.iit.healthcare.Utility;
import org.iit.healthcare.MMP.TestBaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SendMessagePage {

	WebDriver driver;
	
	public SendMessagePage(WebDriver dr)
	{
		this.driver = dr;
	}
	
	public String sendMessages(String msg1, String msg2) throws InterruptedException
	{
		String alertMsg = "";
		
		System.out.println("+++ In sendMessages +++");

		driver.findElement(By.xpath("//input[@id='subject']")).sendKeys(msg1);
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(msg2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Send']")).click();
		Alert alert = driver.switchTo().alert();
		alertMsg = alert.getText();
		// System.out.println("Alert popup msg: " +alertMsg);
		alert.accept();
		return alertMsg;
	}
	
	public HashMap<String, String> fillMessageDetails(Properties pro) throws InterruptedException
	{
		HashMap<String, String> msgData = new HashMap<String, String>();
		String reason = "PAT_AUT_REASON_" +Utility.getRandomString(4);
		System.out.println("reason is: " +reason);
		msgData.put("reason", reason);
		String subject = "PAT_AUT_SUBJECT_" +Utility.getRandomString(4);
		msgData.put("subject", subject);
		System.out.println("subject is: " +subject);
		msgData.put("date", Utility.getFutureDate(0, "dd-MM-YYYY"));
		msgData.put("name",  pro.getProperty("patientName"));
		driver.findElement(By.xpath("//input[@id='subject']")).sendKeys(reason);
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(subject);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Send']")).click();
		return msgData;	
	}
	
	public boolean validateMessages(String msg)
	{
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		alert.accept();
		return alertMsg.equals(msg);
	}
}
