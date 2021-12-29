package com.crm.TestYantra_genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * @author mahipal
 * 
 */
public class ExcelUtility 
{
	//used to fetch data from excel sheet 
	//returns String 
	public String getDataFromExcelSheet(String sheetName, int rowNum, int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		String Data=row.getCell(cellNum).getStringCellValue();
		wb.close();
		return Data;
		
	}
	//used to get the total no of rows present in sheet
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
	
	//used to set the data into excel sheet 
	public void setDataExcel(String sheetName, int rowNum, int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		Cell cel=row.getCell(cellNum);
		cel.setCellValue(data);
		FileOutputStream fos=new FileOutputStream("./Data/testdata.xlsx");
		wb.write(fos);
		wb.close();
	}
	

}
