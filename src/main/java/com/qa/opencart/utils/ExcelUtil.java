package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil 
{
	private static Workbook book;
	private static Sheet sheet;
	public static final String TEST_DATA_SHEET_PATH="./src/test/resources/testdata/OpenCartTestData01.xlsx";
	
	
	public static Object[][] getTestData(String sheetName)
	{
		Object data[][]=null;
		
		try 
		{
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			
			for(int i=0;i<sheet.getLastRowNum();i++)
			{
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					System.out.println(i+"   "+j);
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
		} 
		
		
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	

}
