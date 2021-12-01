package org.iit.healthcare.MMP.adminmodule.pages;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminMessagesPage {

    WebDriver driver;   
	
	public AdminMessagesPage(WebDriver dr)
	{
		this.driver = dr;
	}
	
	public HashMap<String, String> fetchLatestMsgDetails()
	{
		HashMap<String, String> actualHMap = new HashMap<String, String>();
		System.out.println("+++ In fetchLatestMsgDetails +++");
		
		//actualHMap.put("reason", driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText());
		//actualHMap.put("subject", driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText());
		actualHMap.put("reason", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[2]")).getText());
		actualHMap.put("subject", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[3]/td[2]")).getText());
		actualHMap.put("date", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[3]")).getText()  );
		actualHMap.put("name", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[1]")).getText() );
		return actualHMap;
		
	}
	
	public boolean validateMsg (String expectedMsg)
	{
		Alert alert = driver.switchTo().alert();
		String actualMsg = alert.getText();
		return actualMsg.equals(expectedMsg);
	}
	
	public HashMap<String,String> getAdminMessage()
	{
		HashMap<String, String> actualHMap = new HashMap<String, String>();
		System.out.println("+++ In getAdminMessage +++");
		
		WebElement ele = driver.findElement(By.xpath("//tbody/tr[2]/td[2]"));
		String text = ele.getText();
		System.out.println("Ele text is: " +text);
		actualHMap.put("Reason", text);
		ele = driver.findElement(By.xpath("//tbody/tr[3]/td[2]"));
		text = ele.getText();
		System.out.println("Ele text is: " +text);
		actualHMap.put("Message", text);
		return actualHMap;				
	}
}
