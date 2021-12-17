package org.iit.healthcare.MMP;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class HelperClass {

	//Selenium webdriver functionality (interactions with the website/application)
	
	//protected HelperClass helper;
	WebDriver driver;
	
	public HelperClass(WebDriver dr)
	{
		this.driver = dr;
	}
	
	//@Test
	public void launchApplication(String url)
	{
		System.out.println("+++In launchApplication with +++" +url);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}
	
	public void quitApplication() throws InterruptedException 
	{
		System.out.println("+++In quitApplication++++");
		Thread.sleep(1000);
		driver.close();
	}
	
	public String login(String username, String password)
	{
		System.out.println("+++ In login() +++");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.findElement(By.xpath("//input[@value='Sign In']")).click();
		WebElement name = driver.findElement(By.xpath("//h3[contains(text(), 'ria1')]"));
		return name.getText();
	}
	
	public String adminLogin(String username, String password)
	{
		System.out.println("+++ In adminLogin() +++");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.findElement(By.xpath("//input[@value='Sign In']")).click();
		WebElement name = driver.findElement(By.xpath("//h3[@class='panel-title']"));
		//System.out.println("******* name is :" +name.getText());
		return name.getText();
	}
	
	public void pageRefresh()
	{
		System.out.println("+++ In pageRefresh() +++");
		driver.navigate().refresh();
	}
	
//	public String getLoginPageName()
//	{
//		System.out.println("+++In getLoginPageName+++");
//		WebElement name = driver.findElement(By.xpath("//h3[contains(text(), 'ria1')]"));
//		return name.getText();
//	}
	
	public String loginWithInValidUser( String username, String password)
	{
		// Login with In-valid user name and password and validate
		System.out.println("+++ In loginWithInValidCredentials +++");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		Alert alert = driver.switchTo().alert();
		String popupMsg= alert.getText();
		alert.accept();
		return popupMsg;
	}
	
	public String navigateToPage(String tabName)
	{
		System.out.println("+++ In navigateToPage with: " +tabName +"+++");
		driver.findElement(By.xpath("//span[normalize-space()='"+tabName+"']")).click();
		String title = driver.getTitle();
		//System.out.println("Title of the page is: " + title);
		return title;
		
	}
	
	public String logOut()
	{
		System.out.println("+++ In logOut() +++");
		driver.findElement(By.xpath("//span[normalize-space()='Logout']")).click();
		String title = driver.getTitle();
		//System.out.println("===logOut title is: " +title);
		return title;
		
	}
	public WebDriver switchToAFrameAvailable(String frameId,int timeinSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeinSecs);
		driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
		return driver;
	}
	
	public WebElement visibilityofElementLocated(By locator,int timeinSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeinSecs);
		WebElement e= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return e;
	}
	
	public void switchToSideBar(){
		driver.findElement(By.xpath("//div[@class='left-sidebar']")).click();
	}
	
	public void scrollIntoViewOfWebElement(WebElement we){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);",we);
		System.out.println("Scrolling down to the exact location" );
	}
 
	/// should this be in the Utility Class????
	public void captureScreenshot(String tc_Name) throws IOException
	{
		System.out.println("Inside Capturing Screenshot method");
		TakesScreenshot tsh = (TakesScreenshot)driver;
		File sourceFile = tsh.getScreenshotAs(OutputType.FILE);
		System.out.println(sourceFile.getAbsolutePath());
		String destinationPath = System.getProperty("user.dir")+"\\screenshots\\"+tc_Name+"_"+Calendar.getInstance().getTimeInMillis()%1000000000+".jpg";
		File destFile = new File(destinationPath); 
		FileUtils.copyFile(sourceFile,destFile);
		System.out.println(destinationPath);
		System.out.println("Exiting Screenshot");
		
	}
}
