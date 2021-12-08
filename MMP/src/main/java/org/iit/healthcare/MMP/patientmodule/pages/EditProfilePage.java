package org.iit.healthcare.MMP.patientmodule.pages;

import java.util.HashMap;
import java.util.Random;

import org.iit.healthcare.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProfilePage {
	
	WebDriver driver;
	
	public EditProfilePage(WebDriver dr)
	{
		this.driver = dr;
	}

	
	public void clickEditButton()
	{
		System.out.println("+++ In Profile tab clickEditButton +++");
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();
	}
	
	public String clickProfileSaveButton()
	{
		System.out.println("+++ In Profile tab clickProfileSaveButton +++");
		driver.findElement(By.xpath("//input[@id='Sbtn']")).click();
		Alert alert= driver.switchTo().alert();
		String alertMsg = alert.getText();
		System.out.println("Alert msg is: " +alertMsg);
		alert.accept();
		return alertMsg;		
	}
	
	public HashMap<String, String> getProfileDetails()
	{
		System.out.println("+++ In Profile tab getProfileDetails() +++");
		HashMap <String, String> profileDetails = new HashMap<String, String>();

		profileDetails.put("First Name", driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"));
		profileDetails.put("Last Name", driver.findElement(By.xpath("//input[@id='lname']")).getAttribute("value"));
		profileDetails.put("License", driver.findElement(By.xpath("//input[@id='licn']")).getAttribute("value"));
		profileDetails.put("SSN", driver.findElement(By.xpath("//input[@id='ssn']")).getAttribute("value"));
		profileDetails.put("Address", driver.findElement(By.xpath("//input[@id='addr']")).getAttribute("value"));
		profileDetails.put("Age", driver.findElement(By.xpath("//input[@id='age']")).getAttribute("value"));
		profileDetails.put("Weight", driver.findElement(By.xpath("//input[@id='weight']")).getAttribute("value"));
		profileDetails.put("City", driver.findElement(By.xpath("//input[@id='city']")).getAttribute("value"));
		profileDetails.put("State", driver.findElement(By.xpath("//input[@id='state']")).getAttribute("value"));
		profileDetails.put("Zip", driver.findElement(By.xpath("//input[@id='zip']")).getAttribute("value"));
				
		return profileDetails;
		
	}
	
	public void editProfileZip() throws InterruptedException
	{
		int randZip = Utility.getRandomZip(2000);
		
		System.out.println("+++ In Profile tab editProfile() +++");
		driver.findElement(By.xpath("//input[@id='zip']")).clear();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//input[@id='zip']")).sendKeys(randZip);  
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys(""+randZip+"");  
		String newZip = driver.findElement(By.xpath("//input[@id='zip']")).getAttribute("value");
		System.out.println("Changing the zip to: " +newZip);	
	}
	
	public void editProfileDeleteZip() throws InterruptedException
	{
		
		System.out.println("+++ In Profile tab editProfileDeleteZip() +++");
		driver.findElement(By.xpath("//input[@id='zip']")).clear();
		Thread.sleep(2000);	
	}
	
	public void editProfileInvalidZip() throws InterruptedException
	{
		System.out.println("++++ In Profile tab editProfileInvalidZip() ++++");
		int invalidZip = Utility.getRandomZip(2000, 3);
		System.out.println("Invalid zip to be entered: " +invalidZip);
		driver.findElement(By.xpath("//input[@id='zip']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys(""+invalidZip+"");
		
	}
	
	public String editProfileInvalidZipSaveBtn()
	{
		System.out.println("+++ In Profile tab  editProfileInvalidZipSaveBtn()+++");
		driver.findElement(By.xpath("//input[@id='Sbtn']")).click();
		String msg = driver.findElement(By.xpath("//p[@id='ziperr']")).getText();
		return msg;
	}
	
	public boolean compareMaps(HashMap<String, String> m1, HashMap<String, String> m2, String field)
	{
		System.out.println("+++ In Profile Tab compareMaps() +++");
		boolean bool = true;

		if (m1.size() != m2.size()) {
			bool = false;
			System.out.println("The two hashmaps are not the same size!!!");
			return bool;
		}

		for (String key : m1.keySet()) {

			//System.out.println("key is:" + key);

			if (key.equalsIgnoreCase(field)) {
				if (m1.get(key).equals(m2.get(key))) {
					System.out.println(" "+field+" in both maps the same - NOT UPDATED!!!!");
					bool = false;
					return bool;
				} else {
					//System.out.println("" + field + " Updated after the Edit!!!");
					bool = true;
				}
			} else {
				if (m1.get(key).equals(m2.get(key))) {
					//System.out.println("" + key + " remains same in both maps!!");
					bool = true;
				} else {
					System.out.println("Field- " + key + " changed when no Edit is done!!");
					bool = false;
					return bool;
				}
			}
		}
		return bool;
	}
	
	
	
	
	public void temp()
	{
//		String name = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[1]")).getText();
//		//String name = driver.findElement(By.xpath("//form[@action='profileup.php']/div/table/tbody/tr[2]/td[1]")).getText();
		//String name = driver.findElement(By.xpath("//form[@name='myform']/div/table/tbody/tr[2]/td[1]")).getText();
		//System.out.println("Profile name is:"+name);
		
//		String zip = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[12]/td[2]")).getText();
//		System.out.println("Profile zip is:"+zip);
//		
//		//profileDetails.put("First Name", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[1]")).getText());
//		profileDetails.put("First Name", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[1]")).getAttribute("value"));
//		profileDetails.put("Last Name", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[2]/td[2]")).getText());
//		profileDetails.put("License", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[4]/td[1]")).getText());
//		profileDetails.put("SSN", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[4]/td[2]")).getText());
//		profileDetails.put("Address", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[6]/td[1]")).getText());
//		profileDetails.put("Age", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[6]/td[2]")).getText());
//		profileDetails.put("Weight", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[8]/td[1]")).getText());
//		profileDetails.put("Height", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[8]/td[2]")).getText());
//		profileDetails.put("City", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[10]/td[1]")).getText());
//		profileDetails.put("State", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[12]/td[1]")).getText());
//		profileDetails.put("Zip", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[12]/td[2]")).getText());
//		profileDetails.put("Provider", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[15]/td[1]")).getText());
//		profileDetails.put("Insurance", driver.findElement(By.xpath("//table[@class='table']/tbody/tr[15]/td[2]")).getText());
		
	}
}
