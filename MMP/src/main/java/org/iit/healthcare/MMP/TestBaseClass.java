package org.iit.healthcare.MMP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {
	

    //instantiate the driver and load the Properties file
	// get filePath using Sytem.getProperty("user.dir" ="//config//mmp.properties")
	//create File obj and pass the filePath. [File src = new File(filePath)]
	//create FileInputStream and pass the File obj [fis = new FIleInputStream(src)]
	//prop.load(fis)
	
	
	protected WebDriver driver;
	protected Properties prop;
	
	//@Test
	//@BeforeTest
	
	@BeforeClass
	public void instantiateDriver() throws IOException, InterruptedException
	{
		System.out.println("+++ In instantiateDriver +++");
		prop = loadProperties();
		String browser = readPropertiesFile("browser");
		//System.out.println("browser is: " +browser);
	
		if (browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		Thread.sleep(2000);
	}

	public Properties loadProperties() throws IOException
	{
		Properties prop = new Properties();
		
		System.out.println("+++ In loadProperties +++");
		String filePath = System.getProperty("user.dir") +"\\config\\mmp.properties";
		//System.out.println("filePath is: " +filePath);
		File src = new File(filePath);
		FileInputStream fis = new FileInputStream(src);
		prop.load(fis);
		//System.out.println("prop is : " +prop);
		return prop;
	}
	
	public String readPropertiesFile(String key)
	{
		System.out.println("+++ In readPropertiesFile() +++");
		String val = prop.getProperty(key);
		//System.out.println("***val is:" +val);
		return val;
	}
	
	@AfterClass
	public void quitDriver() throws InterruptedException 
	{
		System.out.println("+++ In quitDriver +++");
		Thread.sleep(1000);
		driver.close();
	}
}
