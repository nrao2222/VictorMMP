package org.iit.healthcare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

// 

public class Utility {

//Utility class: Not specific to any module but across modules/projects, java functionality
// useful from the framework perspective. like readxl, read xls etc
//	Excel-File
//	XSSFWorkbook
//		XSSFSheet - 0,1,2
//			rows	
//			cols
//			XSSFCell  -(0,0)-> get the contents
	
	@Test  
	public void testFileRead() throws IOException
	{
		String filePath = System.getProperty("user.dir") +"\\testData\\actidata.xlsx";
		System.out.println("filePath is: " +filePath);
		String[][] arr = readXlsx(filePath);
		System.out.println("arr is: " +arr);
		for(int i =0; i < arr.length ; i++)
		{
			for(int j=0; j< arr[0].length; j++)
			{
				System.out.println("arr[i][j]: " +arr[i][j]);
			}
		}
		 
		
	}
	
	
	///static methods, can be invoked with class name, without creating an instance of the class ==>Utility.readXls()
	public static String[][] readXls(String filePath) throws BiffException, IOException{

		File srcFile = new File(filePath);
		Workbook wb = Workbook.getWorkbook(srcFile);
		Sheet sheet = wb.getSheet(0);
		int row = sheet.getRows();
		int col = sheet.getColumns();
		
		String[][] str = new String[row][col];
		
		for (int i=0; i<row; i++){
			for (int j=0;j<col; j++){
				
				Cell cell = sheet.getCell(j, i);
				str[i][j] = cell.getContents().toString();
			}
		}
		
		return str;		
	}
	
	
	public static String[][] readXlsx(String filePath) throws IOException{
		
		File srcFile = new File(filePath);
		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int row = sheet.getLastRowNum()+1;
		int col = 2;
		String[][] str = new String[row][col];
		for (int i=0; i<row; i++){
			//get the cell contents
			str[i][0] = sheet.getRow(i).getCell(0).toString();
			str[i][1] = sheet.getRow(i).getCell(1).toString();
		}
		wb.close();
		return str;		
	}
	

	public static String getFutureDate(int noOfDays, String pattern)
	{
		Calendar c1 = Calendar.getInstance();
		System.out.println("Current Date is: " +c1.getTime());
		c1.add(Calendar.DATE, noOfDays);
		System.out.println("Current Date is: " +c1.getTime());
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		//Date in January/8/2022 format
		//SimpleDateFormat sdf = new SimpleDateFormat("MMMM/d/YYYY"); //To get full month name
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(c1.getTime());
		return date;
	}
	
	public static String getFutureDate(int noOfDays, int mnth, int year, String pattern)
	{
		Calendar c1 = Calendar.getInstance();
		System.out.println("Current Date is: " +c1.getTime());
		c1.add(Calendar.YEAR, year);
		c1.add(Calendar.MONTH, mnth);
		c1.add(Calendar.DATE, noOfDays);
		System.out.println("Current Date is: " +c1.getTime());
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		//Date in January/8/2022 format
		//SimpleDateFormat sdf = new SimpleDateFormat("MMMM/d/YYYY"); //To get full month name
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(c1.getTime());
		return date;
	}
	
	public static int getRandomNumber(int boundary){
		Random r1 = new Random();
		//returns a number between 0 - 1999(2000 is excluded)
		int i = r1.nextInt(boundary);
		//System.out.println(i);
		return i;
	}
	
	//random 5 digit zip
	public static int getRandomZip(int boundary)
	{
		
		Random r = new Random();
		return(r.nextInt(boundary) * 1000);
	}
	
	public static int getRandomZip(int boundary, int digits)
	{
		Random r = new Random();
		if(digits < 5)
		{
			return(r.nextInt(boundary)*100);
		}
		else
		{
			return(r.nextInt(boundary) * 10000);
		}
	}
	
	public static char getRandomChar()
	{
		Random r1 = new Random();
		int k = 65 + r1.nextInt(26);
		char c2= (char) k;
		System.out.println("Random Upper Case Character::" + c2);		
		return c2;

	}
	public static String getRandomString(int limit)
	{
		String ranAlpha = RandomStringUtils.randomAlphabetic(limit);
		//System.out.println("================ranAlpha is:" +ranAlpha);
		return ranAlpha;
		  
	}
	
//	public int getRowCount(int sheetindex)
//	{
//		return wb.getSheetAt(sheetindex).getLastRowNum()+1;
//	}
//	
//	//Method overloading: getRowCount with 2 different parameters!
//	public int getRowCount(String sheetname)
//	{
//		return wb.getSheet(sheetname).getLastRowNum()+1;
//		}
//	
//	public String getCellData(int sheetindex, int row, int cell)
//	{
//		 return wb.getSheetAt(sheetindex).getRow(row).getCell(cell).toString();
//	}
//	
//	public String getCellData(String sheetname, int row, int cell)
//	{
//		return wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
//		
//	}
}
